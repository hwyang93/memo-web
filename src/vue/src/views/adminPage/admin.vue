<template>
  <div class="container home-banner-container space-type-1">
    <div class="row home-banner-row shadow-wrap-1">
      <div class="title-back">
        <p class="title">회원관리</p>
      </div>
      <table class="userListTbl">
        <colgroup>
          <col width="10%" />
          <col width="22%" />
          <col width="22%" />
          <col width="10%" />
          <col width="22%" />
          <col width="10%" />
        </colgroup>
        <thead>
          <tr>
            <th scope="col">번호</th>
            <th scope="col">아이디</th>
            <th scope="col">비밀번호</th>
            <th scope="col">이름</th>
            <th scope="col">이메일</th>
            <th scope="col">관리</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="user in userList" :key="user">
            <th></th>
            <th>{{ user.userId }}</th>
            <th>{{ user.userPassword }}</th>
            <th>{{ user.userName }}</th>
            <th>{{ user.userEmail }}</th>
            <th><span class="delBtn">Delete</span></th>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axiosUtil from '@/utils/axios-util';

export default {
  name: 'admin',
  data() {
    return {
      userList: []
    };
  },
  methods: {
    getUserList: function () {
      axiosUtil.get('/api/admin/userList', {}, result => {
        this.userList = result.data.userList;
        console.log(this.userList);
      });
    }
  },
  beforeMount() {
    this.getUserList();
  }
};
</script>

<style scoped>
.title-back {
  /* background: rgb(245, 219, 165); */
  width: 90%;
  /* border-radius: 22px; */
  margin: auto;
}
.title {
  font-size: 22px;
  font-weight: 600;
  color: #000;
  padding: 15px 0 0 20px;
}
.userListTbl {
  width: 90%;
  margin: auto;
}
.userListTbl thead {
  background: rgb(230, 230, 230);
  width: 100%;
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
  font-weight: 200;
  border-bottom: 1px solid #ccc;
}
.userListTbl tr {
  padding: 0.25rem;
}
.delBtn {
  background: #ccc;
  padding: 4px;
}
</style>