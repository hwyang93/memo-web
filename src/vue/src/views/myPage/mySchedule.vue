<template>
  <section class="container space blog_page">
    <div class="row">
      <!--blog-sidbar-->
      <div class="col-lg-4 col-md-12">
        <div class="sidebar sidebar_left shadow-wrap-1">
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
          <div class="blog_post col-lg-6 col-md-6 mb-3" v-for="item in userScheduleList" :key="item.idx">
            <div class="card shadow-wrap-1" style="height: 350px" @click="showModal(item.idx)">
              <div class="detail-card"><p id="dc-text">___ 상세보기</p></div>
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
      <schedule-modal v-if="isStatusOn" @close="isStatusOn = false" :send="this.params"> </schedule-modal>
    </div>
  </section>
</template>

<script>
import axiosUtil from '@/utils/axios-util';
import leftMenu from '@/views/myPage/leftMenu';
import scheduleModal from '@/views/myPage/scheduleModal.vue';

export default {
  name: 'schedule',
  components: {
    leftMenu,
    scheduleModal
  },
  data() {
    return {
      isStatusOn: false,
      userScheduleList: [],
      params: null
    };
  },
  methods: {
    getSchedule: function () {
      axiosUtil.get('/api/mySchedule/getUserScheduleListAll', {}, result => {
        this.userScheduleList = result.data.userScheduleList;
        console.log(this.userScheduleList);
      });
    },
    showModal(idx) {
      this.params = idx;
      this.isStatusOn = true;
    }
  },
  beforeMount() {
    this.getSchedule();
  }
};
</script>