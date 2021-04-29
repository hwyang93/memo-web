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
    <div class="search-box"></div>
    <div class="list">
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
