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
<style>
.home-banner-row {
  height: 80vh;
  padding-top: 10px;
  padding-bottom: 10px;
}
.home-banner-row.left {
  margin-right: 3%;
  margin-left: 2%;
}
.title-back {
  padding: 5px 30px;
  width: 100%;
}
#title-icon {
  width: 30px;
  height: 30px;
  float: left;
  margin: 15px;
}
.title-name,
.title-left {
  padding: 15px;
}
.container {
  margin-bottom: 40px;
  width: 100%;
}
.userListBox {
  margin-top: 8px;
  width: 100%;
  height: 80%;
  overflow: scroll;
  overflow: auto;
  overflow-x: hidden;
  padding: 5px;
}
.userListBox::-webkit-scrollbar {
  width: 3px;
}
.userListBox::-webkit-scrollbar-thumb {
  width: 3px;
  background-color: #00adb5;
  border-radius: 10px;
}
.userListBox::-webkit-scrollbar-track {
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
  background-color: #ccc;
}
.userListBox {
  cursor: pointer;
}
.userListTbl,
.userInfoTbl {
  border: 1px solid #ccc;
  width: 100%;
  margin: auto;
}
.userListTbl thead {
  background: rgb(230, 230, 230);
  width: 100%;
  border-top: 1px solid #939597;
  border-bottom: 1px solid #939597;
}
.userListTbl tbody {
  width: 100%;
}
.userListTbl thead tr {
  text-align: center;
  font-weight: 600;
}
.userListTbl tbody tr {
  text-align: center;
  border-bottom: 1px solid #ccc;
}
.userListTbl th {
  padding: 0.1rem;
  font-weight: 200;
  border-left: 1px solid #ccc;
  border-right: 1px solid #ccc;
}
.userInfoTbl th {
  font-weight: 400;
  padding: 8px 0 8px 15px;
  background: rgb(230, 230, 230);
}
.userInfoTbl tr {
  border-bottom: 1px solid #ccc;
}
.userInfoTbl td {
  padding-left: 15px;
}
.delBtn {
  background: #eee;
  padding: 3px 8px;
  border-radius: 8px;
  border: 1px solid #ccc;
  cursor: pointer;
  float: right;
  margin: 15px 0;
}
</style>