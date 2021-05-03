<template>
  <div>
    <button type="button" class="message" :class="[this.$store.state.auth.isLogin ? 'show' : '']" @click="showChat()"><i class="fas fa-comments"></i></button>
    <div v-if="modalFlag">
      <div class="list-box">
        <chat-list @closeChat="closeChat" @showChatRoom="showChatRoom" />
      </div>
    </div>
    <div v-if="chatRoomFlag">
      <div class="chat-box">
        <chat-room :room-info="roomInfo" />
      </div>
    </div>
  </div>
</template>

<script>
import ChatList from './ChatList';
import ChatRoom from './ChatRoom';
import axiosUtil from '../utils/axios-util';
export default {
  name: 'ChatComponent',
  components: { ChatList, ChatRoom },
  data() {
    return {
      modalFlag: false,
      chatRoomFlag: false,
      roomInfo: {}
    };
  },
  methods: {
    showChat() {
      this.modalFlag = true;
    },
    closeChat() {
      this.modalFlag = false;
    },
    showChatRoom(data) {
      const params = {
        user2: data.userId
      };
      axiosUtil.get('/api/chat/chatRoom/' + data.userId, params, result => {
        console.log(result);
        this.roomInfo = result.data;
      });
      this.modalFlag = false;
      this.chatRoomFlag = true;
    }
  }
};
</script>

<style scoped>
button.message {
  margin: 0 !important;
  padding: 0 !important;
  background: #fff;
  height: 0px;
  width: 0px;
  overflow: hidden;
  border-radius: 50px;
  -webkit-border-radius: 50px;
  -moz-border-radius: 50px;
  color: transparent;
  clear: both;
  visibility: hidden;
  position: fixed;
  cursor: pointer;
  display: block;
  border: none;
  right: 50px;
  bottom: 75px;
  font-size: 0px;
  outline: 0 !important;
  z-index: 10;
  transition: all 0.3s ease-in-out;
}
button.message:hover,
button.message:active,
button.message:focus {
  outline: 0 !important;
}
button.message::before {
  display: block;
  vertical-align: middle;
  margin: -5px 0 auto;
}
button.message.show {
  display: block;
  background: #fff;
  color: #007bff;
  font-size: 25px;
  right: 25px;
  bottom: 50px;
  height: 50px;
  width: 50px;
  visibility: visible;
  box-shadow: 0px 2px 4px 1px rgba(0, 0, 0, 0.25);
  -webkit-box-shadow: 0px 2px 4px 1px rgba(0, 0, 0, 0.25);
  -moz-box-shadow: 0px 2px 4px 1px rgba(0, 0, 0, 0.25);
}
button.message.show:active {
  box-shadow: 0px 4px 8px 2px rgba(0, 0, 0, 0.25);
  -webkit-box-shadow: 0px 4px 8px 2px rgba(0, 0, 0, 0.25);
  -moz-box-shadow: 0px 4px 8px 2px rgba(0, 0, 0, 0.25);
}
.list-box {
  box-shadow: 0px 4px 8px 2px rgba(0, 0, 0, 0.25);
  right: 10px;
  bottom: 10px;
  width: 18%;
  height: 55%;
  background: white;
  position: fixed;
  z-index: 15;
  border-radius: 10px;
}
.chat-box {
  box-shadow: 0px 4px 8px 2px rgba(0, 0, 0, 0.25);
  right: 10px;
  bottom: 10px;
  width: 18%;
  height: 60%;
  background: white;
  position: fixed;
  z-index: 15;
  border-radius: 10px;
}
</style>
