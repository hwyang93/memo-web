<template>
  <div class="main">
    <div class="d-flex justify-content-between align-items-center">
      <div class="header">
        <h3>message</h3>
      </div>
      <div class="close_icon" @click="closeList">
        <span><i class="fas fa-times"></i></span>
      </div>
    </div>
    <div class="search-box">
      <b-input-group>
        <span v-if="mode === 'search'" class="search-back" @click="goBack"><i class="fas fa-chevron-left"></i></span>
        <b-input-group-prepend>
          <span class="input-group-text"><i class="fas fa-search"></i></span>
        </b-input-group-prepend>
        <b-form-input v-model="keyword" autocomplete="off" placeholder="입력...." @click="searchFocus" @keyup="searchFriend"></b-form-input>
      </b-input-group>
    </div>
    <div class="list">
      <div v-if="mode === 'search'">
        <b-list-group>
          <b-list-group-item v-for="item in userList" :key="item.userId">
            <div class="d-flex align-items-center list" @click="showChatRoom(item)">
              <div class="user-pic">
                <img alt="" src="../images/friends/user-sample.jpg" />
              </div>
              <span style="padding-left: 10px">
                {{ item.userName }}
              </span>
            </div>
          </b-list-group-item>
        </b-list-group>
      </div>
      <div v-if="mode === 'chat'">
        <b-tabs v-model="tabIdx" content-class="mt-3" fill>
          <!-- 개인일정 TAB start -->
          <b-tab title="개인">
            <b-list-group>
              <b-list-group-item v-for="(item, idx) in chatRoomListI" :key="idx" class="d-flex justify-content-between align-items-center" @click="showChatRoom(item)">
                {{ item.chatRoomTitle }}
                <b-badge pill variant="primary">가능하다면 읽지않은갯수</b-badge>
              </b-list-group-item>
            </b-list-group>
          </b-tab>
          <b-tab title="그룹">
            <b-list-group>
              <b-list-group-item v-for="(item, idx) in chatRoomListG" :key="idx" class="d-flex justify-content-between align-items-center" @click="showChatRoom(item)">
                {{ item.chatRoomTitle }}
                <b-badge pill variant="primary">가능하다면 읽지않은갯수</b-badge>
              </b-list-group-item>
            </b-list-group>
          </b-tab>
        </b-tabs>
      </div>
    </div>
  </div>
</template>

<script>
import axiosUtil from '../utils/axios-util';

export default {
  name: 'chatList',
  props: {
    idx: Number
  },
  components: {},
  data() {
    return {
      mode: 'chat',
      tabIdx: 0,
      gubun: 'I',
      keyword: '',
      searchFlag: false,
      chatRoomListI: [],
      chatRoomListG: [],
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
    },
    tabIdx(val) {
      if (val === 0) {
        this.gubun = 'I';
      } else {
        this.gubun = 'G';
      }
    }
  },
  methods: {
    getChatRoomList() {
      axiosUtil.get('/api/chat/chat', {}, result => {
        this.chatRoomListI = result.data.chatListI;
        this.chatRoomListG = result.data.chatListG;
      });
    },
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
    closeList() {
      this.$emit('closeList');
    }
  },
  beforeMount() {},
  created() {
    this.getChatRoomList();
  }
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
