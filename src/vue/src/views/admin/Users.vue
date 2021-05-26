<template>
  <div class="wrapper-admin">
    <side-menu> </side-menu>
    <div class="main-content">
      <admin-top-menu :send="this.pageName"></admin-top-menu>
      <div class="container home-banner-container space-type-1">
        <div class="row">
          <div class="col-lg-4">
            <div class="home-banner-row left shadow-wrap-1">
              <img src="../../images/dashboard/user.png" id="title-icon" alt="userIcon" />
              <h4 class="title-left">User List</h4>
              <div class="left-listBox listBox">
                <table class="listTbl">
                  <colgroup>
                    <col width="20%" />
                    <col width="80%" />
                  </colgroup>
                  <thead>
                    <tr>
                      <th>No</th>
                      <th scope="col">user Id</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr v-for="(user, index) in userList" :key="index">
                      <th>{{ index + 1 }}</th>
                      <th scope="col" @click="detail(user.userId)">{{ user.userId }}</th>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
          <div class="col-lg-8">
            <div class="home-banner-row right shadow-wrap-1">
              <div class="title-back">
                <img src="../../images/dashboard/user.png" id="title-icon" alt="userIcon" />
                <h3 class="title-name">회원정보</h3>
                <div class="infoBox">
                  <table class="infoTbl">
                    <colgroup>
                      <col width="20%" />
                      <col width="30%" />
                      <col width="20%" />
                      <col width="30%" />
                    </colgroup>
                    <tbody>
                      <tr scope="row">
                        <th scope="col">Id</th>
                        <td>{{ this.userInfo.userId }}</td>
                        <th scope="col">Register Date</th>
                        <td>{{ $moment(this.userInfo.regDate).format('YYYY-MM-DD') }}</td>
                      </tr>
                      <tr scope="row">
                        <th scope="col">Name</th>
                        <td colspan="3">{{ this.userInfo.userName }}</td>
                      </tr>
                      <tr scope="row">
                        <th scope="col">Password</th>
                        <td colspan="3">{{ this.userInfo.userPassword }}</td>
                      </tr>
                      <tr scope="row">
                        <th scope="col">Email</th>
                        <td colspan="3">{{ this.userInfo.userEmail }}</td>
                      </tr>
                      <tr scope="row" v-if="uPostCnt.length == 0">
                        <th scope="col">Post</th>
                        <td colspan="3">0 개</td>
                      </tr>
                      <tr scope="row" v-else>
                        <th scope="col">Post</th>
                        <td colspan="3">{{ this.uPostCnt }} 개</td>
                      </tr>
                      <tr scope="row" v-if="uGroupCnt.length == 0">
                        <th scope="col">Group</th>
                        <td colspan="3">0 개</td>
                      </tr>
                      <tr scope="row" v-else>
                        <th scope="col">Group</th>
                        <td colspan="3">
                          {{ this.uGroupCnt.length }} 개
                          <span id="showGroupName" v-if="close" @click="groupToggle()">그룹 보기 ▼</span>
                          <span id="showGroupName" v-else-if="open" @click="groupToggle()">닫기 ▲</span>
                          <div class="groupBox" v-if="open">
                            <div class="gitem" v-for="gt in groupTitle" :key="gt.index">
                              {{ gt }}
                            </div>
                          </div>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                  <div>
                    <span class="delBtn" @click="delUser()">Delete</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axiosUtil from '@/utils/axios-util';
import SideMenu from '@/components/sideMenu.vue';
import AdminTopMenu from '@/components/adminTopMenu.vue';

export default {
  name: 'Users',
  components: {
    SideMenu,
    AdminTopMenu
  },
  data() {
    return {
      userList: [],
      userInfo: {},
      uGroupCnt: [],
      uPostCnt: 0,
      pageName: 'Users',
      index: 1,
      close: true,
      open: false,
      groupTitle: []
    };
  },
  created() {
    axiosUtil.get('/api/admin/userList', {}, result => {
      this.userList = result.data.userList;
      axiosUtil.get('/api/admin/userInfo/' + this.userList[0].userId, {}, result => {
        this.userInfo = result.data.userInfo;
        console.log(this.userInfo);
        this.groupTitle = Object.keys(result.data.uGroupCnt);
        this.uGroupCnt = Object.values(result.data.uGroupCnt);
        this.uPostCnt = result.data.uPostCnt;
      });
    });
  },
  methods: {
    detail(id) {
      if (this.open == true) {
        this.open = false;
        this.close = true;
      }
      axiosUtil.get('/api/admin/userInfo/' + id, {}, result => {
        this.userInfo = result.data.userInfo;
        this.groupTitle = Object.keys(result.data.uGroupCnt);
        this.uGroupCnt = Object.values(result.data.uGroupCnt);
        this.uPostCnt = result.data.uPostCnt;
      });
    },
    groupToggle() {
      this.close = !this.close;
      this.open = !this.open;
    },
    delUser() {
      axiosUtil.get('/api/admin/deleteUser/' + this.userInfo.userId, {}, result => {
        if (result.data.result == 1) {
          alert('삭제되었습니다.');
          location.reload();
        } else {
          alert('오류');
        }
      });
    }
  }
};
</script>
<style lang="css">
@import '../../css/adminStyle.css';
</style>