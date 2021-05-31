<template>
  <div>
    <div class="reply">
      <div>{{ postUserInfo.userName }}</div>
      <div>{{ postReplyInfo.contents }}</div>
    </div>
    <div>
      <a @click="onReply">답글달기</a>
      <div v-if="replyFlag">
        <b-form-textarea v-model="reply" max-rows="4" placeholder="댓글을 입력하세요..." rows="1" @keydown.enter="onEnter"></b-form-textarea>
      </div>
    </div>
  </div>
</template>

<script>
import axiosUtil from '@/utils/axios-util';

export default {
  name: 'postReply',
  props: {
    postUserInfo: Object,
    postReplyInfo: Object
  },
  data() {
    return {
      replyFlag: false
    };
  },
  methods: {
    onReply() {
      this.replyFlag = !this.replyFlag;
    },
    onEnter(e) {
      e.preventDefault();
      if (this.reply.length > 0) {
        if (e.ctrlKey) {
          this.reply += '\n';
        } else {
          this.saveReply();
        }
      }
    },
    saveReply() {
      console.log(this.post);
      const params = {
        postIdx: this.postReplyInfo.postIdx,
        originNo: this.postReplyInfo.originNo,
        contents: this.reply
      };
      axiosUtil.post('/api/post/postReply', params, result => {
        this.reply = '';
        this.replyFlag = false;
        this.$emit('setReply', result.data.result);
      });
    }
  }
};
</script>

<style scoped></style>
