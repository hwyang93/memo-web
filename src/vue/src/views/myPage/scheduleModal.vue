<template>
  <div name="modal" @close-modal="isStatusOn = false">
    <div class="modal modal-overlay">
      <div class="modal-window">
        <div class="md-map md-container">
          <div id="map" class="map md-map-1"></div>
        </div>
        <div class="md-content">
          <div class="md-default" v-if="isdefault">
            <div class="md-header">{{ scheduleDetail.title }}</div>
            <div class="md-body">
              <h5 class="md-title">주소</h5>
              <p class="md-text">{{ scheduleDetail.promisePlace }}</p>
              <h5 class="md-title">일정</h5>
              <p class="md-text">{{ $moment(scheduleDetail.startDate).format('YYYY-MM-DD') + ' ~ ' + $moment(scheduleDetail.endDate).format('YYYY-MM-DD') }}</p>
              <h5 class="md-title">메모</h5>
              <p class="md-text" style="">{{ scheduleDetail.memo }}</p>
            </div>
          </div>
          <div class="md-modify" v-else-if="isModify">
            <input type="hidden" v-model="this.scheduleDetail.idx" />
            <div class="md-header"><input type="text" id="title" name="title" class="md-header" v-model="this.scheduleDetail.title" /></div>
            <div class="md-body">
              <h5 class="md-title">주소</h5>
              <input type="text" class="md-text" id="promisePlace" name="promisePlace" v-model="this.scheduleDetail.promisePlace" />
              <h5 class="md-title">일정</h5>
              시작 : <input type="date" class="md-text" id="startDate" name="startDate" :value="$moment(scheduleDetail.startDate).format('YYYY-MM-DD')" /><br />
              종료 : <input type="date" class="md-text" id="endDate" name="endDate" :value="$moment(scheduleDetail.endDate).format('YYYY-MM-DD')" />
              <h5 class="md-title">메모</h5>
              <textarea class="md-text" id="memo" name="memo" v-model="this.scheduleDetail.memo"></textarea>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <footer class="foo-default" v-if="isdefault">
            <button class="ModifyBtn md-btn" @click="modify">Modify</button>
            <button class="closeBtn md-btn" @click="$emit('close')">Close</button>
          </footer>
          <footer class="foo-modify" v-else-if="isModify">
            <button class="saveBtn md-btn" @click="save">Save</button>
            <button class="deleteBtn md-btn" @click="deleteBtn">Delete</button>
            <button class="cancelBtn md-btn" @click="cancel">Cancel</button>
          </footer>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axiosUtil from '@/utils/axios-util';

export default {
  name: 'scheduleModal',
  props: ['send'],
  data() {
    return {
      idx: null,
      map: null,
      clusterer: null,
      infowindow: null,
      isStatusOn: true,
      isdefault: false,
      isModify: false,
      backPromisePlace: null,
      backlon: '',
      backlat: '',
      scheduleDetail: {}
    };
  },
  watch: {
    localLat: function () {
      window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
    }
  },
  methods: {
    getScheduleOne(idx) {
      axiosUtil.get('/api/mySchedule/getScheduleDetail/' + idx, {}, result => {
        this.scheduleDetail = result.data.scheduleDetail;
        this.isdefault = true;
        this.backPromisePlace = this.scheduleDetail.promisePlace;
        this.backlat = this.scheduleDetail.lat;
        this.backlon = this.scheduleDetail.lon;
        this.initMap();
      });
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
    },
    modify: function () {
      this.isdefault = false;
      this.isModify = true;
    },
    save: function () {
      this.scheduleDetail.startDate = document.getElementById('startDate').value;
      this.scheduleDetail.endDate = document.getElementById('endDate').value;
      this.scheduleDetail.title = document.getElementById('title').value;
      this.scheduleDetail.promisePlace = document.getElementById('promisePlace').value;
      this.scheduleDetail.memo = document.getElementById('memo').value;
      axiosUtil.post('/api/mySchedule/updateSchedule', this.scheduleDetail, result => {
        alert('수정되었습니다.');
        this.isStatusOn = true;
        this.isdefault = true;
        this.isModify = false;
      });
    },
    deleteBtn() {
      axiosUtil.get('/api/mySchedule/deleteSchedule/' + this.scheduleDetail.idx, {}, result => {
        alert('삭제되었습니다.');
        this.isStatusOn = false;
        this.isdefault = true;
        this.isModify = false;
        window.location.reload();
      });
    },
    cancel: function () {
      this.isdefault = true;
      this.isModify = false;
      this.scheduleDetail.promisePlace = this.backPromisePlace;
      this.scheduleDetail.lon = this.backlon;
      this.scheduleDetail.lat = this.backlat;
      this.initMap();
    }
  },
  beforeMount() {
    this.idx = this.$props.send;
    this.getScheduleOne(this.idx);
  }
};
</script>

<style scoped>
.md-btn {
  margin-right: 10px;
}
.modal-window {
  height: 565px;
}
</style>