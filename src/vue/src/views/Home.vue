<template>
  <div class="main">
    <main class="home-banner">
      <div class="container home-banner-container">
        <div class="row home-banner-row">
          <!-- kakao map -->
          <div class="col-lg-8 col-sm-12">
            <div class="content-center">
              <div id="map" class="map" style="width: 100%; height: 550px"></div>
            </div>
          </div>
          <!-- 위치 정보 입력 -->
          <div class="col-md-4">
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
                            <input v-model="form.promisePlace" type="text" class="form-control" placeholder="주소" readonly />
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
      form: {
        startDate: '2021-04-01',
        endDate: '2021-04-15',
        promisePlace: '',
        memo: '일정',
        lon:'',
        lat:'',
        latLng: {}
      },
      userScheduleList:[]
    };
  },
  watch: {
    localLat: function () {
      window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
    }
  },
  methods: {
    initMap() {
      var container = document.getElementById('map');
      var options = {
        center: new kakao.maps.LatLng(this.localLat, this.localLng),
        level: 3
      };

      // 주소-좌표 변환 객체를 생성합니다
      var geocoder = new kakao.maps.services.Geocoder();

      var map = new kakao.maps.Map(container, options);
      // 마커가 표시될 위치입니다
      var markerPosition = new kakao.maps.LatLng(this.localLat, this.localLng);

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        position: markerPosition
      });
      var infowindow = new kakao.maps.InfoWindow({ zindex: 1 }); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다
      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(map);

      // this.userScheduleList.forEach(item => {
      //   var pa = {
      //     La : item.lon,
      //     Ma : item.lat
      //   }
      //   marker.setPosition(pa);
      //   marker.setMap(map);
      // })

      // 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
      kakao.maps.event.addListener(map, 'click', mouseEvent => {
        console.log(mouseEvent.latLng);
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
            marker.setMap(map);

            // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
            infowindow.setContent(content);
            infowindow.open(map, marker);
          }
        });
      });
    },
    addScript() {
      const script = document.createElement('script');
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
      });
    },
    getSchedule() {
      axiosUtil.get('/api/main/getUserSchedule.do', {}, result => {
        this.userScheduleList = result.data.userScheduleList;
      });
    }
  },
  beforeMount() {
    this.getSchedule();

  },
  mounted() {
    this.getLocation();
    // if (navigator.geolocation) {
    //   navigator.geolocation.getCurrentPosition(this.showPosition);
    // }
    // window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
  }
};
</script>
