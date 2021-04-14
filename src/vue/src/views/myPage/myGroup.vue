<template>

  <section class="container space-type-1 blog_page">
    <h1 class="title-type-1">My Group</h1>
    <div class="row">
      <!--blog-sidbar-->
      <div class="col-lg-4 col-md-12 ">
        <div class="sidebar sidbar_left shadow-wrap-1">
          <!--search-->
          <div class="blog-sidebar-widgets">
            <h3 class="widgets-title">조회</h3>
            <div class="blog-sidebar-widgets-inner">
              <form class="searchform">
                <div class="form-group search_2 p-0">
                  <input type="text" class="form-control" placeholder="키워드 검색" id="s">
                  <button type="button" class="btn"><i class="fas fa-search"></i></button>
                </div>
              </form>
            </div>
          </div>
          <!--widgets-cat-->
          <left-menu />
          <!--tags-sidebar-->
          <div class="blog-sidebar-widgets tags-blog-sidebar mb-0">
            <h3 class="widgets-title"><span>  Tags </span></h3>
            <div class="blog-sidebar-widgets-inner">
              <div class="tagcloud">
                <a href="index.html" class="tag-cloud-link"> Marketing </a>
                <a href="index.html" class="tag-cloud-link"> Font  </a>
                <a href="index.html" class="tag-cloud-link"> Design </a>
                <a href="index.html" class="tag-cloud-link"> Networking </a>
                <a href="index.html" class="tag-cloud-link"> I Interior  </a>
                <a href="index.html" class="tag-cloud-link"> Seo </a>
                <a href="index.html" class="tag-cloud-link"> theme </a>
                <a href="index.html" class="tag-cloud-link">Web design </a>
                <a href="index.html" class="tag-cloud-link">  Post </a>
                <a href="index.html" class="tag-cloud-link">  Developing </a>
                <a href="index.html" class="tag-cloud-link"> Socail </a>
                <a href="index.html" class="tag-cloud-link"> html </a>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!--/sidebar-close-->
      <div class="col-lg-8 col-md-12 blog_page shadow-wrap-1">
        <!-- <button type="button" class="btn"><i class="fas fa-plus-square"></i></button>
        <div class="row">
          <div class="blog_post col-lg-6 col-md-6" v-for="item in userScheduleList" :key="item.idx">
            <figure>
              <figcaption class="post_catipon text-left">
                <h3>{{ item.title }}</h3>
                <p>주소 <br/> {{ item.promisePlace }}</p>
                <p>일정 <br/> {{ item.startDate + ' ~ ' + item.endDate }}</p>
                <p>메모 <br/>{{ item.memo }}</p>
              </figcaption>
            </figure>
          </div>
        </div> -->

        <!-- <div class="box-wrap"> -->

        <h2></h2>
        <div class="tab-area-gf">
          <b-tabs content-class="mt-3" fill>
            <b-tab title="Group" active>
              <h3 class="">그룹 관리</h3>
              <div class="group-area">
                <router-link :to="'/groupDetail/1'">
                  <div class="testGroup" style="width: 30%; height: 150px; margin-right: 20px; border: 1px solid #ccc; background: aliceblue;">
                    <div class="group">
                      <figure>
                        <h4 style="border-bottom: 1px solid #ccc; padding: 5px; text-align: center">그룹이름</h4>
                        <p style="padding: 5px; text-align: center">info : 그룹정보</p>
                      </figure>
                    </div>
                  </div>
                </router-link>
              </div>


              <h3 class="">그룹원 추가</h3>

              <div>
                <b-button v-b-modal.modal-center>멤버 추가 하기</b-button>

                <b-modal id="modal-center" centered title="그룹에 친구 추가">
                  <ul class="user-list-area">
                    <li class="user-list">
                      <a href="#" class="user-pic">
                        <img src="../../images/friends/user-sample.jpg" alt="">
                      </a>
                      <a href="#" class="user-name">charm_bbong</a>
                      <button class="btn btn-info" id="btn-add-group">Add</button>
                    </li>
                    <li class="user-list">
                      <a href="#" class="user-pic">
                        <img src="../../images/friends/user-sample.jpg" alt="">
                      </a>
                      <a href="#" class="user-name">charm_bbong</a>
                      <button class="btn btn-info" id="btn-add-group">Add</button>
                    </li>
                  </ul>
                  <!-- <p class="my-4">Vertically centered modal!</p> -->
                </b-modal>
              </div>
            </b-tab>
            <b-tab title="Friends">
              <h3></h3>
              <p>I'm the second tab</p>
            </b-tab>
          </b-tabs>

        </div>
        <!-- </div> -->

      </div>
    </div>
  </section>
</template>

<script>
import axiosUtil from "@/utils/axios-util";
import leftMenu from "@/views/myPage/leftMenu";
export default {
  name: "group",
  components: {
    leftMenu
  },
  data() {
    return {
      userScheduleList: []
    }
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
    // this.getSchedule();
  }
}
</script>

<style>
  .modal-backdrop {
    background-color: rgba(0,0,0,.3);
  }
</style>