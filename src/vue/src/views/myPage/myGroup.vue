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
                  <input id="s" class="form-control" placeholder="키워드 검색" type="text" />
                  <button class="btn" type="button"><i class="fas fa-search"></i></button>
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
                <a class="tag-cloud-link" href="index.html">Marketing</a>
                <a class="tag-cloud-link" href="index.html">Font</a>
                <a class="tag-cloud-link" href="index.html">Design</a>
                <a class="tag-cloud-link" href="index.html">Networking</a>
                <a class="tag-cloud-link" href="index.html">I Interior</a>
                <a class="tag-cloud-link" href="index.html">Seo</a>
                <a class="tag-cloud-link" href="index.html">theme</a>
                <a class="tag-cloud-link" href="index.html">Web design</a>
                <a class="tag-cloud-link" href="index.html">Post</a>
                <a class="tag-cloud-link" href="index.html">Developing</a>
                <a class="tag-cloud-link" href="index.html">Socail</a>
                <a class="tag-cloud-link" href="index.html">html</a>
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
              <b-tab active title="Group">
                <h3 class="title-type-2">그룹 관리</h3>
                <b-button v-b-modal.modal-center>그룹 만들기</b-button>
                <b-modal id="modal-center" centered title="그룹 만들기" hide-footer>
                  <b-form v-if="form.show" @reset="onReset" @submit="onSubmit">
                    <b-form-group id="input-group-1" label="Group Name:" label-for="input-1">
                      <b-form-input id="input-1" v-model="createGroupDetail.groupTitle" placeholder="Group Name" required type="text"></b-form-input>
                    </b-form-group>
                    <b-form-group id="input-group-2" label="Group Information:" label-for="input-2">
                      <b-form-input id="input-2" v-model="createGroupDetail.groupComment" placeholder="Enter Information" required></b-form-input>
                    </b-form-group>
                    <label>멤버 추가</label>
                    <div>
                      <ul class="user-list-area">
                        <li v-for="(item, index) in confirmFriendsList" :key="index" class="user-list">
                          <a class="d-flex align-items-center" href="#">
                            <div class="user-pic" href="#">
                              <img alt="" src="../../images/friends/user-sample.jpg" />
                            </div>
                            <div class="user-name" href="#">{{ item.followUserName }}</div>
                          </a>
                          <button class="btn btn-info" @click="addMember(item)">Add</button>
                        </li>
                      </ul>
                    </div>
                    <b-form-tags :disableAddButton="true" input-id="tags-basic">
                      <b-form-tag v-for="(item, idx) in createGroupDetail.groupMembers" @remove="removeFromList(item.followUserId)" :key="idx">
                        {{ item.followUserName }}
                      </b-form-tag>
                    </b-form-tags>
                    <div class="button-area mt-3">
                      <b-button @click="createGroup()" type="submit" variant="primary">만들기</b-button>
                      <b-button type="reset" variant="danger">닫기</b-button>
                    </div>
                  </b-form>
                </b-modal>
                <h3 class="title-type-2">참여중인 그룹</h3>
                <div class="group-list-area">
                  <ul class="group-list">
                    <li
                      v-for="(item, index) in groupList"
                      :key="index"
                      :class="[item.memberAuth === 'member' ? 'member' : item.approvalStatus === 'N' ? 'pending' : '']"
                      class="group-item shadow-wrap-1"
                      @click="showModal(item)"
                    >
                      <h4>{{ item.groupTitle }}</h4>
                      <p>info : {{ item.groupComment }}</p>
                    </li>
                  </ul>
                  <b-modal ref="group-modal" centered title="그룹 정보" hide-footer>
                    <ul class="user-list-area">
                      <h4>{{ this.groupDetail.groupTitle }}</h4>
                      <p>info : <br />{{ this.groupDetail.groupComment }}</p>
                      <label>참여 멤버</label>
                      <b-form-tags v-model="memberValue" :disableAddButton="true" input-id="tags-basic"></b-form-tags>
                      <b-button @click="deleteGroup(groupDetail.groupIdx)" variant="danger mt-3">그룹 삭제</b-button>
                    </ul>
                  </b-modal>
                </div>
              </b-tab>

              <b-tab title="Friends">
                <h3 class="title-type-2">친구 관리</h3>
                <p>친구 찾기:</p>
                <b-form inline>
                  <div class="search-friend-area">
                    <b-form-input
                      id="inline-form-input-name search-input"
                      v-model="friend.keyword"
                      autocomplete="off"
                      class="mb-2 mr-sm-2 mb-sm-0"
                      placeholder="enter user name"
                      @keyup="openDrop"
                    ></b-form-input>
                    <!-- <b-button variant="primary" @click="getUserList">Search</b-button> -->
                    <div v-if="this.friend.flag" class="search-drop shadow-wrap-1">
                      <ul class="search-result-box">
                        <li v-for="(item, index) in userList" :key="index" class="search-result-list">
                          <div class="search-result">
                            <a class="user-box" href="#">
                              <div class="user-pic">
                                <img alt="" src="../../images/friends/user-sample.jpg" />
                              </div>
                              <span>{{ item.userName }}</span>
                            </a>
                          </div>
                          <button type="button" id="btn-add-group" class="btn btn-info" @click="requestFriend(item.userId)">Add</button>
                        </li>
                      </ul>
                    </div>
                  </div>
                </b-form>

                <h3 class="title-type-2">친구 목록</h3>
                <ul class="user-list-area">
                  <li v-for="(item, index) in friendsList" :key="index" class="user-list">
                    <div class="d-flex align-items-center">
                      <span v-if="item.relationStatus === 'I'"><i class="fas fa-handshake"></i></span>
                      <span v-else><i class="fas fa-handshake-slash"></i></span>
                      <a class="user-pic" href="#">
                        <img alt="" src="../../images/friends/user-sample.jpg" />
                      </a>
                    </div>
                    <a class="user-name" href="#">{{ item.followUserName }} </a>
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
      confirmFriendsList: [],
      groupList: [],
      groupInfo: [],
      memberValue: [],
      form: {
        groupName: '',
        groupInfo: '',
        show: true
      },
      friend: {
        keyword: '',
        flag: false
      },
      groupId: {
        groupIdx: ''
      },
      groupDetail: {
        groupTitle: '',
        groupIdx: '',
        groupComment: '',
        groupMembers: []
      },
      createGroupDetail: {
        groupTitle: '',
        groupIdx: '',
        groupComment: '',
        groupMembers: []
      }
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
    // showUserName: function() {
    //   let test = [];
    //   this.createGroupDetail.groupMembers.map(item=>{
    //     test.push(item.followUserName);
    //   })
    //   return test;
    // }
  },
  methods: {
    showModal(item) {
      console.log('item', item);
      this.groupDetail.groupTitle = item.groupTitle;
      this.groupDetail.groupComment = item.groupComment;
      this.groupDetail.groupIdx = item.groupIdx;
      console.log('모달열릴때', this.groupDetail.groupTitle);
      this.getGroupInfo(item.groupIdx);
      this.$refs['group-modal'].show();
    },
    openDrop() {
      console.log('event');
      if (this.friend.keyword.length > 0) {
        this.getUserList();
      } else {
        this.userList = [];
        this.friend.flag = false;
      }
      console.log(this.userList);
    },
    addMember(item) {
      this.createGroupDetail.groupMembers.push(item);
    },
    removeFromList(idx) {
      this.createGroupDetail.groupMembers.forEach((item, index) => {
        if (item.followUserId === idx) {
          this.createGroupDetail.groupMembers.splice(index, 1);
        }
      });
    },
    getFriendsList() {
      const params = {
        relationStatus: 'ALL'
      };
      axiosUtil.get('/api/myGroup/getFriendList.do', params, result => {
        console.log(result);
        this.friendsList = result.data.friendsList;
        console.log('friends list : ', this.friendsList);
      });
    },
    getConfirmFriendsList() {
      const params = {
        relationStatus: 'I'
      };
      axiosUtil.get('/api/myGroup/getFriendList.do', params, result => {
        this.confirmFriendsList = result.data.friendsList;
        console.log(result);
      });
    },
    removeFriend() {},
    getUserList() {
      const params = {
        keyword: this.friend.keyword
      };
      console.log(this.friend.keyword);
      axiosUtil.get('/api/myGroup/getUserList.do', params, result => {
        this.userList = result.data.userList;
        this.friend.flag = this.userList.length > 0;
        console.log(this.userList);
      });
    },
    requestFriend(userId) {
      const params = {
        followUserId: userId
      };
      axiosUtil.post('/api/myGroup/joinUserRelation.do', params, () => {
        alert('친구 신청이 완료 되었습니다.');
        this.getFriendsList();
      });
    },
    deleteGroup(groupIdx) {
      const params = {
        groupIdx: groupIdx
      };
      axiosUtil.post('/api/myGroup/deleteGroup.do', params, () => {
        // this.groupList = result.data.groupList;
        // console.log('group list : ', this.groupList);
        alert('삭제 되었습니다');
        this.getGroupList();
      });
    },
    getGroupInfo(groupIdx) {
      const params = {
        groupIdx: groupIdx
      };
      axiosUtil.get('/api/myGroup/getGroupInfo.do', params, result => {
        this.groupId = result.data.groupId;
        console.log(result);
        console.log(this.groupId);
      });
    },
    createGroup() {
      let groupMembers = [];
      let groupMember = {};
      this.createGroupDetail.groupMembers.map(item => {
        groupMember.groupUser = item.followUserId;
        groupMembers.push(groupMember);
      });
      const params = {
        groupTitle: this.createGroupDetail.groupTitle,
        groupMembers: groupMembers
        // group_member: this.createGroupDetail.groupMembers
      };
      axiosUtil.post('/api/myGroup/createGroup.do', params, () => {
        // this.groupList = result.data.groupList;
        alert('저장되었습니다.');
      });
    },
    getGroupList() {
      axiosUtil.get('/api/myGroup/getGroupList.do', {}, result => {
        this.groupList = result.data.groupList;
        console.log('group list : ', this.groupList);
      });
    },
    onSubmit(event) {
      event.preventDefault();
    },
    onReset(event) {
      event.preventDefault();
      this.form.groupName = '';
      this.form.groupInfo = '';
      this.form.show = false;
      this.$nextTick(() => {
        this.form.show = true;
      });
    }
  },
  beforeMount() {
    this.getGroupList();
    // this.getGroupInfo();
  },
  created() {
    this.getFriendsList();
    this.getConfirmFriendsList();
  //  test
  }
};
</script>

<style>
.user-list span {
  margin-right: 10px;
}
</style>
