<template>
  <div class="main">
    <div class="d-flex justify-content-between align-items-center">
      <div class="header">
        <h3>message</h3>
      </div>
      <div class="close_icon" @click="closeChat">
        <span><i class="fas fa-times"></i></span>
      </div>
    </div>
    <div class="search-box">
      <b-input-group>
        <span class="search-back" v-if="mode === 'search'" @click="goBack"><i class="fas fa-chevron-left"></i></span>
        <b-input-group-prepend>
          <span class="input-group-text"><i class="fas fa-search"></i></span>
        </b-input-group-prepend>
        <b-form-input @keyup="searchFriend" @click="searchFocus" v-model="keyword" autocomplete="off" placeholder="입력...."> </b-form-input>
      </b-input-group>
    </div>
    <div class="list">
      <div v-if="mode === 'search'">
        <b-list-group>
          <b-list-group-item v-for="item in userList" :key="item.userId">
            <div class="d-flex align-items-center list" @click="showChatRoom(item)">
              <div class="user-pic">
                <img src="../images/friends/user-sample.jpg" alt="" />
              </div>
              <span style="padding-left: 10px">
                {{ item.userName }}
              </span>
            </div>
          </b-list-group-item>
        </b-list-group>
      </div>
      <div v-if="mode === 'chat'">
        <b-list-group>
          <b-list-group-item class="d-flex justify-content-between align-items-center">
            채팅목록
            <b-badge variant="primary" pill>가능하다면 읽지않은갯수</b-badge>
          </b-list-group-item>
        </b-list-group>
      </div>
    </div>
  </div>
</template>

<script>
import axiosUtil from '../utils/axios-util';
export default {
  name: 'memoDetail',
  props: {
    idx: Number
  },
  components: {},
  data() {
    return {
      mode: 'chat',
      keyword: '',
      searchFlag: false,
      userList: []
    };
  },
  watch: {
    keyword: function () {
      if (this.keyword.length === 0) {
        this.userList = [];
      }
    },
    userList: function () {
      this.searchFlag = this.userList.length > 0;
    }
  },
  methods: {
    searchFriend() {
      if (this.keyword === '') {
        this.userList = [];
        return;
      }
      const params = {
        keyword: this.keyword,
        relationStatus: 'I'
      };
      axiosUtil.get('/api/main/friend', params, result => {
        this.userList = result.data.friendList;
      });
    },
    outfocus() {
      this.keyword = '';
      this.userList = [];
      this.mode = 'chat';
    },
    showChatRoom(data) {
      this.$emit('showChatRoom', data);
      console.log(data);
    },
    searchFocus() {
      this.mode = 'search';
    },
    goBack() {
      this.mode = 'chat';
      this.keyword = '';
    },
    closeChat() {
      this.$emit('closeChat');
    }
  },
  beforeMount() {}
};
</script>

<style scoped>
.main h3 {
  margin: 8px;
}
.main .close_icon {
  margin: 8px;
  padding-right: 10px;
  cursor: pointer;
}
</style>
