<template>
  <div class="main">
    <main class="home-banner">
      <div class="container home-banner-container space-type-1">
        <div class="row home-banner-row shadow-wrap-1">
          <!-- kakao map -->
          <div class="col-lg-8 col-sm-12">
            <div class="map_wrap">
              <div id="map" class="map" style="width: 100%; height: 550px"></div>
              <div id="menu_wrap" class="bg_white">
                <div class="option">
                  <div>
                    <div>
                      검색 : <input type="text" value="" id="keyword" size="15">
                      <button @click="searchPlaces()" type="submit">검색하기</button>
                    </div>
                  </div>
                </div>
                <hr>
                <ul id="placesList">
                  <li v-for="item in searchPlaceList" :key="item.id">
                    <div class="info" @click="setFormInfo(item)">
                      <h5>{{item.place_name}}</h5>
                      <span>{{ item.road_address_name }}</span>
                      <span class="jibun gray"> {{ item.address_name }}</span>
                      <span class="tel">{{ item.phone }}</span>
                    </div>
                  </li>
                </ul>
                <div id="pagination"></div>
              </div>
            </div>
          </div>
          <!-- 위치 정보 입력 -->
          <div class="col-lg-4">
            <div class="content-center">
              <div class="row">
                <div class="form-group col-md-12">
                  <div class="form-group search_2">
                    <input type="text" class="form-control" placeholder="검색" id="s" />
                    <button type="button" class="btn">
                      <i class="fas fa-search"></i>
                    </button>
                  </div>
                  <div class="contact-section comment_form">
                    <div class="contactus_form">
                      <h3 class="comment_title"><span></span></h3>
                      <div class="row">
                        <div class="form-group col-lg-12">
                          <div class="input-group">
                            <input v-model="form.title" type="text" class="form-control" placeholder="제목" />
                          </div>
                        </div>
                        <div class="form-group col-lg-12">
                          <div class="input-group">
                            <input v-model="form.promisePlace" type="text" class="form-control" placeholder="위치" readonly />
                          </div>
                        </div>
                        <div class="form-group col-lg-12">
                          <div class="input-group">
                            <input v-model="form.startDate" type="text" class="form-control" placeholder="First Name" />
                          </div>
                        </div>
                        <div class="form-group col-lg-12">
                          <div class="input-group">
                            <input v-model="form.endDate" type="text" class="form-control" placeholder="Last Name" />
                          </div>
                        </div>
                      </div>
                      <div class="form-group">
                        <div class="input-group">
                          <textarea v-model="form.memo" class="form-control" placeholder="메모" style="resize: none"></textarea>
                        </div>
                      </div>
                      <div class="comment_submit_btn text-right">
                        <b-button variant="outline-primary" @click="onSave()">일정 저장</b-button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import axiosUtil from '../utils/axios-util.js';
