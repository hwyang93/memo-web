<template>
  <section class="container space blog_page">
    <div class="row">
      <!--blog-sidbar-->
      <div class="col-lg-4 col-md-12">
        <div class="sidebar sidbar_left bg-gray">
          <!--search-->
          <div class="blog-sidebar-widgets">
            <h3 class="widgets-title">조회</h3>
            <div class="blog-sidebar-widgets-inner">
              <form class="searchform">
                <div class="form-group search_2 p-0">
                  <input type="text" class="form-control" placeholder="키워드 검색" id="s" />
                  <button type="button" class="btn"><i class="fas fa-search"></i></button>
                </div>
              </form>
            </div>
          </div>
          <!--widgets-cat-->
          <left-menu></left-menu>
          <!--tags-sidebar-->
          <div class="blog-sidebar-widgets tags-blog-sidebar mb-0">
            <h3 class="widgets-title"><span> Tags </span></h3>
            <div class="blog-sidebar-widgets-inner">
              <div class="tagcloud">
                <a href="index.html" class="tag-cloud-link"> Marketing </a>
                <a href="index.html" class="tag-cloud-link"> Font </a>
                <a href="index.html" class="tag-cloud-link"> Design </a>
                <a href="index.html" class="tag-cloud-link"> Networking </a>
                <a href="index.html" class="tag-cloud-link"> I Interior </a>
                <a href="index.html" class="tag-cloud-link"> Seo </a>
                <a href="index.html" class="tag-cloud-link"> theme </a>
                <a href="index.html" class="tag-cloud-link">Web design </a>
                <a href="index.html" class="tag-cloud-link"> Post </a>
                <a href="index.html" class="tag-cloud-link"> Developing </a>
                <a href="index.html" class="tag-cloud-link"> Socail </a>
                <a href="index.html" class="tag-cloud-link"> html </a>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!--/sidebar-close-->
      <div class="col-lg-8 col-md-12 blog_page">
        <div class="row">
          <div class="blog_post col-lg-6 col-md-6" v-for="item in userScheduleList" :key="item.idx">
            <div class="card mb-3" style="height: 350px" @click="showModal(item.idx)">
              <div class="detail-card">
                <p id="dc-text">___ Detail</p>
              </div>
              <div class="card-header">{{ item.title }}</div>
              <div class="card-body">
                <h5 class="card-title">주소</h5>
                <p class="card-text" aria-readonly>{{ item.promisePlace }}</p>
                <h5 class="card-title">일정</h5>
                <p class="card-text">{{ $moment(item.startDate).format('YYYY-MM-DD') + ' ~ ' + $moment(item.endDate).format('YYYY-MM-DD') }}</p>
                <h5 class="card-title">메모</h5>
                <p class="card-text">{{ item.memo }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <transition name="modal" v-if="isStatusOn">
        <div class="modal modal-overlay">
          <div class="modal-window">
            <div class="md-map md-container">
              <div id="map" class="map md-map-1"></div>
            </div>
            <div class="md-content md-default" v-if="isdefault">
              <div class="md-header">{{ scheduleDetail.title }}</div>
              <div class="md-body">
                <h5 class="md-title">주소</h5>
                <p class="md-text">{{ scheduleDetail.promisePlace }}</p>
                <h5 class="md-title">일정</h5>
                <p class="md-text">{{ $moment(scheduleDetail.startDate).format('YYYY-MM-DD') + ' ~ ' + $moment(scheduleDetail.endDate).format('YYYY-MM-DD') }}</p>
                <h5 class="md-title">메모</h5>
                <p class="md-text">{{ scheduleDetail.memo }}</p>
              </div>
            </div>
            <div class="md-content md-modify" v-else-if="isModify">
              <input type="hidden" v-model="this.scheduleDetail.idx" />
              <div class="md-header"><input type="text" class="md-header" v-model="this.scheduleDetail.title" /></div>
              <div class="md-body">
                <h5 class="md-title">주소</h5>
                <input type="text" class="md-text" v-model="this.scheduleDetail.promisePlace" />
                <h5 class="md-title">일정</h5>
                시작 : <input type="date" class="md-text" id="startDate" :value="$moment(scheduleDetail.startDate).format('YYYY-MM-DD')" /><br />
                종료 : <input type="date" class="md-text" id="endDate" :value="$moment(scheduleDetail.endDate).format('YYYY-MM-DD')" />
                <h5 class="md-title">메모</h5>
                <textarea class="md-text" v-model="this.scheduleDetail.memo"></textarea>
              </div>
            </div>
            <footer class="modal-footer foo-default" v-if="isdefault">
              <slot name="footer">
                <button class="ModifyBtn md-btn" @click="modify">Modify</button>
                <button class="closeBtn md-btn" @click="close">Close</button>
              </slot>
            </footer>
            <footer class="modal-footer foo-modify" v-else-if="isModify">
              <button class="saveBtn md-btn" @click="save">Save</button>
              <button class="deleteBtn md-btn" @click="deleteBtn">Delete</button>
              <button class="cancelBtn md-btn" @click="cancel">Cancel</button>
            </footer>
          </div>
        </div>
      </transition>
    </div>
  </section>
</template>

<script>
import axiosUtil from '@/utils/axios-util';
import leftMenu from '@/views/myPage/leftMenu';

export default {
  name: 'schedule',
  components: {
    leftMenu
  },
  data() {
    return {
      localLat: 0,
      localLng: 0,
      map: null,
      clusterer: null,
      infowindow: null,
      isStatusOn: false,
      isdefault: true,
      isModify: false,
      backPromisePlace: null,
      backlat: '',
      backlon: '',
      userScheduleList: [],
      scheduleDetail: {}
    };
  },
  watch: {
    localLat: function () {
      window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
    }
  },
  methods: {
    getSchedule: function () {
      axiosUtil.get('/api/mySchedule/getUserScheduleListAll.do', {}, result => {
        this.userScheduleList = result.data.userScheduleList;
      });
    },
    showModal(idx) {
      axiosUtil.get('/api/mySchedule/getScheduleDetail.do', { params: { idx: idx } }, result => {
        this.scheduleDetail = result.data.scheduleDetail;
        this.backPromisePlace = this.scheduleDetail.promisePlace;
        this.getLocation();
        this.initMap();
        this.backlat = this.scheduleDetail.lat;
        this.backlon = this.scheduleDetail.lon;
      });

      this.isStatusOn = true;
    },
    initMap() {
      const container = document.getElementById('map');
      const options = {
        center: new kakao.maps.LatLng(this.scheduleDetail.lat, this.scheduleDetail.lon),
        level: 3
      };

      this.map = new kakao.maps.Map(container, options);
      // 마커가 표시될 위치입니다
      const markerPosition = new kakao.maps.LatLng(this.scheduleDetail.lat, this.scheduleDetail.lon);
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
        if (this.isdefault == false) {
          this.scheduleDetail.latLng = mouseEvent.latLng;
          geocoder.coord2Address(mouseEvent.latLng.La, mouseEvent.latLng.Ma, (result, status) => {
            if (status === kakao.maps.services.Status.OK) {
              var detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
              detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';

              var content = '<div class="bAddr">' + '<span class="title">법정동 주소정보</span>' + detailAddr + '</div>';
              this.scheduleDetail.promisePlace = !!result[0].road_address ? result[0].road_address.address_name : result[0].address.address_name;
              this.scheduleDetail.lon = mouseEvent.latLng.La;
              this.scheduleDetail.lat = mouseEvent.latLng.Ma;
              // 마커를 클릭한 위치에 표시합니다
              marker.setPosition(mouseEvent.latLng);
              marker.setMap(this.map);

              // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
              this.infowindow.setContent(content);
              this.infowindow.open(this.map, marker);
            }
          });
        }
      });
      if (this.scheduleDetail.length > 0) {
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
    modify: function () {
      this.isdefault = false;
      this.isModify = true;
    },
    save: function () {
      this.scheduleDetail.startDate = document.getElementById('startDate').value;
      this.scheduleDetail.endDate = document.getElementById('endDate').value;
      axiosUtil.post('/api/mySchedule/updateSchedule.do', this.scheduleDetail, result => {
        alert('수정되었습니다.');
        this.isStatusOn = false;
        this.isdefault = true;
        this.isModify = false;
        this.getSchedule();
      });
    },
    deleteBtn() {
      axiosUtil.get('/api/mySchedule/deleteSchedule.do', { params: { idx: this.scheduleDetail.idx } }, result => {
        alert('삭제되었습니다.');
        this.isStatusOn = false;
        this.isdefault = true;
        this.isModify = false;
        this.getSchedule();
      });
    },
    cancel: function () {
      this.isdefault = true;
      this.isModify = false;
      this.scheduleDetail.promisePlace = this.backPromisePlace;
      this.scheduleDetail.lat = this.backlat;
      this.scheduleDetail.lon = this.backlon;
      this.initMap();
    },
    close: function () {
      this.isStatusOn = false;
    },
    setMarkers() {
      let markers = this.scheduleDetail.map(item => {
        return new kakao.maps.Marker({
          position: new kakao.maps.LatLng(item.lat, item.lon)
        });
      });
      this.clusterer.addMarkers(markers);

      markers.map((marker, index) => {
        let content = '<div>장소 : ' + this.scheduleDetail.promisePlace + '</div>';
        content += '<div>일정 : ' + this.scheduleDetail.startDate + ' ~ ' + this.scheduleDetail.endDate + '</div>';
        content += '<div>일정 : ' + this.scheduleDetail.memo + '</div>';
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
    }
  },
  beforeMount() {
    this.getSchedule();
  }
};
</script>