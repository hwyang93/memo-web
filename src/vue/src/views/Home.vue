<template>
  <div class="main">
    <main class="home-banner">
      <div class="container home-banner-container space-type-1">
        <div class="row home-banner-row shadow-wrap-1">
          <!-- kakao map -->
          <div class="col-lg-8 col-sm-12">
            <kakao-map :scheduleList="scheduleList" @setMarkerInfo="setMarkerInfo" />
          </div>
          <!-- 위치 정보 입력 -->
          <div class="col-lg-4">
            <div class="content-center">
              <div class="row">
                <div class="form-group col-md-12">
                  <b-tabs content-class="mt-3" v-model="tabIdx" fill>
                    <!-- 개인일정 TAB start -->
                    <b-tab title="개인일정">
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
                                <input v-model="form.startDate" type="date" class="form-control" />
                              </div>
                            </div>
                            <div class="form-group col-lg-12">
                              <div class="input-group">
                                <input v-model="form.endDate" type="date" class="form-control" />
                              </div>
                            </div>
                          </div>
                          <div class="form-group">
                            <div class="input-group">
                              <textarea v-model="form.memo" class="form-control" placeholder="메모" style="resize: none"></textarea>
                            </div>
                          </div>
                        </div>
                      </div>
                    </b-tab>
                    <!-- 개인일정 TAB end -->
                    <!-- 그룹일정 TAB start -->
                    <b-tab title="그룹일정">
                      <div class="contact-section comment_form">
                        <div class="contactus_form">
                          <h3 class="comment_title"><span></span></h3>
                          <div class="row">
                            <div class="form-group col-lg-12">
                              <b-form-select v-model="form.groupIdx" :options="userGroupList" value-field="groupIdx" text-field="groupTitle"></b-form-select>
                            </div>
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
                                <input v-model="form.startDate" type="date" class="form-control" placeholder="First Name" />
                              </div>
                            </div>
                            <div class="form-group col-lg-12">
                              <div class="input-group">
                                <input v-model="form.endDate" type="date" class="form-control" placeholder="Last Name" />
                              </div>
                            </div>
                          </div>
                          <div class="form-group">
                            <div class="input-group">
                              <textarea v-model="form.memo" class="form-control" placeholder="메모" style="resize: none"></textarea>
                            </div>
                          </div>
                        </div>
                      </div>
                    </b-tab>
                    <!-- 그룹일정 TAB end -->
                    <!-- memo TAB start -->
                    <b-tab title="memo">
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
                                <input v-model="form.memoPlace" type="text" class="form-control" placeholder="위치" readonly />
                              </div>
                            </div>
                          </div>
                          <div class="form-group">
                            <div class="input-group">
                              <textarea v-model="form.memo" class="form-control" placeholder="메모" style="resize: none"></textarea>
                            </div>
                          </div>
                        </div>
                      </div>
                    </b-tab>
                    <!-- memo TAB end -->
                  </b-tabs>
                  <div class="comment_submit_btn text-right">
                    <b-button variant="outline-primary" @click="onSave()">일정 저장</b-button>
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
import KakaoMap from '../components/KakaoMap';

