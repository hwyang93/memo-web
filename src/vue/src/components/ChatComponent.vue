<template>
  <div>
    <button type="button" class="message" :class="[this.$store.state.auth.isLogin ? 'show' : '']" @click="showChat()"><i class="fas fa-comments"></i></button>
    <div v-if="modalFlag">
      <!--      <div style="position: absolute; right: 0px; bottom: 0px">fasdfsfsdfsd</div>-->

      <div class="message-box">
        <chat-list />
      </div>
    </div>
  </div>
</template>

<script>
import ChatList from './ChatList';
export default {
  name: 'ChatComponent',
  components: { ChatList },
  data() {
    return {
      modalFlag: false
    };
  },
  methods: {
    showChat() {
      this.modalFlag = true;
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
.message-box {
  box-shadow: 0px 4px 8px 2px rgba(0, 0, 0, 0.25);
  right: 10px;
  bottom: 10px;
  width: 15%;
  height: 50%;
  background: white;
  position: fixed;
  z-index: 15;
  border-radius: 30px;
}
</style>