export default {
  name: 'Main',
  components: {},
  data() {
    return {
      localLat: 0,
      localLng: 0,
      map: null,
      clusterer: null,
      infowindow: null,
      ps : null,
      form: {
        title: '제목입력',
        startDate: '2021-04-01',
        endDate: '2021-04-15',
        promisePlace: '',
        memo: '일정',
        lon: '',
        lat: '',
        latLng: {}
      },
      userScheduleList: [],
      searchPlaceList: []
    };
  },
  watch: {
    localLat: function () {
      window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById('map');
      const options = {
        center: new kakao.maps.LatLng(this.localLat, this.localLng),
        level: 3
      };

      this.map = new kakao.maps.Map(container, options);
      this.ps = new kakao.maps.services.Places();
      // 마커가 표시될 위치입니다
      const markerPosition = new kakao.maps.LatLng(this.localLat, this.localLng);
      // 마커를 생성합니다
      const marker = new kakao.maps.Marker({
        position: markerPosition
      });
      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(this.map);

      this.clusterer = new kakao.maps.MarkerClusterer({
        map: this.map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 7 // 클러스터 할 최소 지도 레벨
      });

      // 주소-좌표 변환 객체를 생성합니다
      const geocoder = new kakao.maps.services.Geocoder();
      this.infowindow = new kakao.maps.InfoWindow({ zindex: 1 }); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다
      // 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
      kakao.maps.event.addListener(this.map, 'click', mouseEvent => {
        this.form.latLng = mouseEvent.latLng;
        geocoder.coord2Address(mouseEvent.latLng.La, mouseEvent.latLng.Ma, (result, status) => {
          if (status === kakao.maps.services.Status.OK) {
            var detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
            detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';

            var content = '<div class="bAddr">' + '<span class="title">법정동 주소정보</span>' + detailAddr + '</div>';
            this.form.promisePlace = !!result[0].road_address ? result[0].road_address.address_name : result[0].address.address_name;
            this.form.lon = mouseEvent.latLng.La;
            this.form.lat = mouseEvent.latLng.Ma;
            // 마커를 클릭한 위치에 표시합니다
            marker.setPosition(mouseEvent.latLng);
            marker.setMap(this.map);

            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
            this.infowindow.setContent(content);
            this.infowindow.open(this.map, marker);
          }
        });
      });
      if (this.userScheduleList.length > 0) {
        this.setMarkers();
      }
    },
    addScript() {
      let script = document.createElement('script');
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=233fb509b41527eb6530cb8ca5b635c5&libraries=services,clusterer,drawing';
      document.head.appendChild(script);
    },
    showPosition(position) {
      this.localLat = position.coords.latitude;
      this.localLng = position.coords.longitude;
    },
    getLocation() {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(this.showPosition);
      }
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
    onSave() {
      axiosUtil.post('/api/main/saveUserSchedule.do', this.form, result => {
        alert('저장되었습니다.');
        this.getSchedule();
      });
    },
    getSchedule() {
      axiosUtil.get('/api/main/getUserSchedule.do', {}, result => {
        this.userScheduleList = result.data.userScheduleList;
        this.getLocation();
      });
    },
    setMarkers() {
      let markers = this.userScheduleList.map(item => {
        return new kakao.maps.Marker({
          position: new kakao.maps.LatLng(item.lat, item.lon)
        });
      });
      this.clusterer.addMarkers(markers);

      markers.map((marker, index) => {
        let content = '<div>장소 : ' + this.userScheduleList[index].promisePlace + '</div>';
        content += '<div>일정 : ' + this.userScheduleList[index].startDate + ' ~ ' + this.userScheduleList[index].endDate + '</div>';
        content += '<div>일정 : ' + this.userScheduleList[index].memo + '</div>';
        // let infowindow = new kakao.maps.InfoWindow({
        //   content : content
        // });

        // 마커에 마우스오버 이벤트를 등록합니다
        kakao.maps.event.addListener(marker, 'mouseover', () => {
          this.infowindow.setContent(content);
          this.infowindow.open(this.map, marker);
        });

        // 마커에 마우스오버 이벤트를 등록합니다
        kakao.maps.event.addListener(marker, 'mouseout', () => {
          this.infowindow.close();
        });
      });
    },
    searchPlaces() {
      const keyword = document.getElementById('keyword').value;

      if (!keyword.replace(/^\s+|\s+$/g, '')) {
        alert('키워드를 입력해주세요!');
        return false;
      }

      // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
      this.ps.keywordSearch( keyword, this.placesSearchCB);
    },
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        console.log(data);
        this.searchPlaceList = data;

        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        this.displayPlaces(data);
        // 페이지 번호를 표출합니다
        // displayPagination(pagination);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        alert('검색 결과가 존재하지 않습니다.');
        return;
      } else if (status === kakao.maps.services.Status.ERROR) {
        alert('검색 결과 중 오류가 발생했습니다.');
        return;

      }
    },
    displayPlaces(data) {
      data.forEach(item => {
        console.log(item);
      })
    },
    setFormInfo(data) {
      console.log(data);
      this.form.promisePlace = !!data.road_address_name ? data.road_address_name: data.address.address_name;
      this.form.lon = data.x;
      this.form.lat = data.y;
    }
  },
  beforeMount() {
    this.getSchedule();
  },
  mounted() {}
};
</script>

<style>
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
