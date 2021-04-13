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
                <div class="form-group search_2">
                  <input type="text" class="form-control" placeholder="키워드 검색" id="s" />
                  <button type="button" class="btn"><i class="fas fa-search"></i></button>
                </div>
              </form>
            </div>
          </div>
          <!--widgets-cat-->
          <left-menu />
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
        <p style="float: left; width: 90%; border-bottom: 2px solid #00adb5; padding: 10px; margin-bottom: 30px">MyGroupList</p>
        <button type="button" class="btn" style="margin-top: 10px"><i class="fas fa-plus-square"></i></button>
        <div class="groupList" style="width: 100%">
          <div class="testGroup" @click="goDetail('/myPage/groupDetail/1')" style="width: 30%; height: 150px; margin-right: 20px; border: 1px solid #ccc; background: aliceblue; float: left">
            <div class="group">
              <figure>
                <h4 style="border-bottom: 1px solid #ccc; padding: 5px; text-align: center">그룹이름</h4>
                <p style="padding: 5px; text-align: center">info : 그룹정보</p>
              </figure>
            </div>
          </div>
          <div class="testGroup" @click="goDetail('/myPage/groupDetail/2')" style="width: 30%; height: 150px; margin-right: 20px; border: 1px solid #ccc; background: aliceblue; float: left">
            <div class="group">
              <figure>
                <h4 style="border-bottom: 1px solid #ccc; padding: 5px; text-align: center">그룹이름</h4>
                <p style="padding: 5px; text-align: center">info : 그룹정보</p>
              </figure>
            </div>
          </div>
          <div class="testGroup" @click="goDetail('/myPage/groupDetail/3')" style="width: 30%; height: 150px; border: 1px solid #ccc; background: aliceblue; float: left">
            <div class="group">
              <figure>
                <h4 style="border-bottom: 1px solid #ccc; padding: 5px; text-align: center">그룹이름</h4>
                <p style="padding: 5px; text-align: center">info : 그룹정보</p>
              </figure>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="blog_post col-lg-6 col-md-6" v-for="item in userScheduleList" :key="item.idx">
            <figure>
              <figcaption class="post_catipon text-left">
                <h3>{{ item.title }}</h3>
                <p>주소 <br />{{ item.promisePlace }}</p>
                <p>일정 <br />{{ item.startDate + ' ~ ' + item.endDate }}</p>
                <p>메모 <br />{{ item.memo }}</p>
              </figcaption>
            </figure>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import axiosUtil from '@/utils/axios-util';
import leftMenu from '@/views/myPage/leftMenu';
export default {
  name: 'group',
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
    },
    goDetail(url) {
      console.log(url);
      this.$router.push(url);
    }
  },
  beforeMount() {
    // this.getSchedule();
  }
};
</script>