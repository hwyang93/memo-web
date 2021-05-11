<template>
  <div class="main d-flex flex-column">
    <div class="d-flex justify-content-between align-items-center">
      <div class="header">
        <h3>{{ title }}</h3>
      </div>
      <div class="close_icon" @click="closeRoom">
        <span><i class="fas fa-times"></i></span>
      </div>
    </div>
    <div class="content">
      <div v-for="(item, idx) in messageList" :key="idx">
        <div v-if="item.sendUserId !== $store.state.auth.userInfo.userId" class="d-flex justify-content-start">
          <div class="user-pic">
            <img alt="" src="../images/friends/user-sample.jpg" />
          </div>
          <div>
            <div class="user-info">
              {{ '사용자 이름' }}
            </div>
            <div class="message-recieve">
              {{ item.sendMessage }}
            </div>
          </div>
        </div>
        <div v-if="item.sendUserId === $store.state.auth.userInfo.userId" class="d-flex justify-content-end">
          <div class="message-send">
            {{ item.sendMessage }}
          </div>
        </div>
      </div>
    </div>
    <div class="d-flex justify-content-between align-items-center send">
      <div class="message-area">
        <b-form-textarea ref="textarea" v-model="message" max-rows="4" placeholder="Enter something..." rows="1" @keydown.enter="onEnter"></b-form-textarea>
      </div>
      <div class="icon" @click="sendMessage">
        <i class="far fa-paper-plane"></i>
      </div>
    </div>
  </div>
</template>

<script>
import axiosUtil from '../utils/axios-util';
import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';

export default {
  name: 'chatRoom',
  props: {
    roomInfo: Object
  },
  components: {},
  data() {
    return {
      mode: 'chat',
      title: '',
      searchFlag: false,
      message: '',
      userList: [],
      stompClient: null,
      roomIdx: '',
      chatRoomInfo: {},
      chatUserInfo: {},
      messageList: []
    };
  },
  watch: {
    roomInfo: function () {
      this.initRoom();
    }
  },
  methods: {
    initRoom() {
      if (!this.roomInfo.chatRoomInfo) {
        this.title = this.roomInfo.chatUserInfo.userName;
      } else {
        this.title = this.roomInfo.chatRoomInfo.chatRoomTitle;
        this.roomIdx = this.roomInfo.chatRoomInfo.chatRoomIdx;
        this.chatRoomInfo = this.roomInfo.chatRoomInfo;
        this.messageList = this.roomInfo.chatRoomMessage;
        this.connect();
      }
      this.chatUserInfo = this.roomInfo.chatUserInfo;
    },
    closeRoom() {
      this.$emit('closeRoom');
    },
    onEnter(e) {
      e.preventDefault();
      if (this.message.length > 0) {
        if (e.ctrlKey) {
          this.message += '\n';
        } else {
          this.sendMessage();
        }
      }
      this.$refs.textarea.focus();
    },
    async sendMessage() {
      if (Object.keys(this.chatRoomInfo).length === 0) {
        this.createChatRoom();
        // this.connect();
      }
      if (!(this.stompClient && this.stompClient.connected)) {
        this.connect();
      }
      setTimeout(() => {
        this.send();
      }, 100);

      this.scrollDown();
    },
    createChatRoom() {
      axiosUtil.post('/api/chat/chat/' + this.chatUserInfo.userId, {}, result => {
        this.chatRoomInfo = result.data.result;
      });
    },
    connect() {
      const serverURL = 'http://localhost:8099/ws';

      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      this.stompClient.connect(
        {},
        () => {
          this.connected = true;
          this.stompClient.subscribe('/send/' + this.chatRoomInfo.chatRoomIdx, res => {
            this.messageList.push(JSON.parse(res.body));
            this.scrollDown();
          });
        },
        () => {
          this.connected = false;
        }
      );
    },
    send() {
      console.log(this.$store.state.auth.stompClient);
      if (this.stompClient && this.stompClient.connected) {
        const msgInfo = {
          chatRoomIdx: this.chatRoomInfo.chatRoomIdx,
          sendUserId: this.$store.state.auth.userInfo.userId,
          sendMessage: this.message
        };
        this.stompClient.send('/receive/' + this.chatRoomInfo.chatRoomIdx, JSON.stringify(msgInfo), {});
        this.message = '';
      }
    },
    scrollDown() {
      let container = this.$el.querySelector('.content');
      container.scrollTop = container.scrollHeight;
    }
  },
  beforeMount() {},
  mounted() {}
};
</script>

<style scoped>
.main {
  height: 100%;
}

.main h3 {
  margin: 8px;
}

.main .close_icon {
  margin: 8px;
  padding-right: 10px;
  cursor: pointer;
}

.main .content {
  flex-basis: 80%;
  flex-shrink: 1;
  background-color: blanchedalmond;
  padding: 20px;
  overflow-y: auto;
}

.main .send {
  flex: auto;
}

.send .message-area {
  flex-grow: 1;
  margin: 10px;
}

.send .message-area textarea {
  overflow-y: unset !important;
}

.send .icon {
  background-color: #1fbbff87;
  padding: 15px;
  border-radius: 15px;
  margin-right: 10px;
}

.content > * {
  margin-bottom: 10px;
}

.content .user-info {
  margin-left: 15px;
  color: black;
}

.content .message-recieve {
  background-color: lavender;
  border-radius: 5px;
  box-shadow: 0px 1px 2px 0px;
  margin-left: 15px;
}

.content .message-send {
  background-color: lightskyblue;
  border-radius: 5px;
  box-shadow: 0px 1px 2px 0px;
}
</style>
