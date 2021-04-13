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
          <div class="blog_post col-lg-6 col-md-6" v-for="item in userScheduleList" :key="item.idx" @click="showModal">
            <div class="card mb-3" style="height: 350px">
              <div class="card-header">{{ item.title }}</div>
              <div class="card-body">
                <h5 class="card-title">주소</h5>
                <p class="card-text">{{ item.promisePlace }}</p>
                <h5 class="card-title">일정</h5>
                <p class="card-text">{{ item.startDate + ' ~ ' + item.endDate }}</p>
                <h5 class="card-title">메모</h5>
                <p class="card-text">{{ item.memo }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="md-containar" id="modal" v-show="is_show">
        <div class="scheduleDetail">
          <div class="md-schedule">
            <div class="md-b">
              <div class="md-header"></div>
              <div class="md-body">
                <h5 class="md-title">주소</h5>
                <p class="md-text"></p>
                <h5 class="md-title">일정</h5>
                <p class="md-text"></p>
                <h5 class="md-title">메모</h5>
                <p class="md-text"></p>
              </div>
            </div>
            <div class="md-footer">
              <b-button>수정</b-button>
              <b-button>삭제</b-button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>
<style>
.md-containar {
  background-color: rgba(0, 0, 0, 0.3);
}
.scheduleDetail {
  width: 40%;
}
</style>


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
      userScheduleList: []
    };
  },
  methods: {
    getSchedule() {
      axiosUtil.get('/api/mySchedule/getUserScheduleListAll.do', {}, result => {
        this.userScheduleList = result.data.userScheduleList;
        console.log(this.userScheduleList);
      });
    }
  },
  beforeMount() {
    this.getSchedule();
  }
};
</script>