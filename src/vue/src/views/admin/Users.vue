<template>
  <div class="wrapper-admin">
    <side-menu> </side-menu>
    <div class="main-content">
      <admin-top-menu :send="this.pageName"></admin-top-menu>
      <div class="container home-banner-container space-type-1">
        <div class="row">
          <div class="col-lg-3 home-banner-row left shadow-wrap-1">
            <img src="../../images/dashboard/user.png" id="title-icon" alt="userIcon" />
            <h4 class="title-left">User List</h4>
            <div class="userListBox">
              <table class="userListTbl">
                <colgroup>
                  <col width="20%" />
                  <col width="80%" />
                </colgroup>
                <thead>
                  <tr>
                    <th>No</th>
                    <th scope="col">USER ID</th>
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
          <div class="col-lg-8 home-banner-row right shadow-wrap-1">
            <div class="title-back">
              <img src="../../images/dashboard/user.png" id="title-icon" alt="userIcon" />
              <h3 class="title-name">회원정보</h3>
              <div class="userInfoBox">
                <table class="userInfoTbl">
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
                  </tbody>
                </table>
                <div>
                  <span class="delBtn" @click="delUser(this.userInfo.userId)">Delete</span>
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
      pageName: 'Users',
      index: 1
    };
  },
  created() {
    axiosUtil.get('/api/admin/userList', {}, result => {
      this.userList = result.data.userList;
      console.log(this.userList[0].userId);
      axiosUtil.get('/api/admin/userInfo/' + this.userList[0].userId, {}, result => {
        this.userInfo = result.data.userInfo;
      });
    });
  },
  methods: {
    detail(id) {
      axiosUtil.get('/api/admin/userInfo/' + id, {}, result => {
        this.userInfo = result.data.userInfo;
      });
    },
    delUser: function (id) {
      axiosUtil.get('/api/admin/deleteUser/' + id, {}, result => {
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