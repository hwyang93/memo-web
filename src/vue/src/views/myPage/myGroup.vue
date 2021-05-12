<template>
  <section class="container space-type-1 blog_page">
    <h1 class="title-type-1">My Group</h1>
    <div class="row">
      <!--blog-sidebar-->
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
          <left-menu />
          <!--tags-sidebar-->
          <div class="blog-sidebar-widgets tags-blog-sidebar mb-0">
            <h3 class="widgets-title"><span>Tags</span></h3>
            <div class="blog-sidebar-widgets-inner">
              <div class="tagcloud">
                <a href="index.html" class="tag-cloud-link">Marketing</a>
                <a href="index.html" class="tag-cloud-link">Font</a>
                <a href="index.html" class="tag-cloud-link">Design</a>
                <a href="index.html" class="tag-cloud-link">Networking</a>
                <a href="index.html" class="tag-cloud-link">I Interior</a>
                <a href="index.html" class="tag-cloud-link">Seo</a>
                <a href="index.html" class="tag-cloud-link">theme</a>
                <a href="index.html" class="tag-cloud-link">Web design</a>
                <a href="index.html" class="tag-cloud-link">Post</a>
                <a href="index.html" class="tag-cloud-link">Developing</a>
                <a href="index.html" class="tag-cloud-link">Socail</a>
                <a href="index.html" class="tag-cloud-link">html</a>
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

                    <b-form @submit="onSubmit" @reset="onReset" v-if="form.show">
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

                      <label>멤버 추가</label>
                      <b-form-tags input-id="tags-basic" v-model="memberValue" :disableAddButton="true"></b-form-tags>

                      <div class="button-area mt-3">
                        <b-button type="submit" variant="primary">Submit</b-button>
                        <b-button type="reset" variant="danger">Reset</b-button>
                      </div>
                    </b-form>
                  </b-modal>
                <h3 class="title-type-2">참여중인 그룹</h3>
                <div class="group-list-area">
                  <ul class="group-list">
                    <li v-for="(item, index) in groupList" :key="index" @click="showModal(item)" class="group-item shadow-wrap-1" :class="[item.memberAuth == 'member' ? 'member':item.approvalStatus=='N' ? 'pending':'']">
                      <h4>{{item.groupTitle}}</h4>
                      <p>info : {{item.groupComment}}</p>
                    </li>
                  </ul>
                  <b-modal ref="group-modal" centered title="그룹 정보">
                    <ul class="user-list-area">
                      <h4>{{this.groupDetail.groupTitle}}</h4>
                      <p>info : <br>{{this.groupDetail.groupComment}}</p>
                      <label>참여 멤버</label>
                      <b-form-tags input-id="tags-basic" v-model="memberValue" :disableAddButton="true"></b-form-tags>
                      <b-button variant="danger mt-3">그룹 삭제</b-button>
                    </ul>
                  </b-modal>
                </div>
              </b-tab>

              <b-tab title="Friends">
                <h3 class="title-type-2">친구 관리</h3>
                <p>친구 찾기: </p>
                <b-form inline>
                  <div class="search-friend-area">
                    <b-form-input v-model="friend.keyword" id="inline-form-input-name search-input" class="mb-2 mr-sm-2 mb-sm-0" placeholder="enter user name" @keyup="openDrop" autocomplete="off"></b-form-input>
                    <!-- <b-button variant="primary" @click="getUserList">Search</b-button> -->
                    <div class="search-drop shadow-wrap-1" v-if="this.friend.flag">
                      <ul class="search-result-box">
                        <li v-for="(item, index) in userList" :key="index" class="search-result-list">
                          <div class="search-result">
                            <a href="#" class="user-box">
                              <div class="user-pic">
                                <img src="../../images/friends/user-sample.jpg" alt="" />
                              </div>
                              <span>{{item.userName}}</span>
                            </a>
                          </div>
                          <button class="btn btn-info" @click="requestFriend(item.userId)" id="btn-add-group">Add</button>
                        </li>
                      </ul>
                    </div>
                  </div>
                </b-form>

                <h3 class="title-type-2">친구 목록</h3>
                <ul class="user-list-area">
                  <li class="user-list" v-for="(item, index) in friendsList" :key="index">
                    <a href="#" class="user-pic">
                      <img src="../../images/friends/user-sample.jpg" alt="" />
                    </a>
                    <a href="#" class="user-name">{{item.followUserName}}
                    </a>
                    <span v-if="item.relationStatus === 'C'"><i class="fas fa-handshake"></i></span>
                    <span v-else v-b-tooltip.hover title="수락 대기 중 입니다."><i class="fas fa-handshake-slash"></i></span>
                    <button class="btn btn-danger">Delete</button>
                  </li>
                </ul>

                <h3 class="title-type-2">친구 요청</h3>


              </b-tab>
            </b-tabs>
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
      userList: [],
      friendsList: [],
      groupList: [],
      groupInfo: [],
      memberValue: [],
      form: {
        groupName: '',
        groupInfo: '',
        show: true
      },
      friend:{
        keyword : '',
        flag : false,
      },
      groupId:{
        groupIdx : ''
      },
      groupDetail: {
        groupTitle: '',
        groupComment: '',
        groupMembers: []
      },

    };
  },
  watch: {
    // 'friend.keyword': function(val) {
    //   if (val.length > 0) {
    //     this.getUserList();
    //   } else {
    //     this.userList = [];
    //   }
    // }
  },
  computed: {
    // classChange: function() {
    //   return {
    //     master: this.groupList.memberAuth === master,
    //     member: this.groupList.memberAuth === member,
    //   }
    // }
  },
  methods: {
    showModal(item) {
      console.log('item',item);
      this.groupDetail.groupTitle = item.groupTitle;
      this.groupDetail.groupComment = item.groupComment;
      console.log(this.groupDetail.groupTitle);
      this.$refs['group-modal'].show();
    },
    openDrop() {
      console.log("event")
      if (this.friend.keyword.length > 0) {
        this.getUserList();
      } else {
        this.userList = [];
        this.friend.flag = false;
      }
      console.log(this.userList);

    },
    // outFocus() {
    //   this.friend.flag = false;
    //   this.friend.keyword = '';
    // },
    getFriendsList() {
      const params =  {
        relationStatus : 'ALL'
      }
      axiosUtil.get('/api/myGroup/getFriendList.do', params, result => {
        console.log(result);
        this.friendsList = result.data.friendsList;
        console.log('friends list : ', this.friendsList)
      });
    },
    removeFriend() {

    },
    getUserList() {
      const params =  {
        keyword : this.friend.keyword
      }
      console.log(this.friend.keyword);
      axiosUtil.get('/api/myGroup/getUserList.do', params, result => {
        this.userList = result.data.userList;
        this.friend.flag = this.userList.length > 0;
        console.log(this.userList);
      });
    },
    requestFriend(userId) {
      const params = {
        followUserId : userId
      }
      axiosUtil.post('/api/myGroup/joinUserRelation.do', params, () => {
        alert('친구 신청이 완료 되었습니다.');
        this.getFriendsList()
      });
    },

    deleteGroup() {
      axiosUtil.post('/api/myGroup/deleteGroup.do', {}, result => {
        this.groupList = result.data.groupList;
        // console.log('group list : ', this.groupList);
      });
    },
    // getGroupInfo() {
    //   const params = {
    //     groupIdx : this.groupId.groupIdx
    //   }
    //   axiosUtil.get('/api/myGroup/getGroupInfo.do', {params}, result => {
    //     this.groupId = result.data.groupId;
    //     console.log(this.groupId);
    //   });
    // },
    getGroupList() {
      axiosUtil.get('/api/myGroup/getGroupList.do', {}, result => {
        this.groupList = result.data.groupList;
        console.log('group list : ', this.groupList);
      });
    },
    onSubmit(event) {
      event.preventDefault()

    },
    onReset(event) {
      event.preventDefault()
      this.form.groupName = ''
      this.form.groupInfo = ''
      this.form.show = false
      this.$nextTick(() => {
        this.form.show = true
      })
    },

  },
  beforeMount() {
    this.getGroupList();
    // this.getGroupInfo();
  },
  created() {
    this.getFriendsList()
  }
};
</script>

<style>

</style>