export default {
  name: 'Main',
  components: { KakaoMap },
  data() {
    return {
      tabIdx: 0,
      localLat: 0,
      localLng: 0,
      map: null,
      clusterer: null,
      infowindow: null,
      ps: null,
      form: {
        groupIdx: '',
        title: '제목입력',
        startDate: '2021-04-01',
        endDate: '2021-04-30',
        promisePlace: '',
        memoPlace: '',
        memo: '일정',
        lon: '',
        lat: '',
        latLng: {}
      },
      scheduleList: [],
      userGroupList: [],
      userScheduleList: [],
      groupScheduleList: [],
      userMemoList: []
    };
  },
  watch: {
    tabIdx: function (val) {
      if (val === 0) {
        this.scheduleList = this.userScheduleList;
      } else if (val === 1) {
        this.scheduleList = this.groupScheduleList;
      } else {
        this.scheduleList = this.userMemoList;
      }
    }
  },
  methods: {
    onSave() {
      if (this.tabIdx === 0) {
        this.saveUserSchedule();
      } else if (this.tabIdx === 1) {
        this.saveGroupSchedule();
      } else if (this.tabIdx === 2) {
        this.saveMemo();
      }
    },
    saveUserSchedule() {
      axiosUtil.post('/api/main/saveUserSchedule.do', this.form, () => {
        alert('저장되었습니다.');
        this.getSchedule();
      });
    },
    saveGroupSchedule() {
      axiosUtil.post('/api/main/saveGroupSchedule.do', this.form, () => {
        alert('저장되었습니다.');
        this.getSchedule();
      });
    },
    saveMemo() {
      axiosUtil.post('/api/main/memo', this.form, () => {
        alert('저장되었습니다.');
        this.getSchedule();
      });
    },
    getSchedule() {
      axiosUtil.get('/api/main/schedule', {}, result => {
        this.userScheduleList = result.data.userScheduleList;
        this.groupScheduleList = result.data.groupScheduleList;
        this.userMemoList = result.data.userMemoList;
        if (this.tabIdx === 0) {
          this.scheduleList = this.userScheduleList;
        } else if (this.tabIdx === 1) {
          this.scheduleList = this.groupScheduleList;
        } else {
          this.scheduleList = this.userMemoList;
        }
      });
      // axiosUtil.get('/api/main/getSchedule.do', {}, result => {
      //   this.userScheduleList = result.data.userScheduleList;
      //   this.groupScheduleList = result.data.groupScheduleList;
      //   if (this.tabIdx === 0) {
      //     this.scheduleList = this.userScheduleList;
      //   } else if (this.tabIdx === 1) {
      //     this.scheduleList = this.groupScheduleList;
      //   } else {
      //     this.scheduleList = this.memoList;
      //   }
      // });
    },
    setMarkerInfo(data) {
      if (this.tabIdx === 2) {
        this.form.memoPlace = !!data.road_address_name ? data.road_address_name : data.address_name;
      } else {
        this.form.promisePlace = !!data.road_address_name ? data.road_address_name : data.address_name;
      }
      this.form.lon = data.x;
      this.form.lat = data.y;
    }
  },
  beforeMount() {
    axiosUtil.get('/api/myGroup/getGroupList.do', {}, result => {
      this.userGroupList = result.data.groupList;
    });
  },
  mounted() {
    this.$nextTick(() => {
      this.getSchedule();
    });
  }
};
</script>

<style>
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;
  font-size: 12px;
}

.map_wrap a,
.map_wrap a:hover,
.map_wrap a:active {
  color: #000;
  text-decoration: none;
}

.map_wrap {
  position: relative;
  width: 100%;
  height: 500px;
}

#menu_wrap {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 250px;
  margin: 10px 0 30px 10px;
  padding: 5px;
  overflow-y: auto;
  background: rgba(255, 255, 255, 0.7);
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;
}

.bg_white {
  background: #fff;
}

#menu_wrap hr {
  display: block;
  height: 1px;
  border: 0;
  border-top: 2px solid #5f5f5f;
  margin: 3px 0;
}

#menu_wrap .option {
  text-align: center;
}

#menu_wrap .option p {
  margin: 10px 0;
}

#menu_wrap .option button {
  margin-left: 5px;
}

#placesList li {
  list-style: none;
}

#placesList .item {
  position: relative;
  border-bottom: 1px solid #888;
  overflow: hidden;
  cursor: pointer;
  min-height: 65px;
}

#placesList .item span {
  display: block;
  margin-top: 4px;
}

#placesList .item h5,
#placesList .item .info {
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}

#placesList .item .info {
  padding: 10px 0 10px 55px;
}

#placesList .info .gray {
  color: #8a8a8a;
}

#placesList .info .jibun {
  padding-left: 26px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;
}

#placesList .info .tel {
  color: #009900;
}

#placesList .item .markerbg {
  float: left;
  position: absolute;
  width: 36px;
  height: 37px;
  margin: 10px 0 0 10px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;
}

#placesList .item .marker_1 {
  background-position: 0 -10px;
}

#placesList .item .marker_2 {
  background-position: 0 -56px;
}

#placesList .item .marker_3 {
  background-position: 0 -102px;
}

#placesList .item .marker_4 {
  background-position: 0 -148px;
}

#placesList .item .marker_5 {
  background-position: 0 -194px;
}

#placesList .item .marker_6 {
  background-position: 0 -240px;
}

#placesList .item .marker_7 {
  background-position: 0 -286px;
}

#placesList .item .marker_8 {
  background-position: 0 -332px;
}

#placesList .item .marker_9 {
  background-position: 0 -378px;
}

#placesList .item .marker_10 {
  background-position: 0 -423px;
}

#placesList .item .marker_11 {
  background-position: 0 -470px;
}

#placesList .item .marker_12 {
  background-position: 0 -516px;
}

#placesList .item .marker_13 {
  background-position: 0 -562px;
}

#placesList .item .marker_14 {
  background-position: 0 -608px;
}

#placesList .item .marker_15 {
  background-position: 0 -654px;
}

#pagination {
  margin: 10px auto;
  text-align: center;
}

#pagination a {
  display: inline-block;
  margin-right: 10px;
}

#pagination .on {
  font-weight: bold;
  cursor: default;
  color: #777;
}
</style>
