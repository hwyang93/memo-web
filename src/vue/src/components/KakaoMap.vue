<template>
  <div class="map_wrap">
    <div id="map"></div>
    <div id="menu_wrap" class="bg_white">
      <div class="option">
        <div>
          <div>
            검색 : <input v-model="searchKeyword" type="text" id="keyword" size="15">
            <button @click="searchPlaces" type="submit">검색하기</button>
          </div>
        </div>
      </div>
      <hr>
      <ul id="placesList"></ul>
      <div id="pagination"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "KakaoMap",
  props: {
    scheduleList : Array
  },
  data() {
    return {
      map: {
        map: null,
        markers: [],
        searchClusterer: null,
        scheduleClusterer: null
      },
      local: {
        lon: 0,
        lat: 0
      },
      searchKeyword: '',
      searchPlaceList: [],
      searchMarkers: [],
      scheduleMarkers: []
    };
  },
  watch: {
    scheduleList : function() {
      setTimeout(()=>{
        this.setScheduleMarkers();
      },500)
    }
  },
  beforeMount() {
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      script.async = false;
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
          "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=233fb509b41527eb6530cb8ca5b635c5&libraries=services,clusterer,drawing";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      this.getLocation().then(() => {
        const container = document.getElementById("map");
        const options = {
          center: new kakao.maps.LatLng(this.local.lat, this.local.lon),
          level: 3,
        };
        this.map.map = new kakao.maps.Map(container, options);
        const markerPosition = new kakao.maps.LatLng(this.local.lat, this.local.lon);
        const marker = new kakao.maps.Marker({
          position: markerPosition
        });
        const nowMarkerImage = new kakao.maps.MarkerImage(
            '/icon/now_marker.png',
            new kakao.maps.Size(31, 35), new kakao.maps.Point(13, 34));
        marker.setMap(this.map.map);
        marker.setImage(nowMarkerImage);
        const geocoder = new kakao.maps.services.Geocoder();
        const infowindow = new kakao.maps.InfoWindow({zindex: 1});
        let clickMarker = new kakao.maps.Marker({});
        kakao.maps.event.addListener(this.map.map, 'click', mouseEvent => {
          geocoder.coord2Address(mouseEvent.latLng.La, mouseEvent.latLng.Ma, (result, status) => {
            if (status === kakao.maps.services.Status.OK) {
              let detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
              detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';

              let content = '<div class="bAddr">' + '<span class="title">법정동 주소정보</span>' + detailAddr + '</div>';

              clickMarker.setPosition(mouseEvent.latLng);
              clickMarker.setMap(this.map.map);

              infowindow.setContent(content);
              infowindow.open(this.map.map, clickMarker);
              let sendData = {};
              if (!result[0].road_address) {
                sendData = result[0].address
              } else {
                sendData = result[0].road_address
              }
              sendData.x  = mouseEvent.latLng.La;
              sendData.y  = mouseEvent.latLng.Ma;
              this.setMarkerInfo(sendData);
            }
          });
        });

        this.map.searchClusterer = new kakao.maps.MarkerClusterer({
          map: this.map.map,
          averageCenter: true,
          minLevel: 7
        });
        this.map.scheduleClusterer = new kakao.maps.MarkerClusterer({
          map: this.map.map,
          averageCenter: true,
          minLevel: 7
        });
      });
    },
    getLocation() {
      return new Promise(((resolve) => {
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition((position) => {
            this.local.lat = position.coords.latitude;
            this.local.lon = position.coords.longitude;
            resolve();
          })
        }
      }))

      // if (navigator.geolocation) {
      //   navigator.geolocation.getCurrentPosition((position) => {
      //     debugger;
      //     console.log(position)
      //     this.local.lat = position.coords.latitude;
      //     this.local.lon = position.coords.longitude;
      //   });
      // }

      // if (navigator.geolocation) {
      //   navigator.geolocation.getCurrentPosition(
      //     function (position) {
      //       this.latitude = position.coords.latitude;
      //       this.longitude = position.coords.longitude;
      //     },
      //     function (error) {
      //       console.error(error);
      //     },
      // {
      //   enableHighAccuracy: false,
      //   maximumAge: 0,
      //   timeout: Infinity,
      // }
      //   );
      //   console.log(this);
      // } else {
      //   alert("GPS를 지원하지 않습니다");
      // }
    },
    searchPlaces() {
      const keyword = this.searchKeyword;

      if (!keyword.replace(/^\s+|\s+$/g, '')) {
        alert('키워드를 입력해주세요!');
        return false;
      }

      const ps = new kakao.maps.services.Places();

      ps.keywordSearch(keyword, this.placesSearchCB);
    },
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        this.searchPlaceList = data;
        this.displayPlaces(data);
        this.displayPagination(pagination);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        alert('검색 결과가 존재하지 않습니다.');
        return false;
      } else if (status === kakao.maps.services.Status.ERROR) {
        alert('검색 결과 중 오류가 발생했습니다.');
        return false;
      }
    },
    displayPlaces(data) {
      const listEl = document.getElementById('placesList');
      if (listEl.hasChildNodes()) {
        while (listEl.hasChildNodes()) {
          listEl.removeChild (listEl.lastChild);
        }
      }
      const fragment = document.createDocumentFragment();
      const bounds = new kakao.maps.LatLngBounds();
      this.map.searchClusterer.removeMarkers(this.searchMarkers);
      this.searchMarkers = [];
      let markers = [];
      const searchInfowindow = new kakao.maps.InfoWindow({zindex: 1});
      data.forEach((item, index) => {
        const markerImage = new kakao.maps.MarkerImage(
            '/icon/promise_marker.png',
            new kakao.maps.Size(31, 35), new kakao.maps.Point(13, 34));
        const itemEl = this.getListItem(index, item);

        const searchMarkerPosition = new kakao.maps.LatLng(item.y, item.x);
        const searchMarker = new kakao.maps.Marker({position: searchMarkerPosition});
        searchMarker.setImage(markerImage);
        markers.push(searchMarker);
        this.searchMarkers.push(searchMarker);
        bounds.extend(searchMarkerPosition);

        kakao.maps.event.addListener(searchMarker, 'mouseover', () => {
          let content = '<div style="padding:5px;z-index:1;">' + item.place_name + '</div>';
          searchInfowindow.setContent(content);
          searchInfowindow.open(this.map.map, searchMarker);
        });
        kakao.maps.event.addListener(searchMarker, 'mouseout', () => {
          searchInfowindow.close();
        });
        kakao.maps.event.addListener(searchMarker, 'click', () => {
          this.setMarkerInfo(item);
        })
        itemEl.onmouseover = () => {
          let content = '<div style="padding:5px;z-index:1;">' + item.place_name + '</div>';
          searchInfowindow.setContent(content);
          searchInfowindow.open(this.map.map, searchMarker);
        }
        itemEl.onmouseout = () => {
          searchInfowindow.close();
        }
        itemEl.onclick = () => {
          this.setMarkerInfo(item);
          this.map.map.setCenter(new kakao.maps.LatLng(item.y, item.x));
        }

        fragment.appendChild(itemEl);
      })
      listEl.appendChild(fragment);
      this.map.searchClusterer.addMarkers(markers);
      this.map.map.setBounds(bounds);

    },
    displayPagination(pagination) {
      const paginationEl = document.getElementById('pagination');
      const fragment = document.createDocumentFragment();

      while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild (paginationEl.lastChild);
      }

      for (let i=1; i<=pagination.last; i++) {
        const el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;

        if (i===pagination.current) {
          el.className = 'on';
        } else {
          el.onclick = (function(i) {
            return function() {
              pagination.gotoPage(i);
            }
          })(i);
        }

        fragment.appendChild(el);
      }
      paginationEl.appendChild(fragment);
    },
    setMarkerInfo(data) {
      this.$emit("setMarkerInfo", data);
    },
    getListItem(index, places) {

      const el = document.createElement('li');
      let itemStr = '<span class="markerbg marker_' + (index + 1) + '"></span>' +
          '<div class="info">' +
          '   <h5>' + places.place_name + '</h5>';

      if (places.road_address_name) {
        itemStr += '    <span>' + places.road_address_name + '</span>' +
            '   <span class="jibun gray">' + places.address_name + '</span>';
      } else {
        itemStr += '    <span>' + places.address_name + '</span>';
      }

      itemStr += '  <span class="tel">' + places.phone + '</span>' + '</div>';

      el.innerHTML = itemStr;
      el.className = 'item';

      return el;
    },
    onClickSearchItem(item) {

    },
    setScheduleMarkers() {
      const scheduleMarkers = [];
      this.map.scheduleClusterer.clear();
      const scheduleInfowindow = new kakao.maps.InfoWindow({zindex: 1});
      this.scheduleList.forEach(item => {
        const scheduleMarkerPosition = new kakao.maps.LatLng(item.lat, item.lon);
        const scheduleMarker = new kakao.maps.Marker({position: scheduleMarkerPosition});
        scheduleMarkers.push(scheduleMarker);

        kakao.maps.event.addListener(scheduleMarker, 'mouseover', () => {
          let content = '';
          if (typeof item.startDate != 'undefined') {
            content = '<div>장소 : ' + item.promisePlace + '</div>';
            content += '<div>일정 : ' + item.startDate + ' ~ ' + item.endDate + '</div>';
            content += '<div>메모 : ' + item.memo + '</div>';
          } else {
            content = '<div>장소 : ' + item.memoPlace + '</div>';
            content += '<div>메모 : ' + item.memo + '</div>';
          }
          scheduleInfowindow.setContent(content);
          scheduleInfowindow.open(this.map.map, scheduleMarker);
        });
        kakao.maps.event.addListener(scheduleMarker, 'mouseout', () => {
          scheduleInfowindow.close();
        });
      })
      this.map.scheduleClusterer.addMarkers(scheduleMarkers);
    },
    setCenter(y, x) {
      this.map.map.setCenter(new kakao.maps.LatLng(y, x));
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  width: 100%;
  height: 100%;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
.map_wrap, .map_wrap * {margin:0;padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
.map_wrap a, .map_wrap a:hover, .map_wrap a:active{color:#000;text-decoration: none;}
.map_wrap {position:relative;width:100%;height:500px;}
#menu_wrap {position:absolute;top:0;left:0;bottom:0;width:250px;margin:10px 0 30px 10px;padding:5px;overflow-y:auto;background:rgba(255, 255, 255, 0.7);z-index: 1;font-size:12px;border-radius: 10px;}
.bg_white {background:#fff;}
#menu_wrap hr {display: block; height: 1px;border: 0; border-top: 2px solid #5F5F5F;margin:3px 0;}
#menu_wrap .option{text-align: center;}
#menu_wrap .option p {margin:10px 0;}
#menu_wrap .option button {margin-left:5px;}
#placesList li {list-style: none;}
#placesList .item {position:relative;border-bottom:1px solid #888;overflow: hidden;cursor: pointer;min-height: 65px;}
#placesList .item span {display: block;margin-top:4px;}
#placesList .item h5, #placesList .item .info {text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
#placesList .item .info{padding:10px 0 10px 55px;}
#placesList .info .gray {color:#8a8a8a;}
#placesList .info .jibun {padding-left:26px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;}
#placesList .info .tel {color:#009900;}
#placesList .item .markerbg {float:left;position:absolute;width:36px; height:37px;margin:10px 0 0 10px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;}
#placesList .item .marker_1 {background-position: 0 -10px;}
#placesList .item .marker_2 {background-position: 0 -56px;}
#placesList .item .marker_3 {background-position: 0 -102px}
#placesList .item .marker_4 {background-position: 0 -148px;}
#placesList .item .marker_5 {background-position: 0 -194px;}
#placesList .item .marker_6 {background-position: 0 -240px;}
#placesList .item .marker_7 {background-position: 0 -286px;}
#placesList .item .marker_8 {background-position: 0 -332px;}
#placesList .item .marker_9 {background-position: 0 -378px;}
#placesList .item .marker_10 {background-position: 0 -423px;}
#placesList .item .marker_11 {background-position: 0 -470px;}
#placesList .item .marker_12 {background-position: 0 -516px;}
#placesList .item .marker_13 {background-position: 0 -562px;}
#placesList .item .marker_14 {background-position: 0 -608px;}
#placesList .item .marker_15 {background-position: 0 -654px;}
#pagination {margin:10px auto;text-align: center;}
#pagination a {display:inline-block;margin-right:10px;}
#pagination .on {font-weight: bold; cursor: default;color:#777;}

</style>
