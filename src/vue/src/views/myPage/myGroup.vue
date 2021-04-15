<template>
  <section class="container space-type-1 blog_page">
    <h1 class="title-type-1">My Group</h1>
    <div class="row">
      <!--blog-sidbar-->
      <div class="col-lg-4 col-md-12 ">
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
          <left-menu />
          <!--tags-sidebar-->
          <div class="blog-sidebar-widgets tags-blog-sidebar mb-0">
            <h3 class="widgets-title"><span>Tags</span></h3>
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
        <div class="my-group-content shadow-wrap-1">
          <h2></h2>
        <div class="tab-area-gf">
          <b-tabs content-class="mt-3" fill>
            <b-tab title="Group" active>
              <h3 class="title-type-2">그룹 관리</h3>
              <b-button v-b-modal.modal-center>그룹 만들기</b-button>
                <b-modal id="modal-center" centered title="그룹 만들기">

                  <b-form @submit="onSubmit" @reset="onReset" v-if="show">
                    <b-form-group
                      id="input-group-1"
                      label="Group Name:"
                      label-for="input-1"
                    >
                      <b-form-input
                        id="input-1"
                        v-model="form.groupName"
                        type="text"
                        placeholder="Group Name"
                        required
                      ></b-form-input>
                    </b-form-group>

                    <b-form-group id="input-group-2" label="Group Information:" label-for="input-2">
                      <b-form-input
                        id="input-2"
                        v-model="form.groupInfo"
                        placeholder="Enter Information"
                        required
                      ></b-form-input>
                    </b-form-group>

                    <b-form-group id="input-group-3" label="Food:" label-for="input-3">
                      <b-form-select
                        id="input-3"
                        v-model="form.food"
                        :options="foods"
                        required
                      ></b-form-select>
                    </b-form-group>

                    <b-form-group id="input-group-4" v-slot="{ ariaDescribedby }">
                      <b-form-checkbox-group
                        v-model="form.checked"
                        id="checkboxes-4"
                        :aria-describedby="ariaDescribedby"
                      >
                        <b-form-checkbox value="me">Check me out</b-form-checkbox>
                        <b-form-checkbox value="that">Check that out</b-form-checkbox>
                      </b-form-checkbox-group>
                    </b-form-group>

                    <b-button type="submit" variant="primary">Submit</b-button>
                    <b-button type="reset" variant="danger">Reset</b-button>
                  </b-form>

                  <ul class="user-list-area">
                    <li class="user-list">
                      <a href="#" class="user-pic">
                        <img src="../../images/friends/user-sample.jpg" alt="" />
                      </a>
                      <a href="#" class="user-name">charm_bbong</a>
                      <button class="btn btn-info" id="btn-add-group">Add</button>
                    </li>
                    <li class="user-list">
                      <a href="#" class="user-pic">
                        <img src="../../images/friends/user-sample.jpg" alt="" />
                      </a>
                      <a href="#" class="user-name">charm_bbong</a>
                      <button class="btn btn-info" id="btn-add-group">Add</button>
                    </li>
                  </ul>
                </b-modal>
              <div class="group-list-area">
                <router-link :to="'/myPage/group/groupDetail/'" class="shadow-wrap-1">
                  <div class="testGroup" style="">
                    <div class="group">
                      <h4 style="border-bottom: 1px solid #ccc; padding: 5px; text-align: center">그룹이름</h4>
                      <p style="padding: 5px; text-align: center">info : 그룹정보</p>
                    </div>
                  </div>
                </router-link>

                <router-link :to="'/myPage/group/groupDetail'" class="shadow-wrap-1">
                  <div class="testGroup" style="">
                    <div class="group">
                      <h4 style="border-bottom: 1px solid #ccc; padding: 5px; text-align: center">그룹이름</h4>
                      <p style="padding: 5px; text-align: center">info : 그룹정보</p>
                    </div>
                  </div>
                </router-link>

              </div>
              <h3 class="title-type-2">그룹원 추가</h3>
              <div class="add-member-area">
                <b-button v-b-modal.modal-center1>멤버 추가 하기</b-button>
                <b-modal id="modal-center1" centered title="그룹에 친구 추가">
                  <ul class="user-list-area">
                    <li class="user-list">
                      <a href="#" class="user-pic">
                        <img src="../../images/friends/user-sample.jpg" alt="" />
                      </a>
                      <a href="#" class="user-name">charm_bbong</a>
                      <button class="btn btn-info" id="btn-add-group">Add</button>
                    </li>
                    <li class="user-list">
                      <a href="#" class="user-pic">
                        <img src="../../images/friends/user-sample.jpg" alt="" />
                      </a>
                      <a href="#" class="user-name">charm_bbong</a>
                      <button class="btn btn-info" id="btn-add-group">Add</button>
                    </li>
                  </ul>
                </b-modal>
              </div>
            </b-tab>

            <b-tab title="Friends">
              <h3 class="title-type-2">친구 관리</h3>
              <p>I'm the second tab</p>
            </b-tab>
          </b-tabs>
        </div>
        </div>

        <!-- </div> -->
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
      userScheduleList: [],
      userList: [],
      form: {
        groupName: '',
        groupInfo: '',
        food: null,
        checked: []
      },
      foods: [{ text: 'Select One', value: null }, 'Carrots', 'Beans', 'Tomatoes', 'Corn'],
      show: true
    };
  },
  methods: {
    getSchedule() {
      axiosUtil.get('/api/mySchedule/getUserScheduleListAll.do', {}, result => {
        this.userScheduleList = result.data.userScheduleList;
        console.log(this.userScheduleList);
      });
    },
    getUserInfo() {
      var params =  {
        keyword : 'test'
      }
      axiosUtil.get('/api/myGroup/getUserList.do', {params}, result => {
        this.userList = result.data.userList;
        console.log(this.userList);
      });
    },
    onSubmit(event) {
      event.preventDefault()
      alert(JSON.stringify(this.form))
    },
    onReset(event) {
      event.preventDefault()
      // Reset our form values
      this.form.groupName = ''
      this.form.groupInfo = ''
      this.form.food = null
      this.form.checked = []
      // Trick to reset/clear native browser form validation state
      this.show = false
      this.$nextTick(() => {
        this.show = true
      })
    }

  },
  beforeMount() {
    // this.getSchedule();
    this.getUserInfo();
  }
};
</script>

<style>

</style>