<template>
  <div class="shadow-wrap-2">
    <img src="" class="img-fluid" />
    <div class="post_catipon text-left">
      <ul class="nav post_nav">
        <li class="post_user">
          <img src="" class="img_post_user" />
          <h4>{{ post.userMemo.user.userName }}</h4>
          <span class="date"> {{ post.regDate }} </span>
        </li>
      </ul>
      <p>{{ post.contents }}</p>
    </div>
    <b-carousel id="carousel-1" :interval="4000" controls indicators img-width="720" img-height="450" style="text-shadow: 1px 1px 2px #333">
      <b-carousel-slide img-height="450" v-for="(fileItem, index) in post.postFiles" :key="'postFile' + index" :img-src="'data:image/jpeg;base64,' + fileItem.fileContent"></b-carousel-slide>
    </b-carousel>
    <hr />
    <div class="d-flex justify-content-between">
      <div>
        <div @click="onLike(post)" class="icon">
          <i :class="[post.likeYn === 'Y' ? 'fas fa-heart fa-2x' : 'far fa-heart fa-2x']"></i>
        </div>
        <div>좋아요 {{ post.postLikes.length }}개</div>
      </div>
      <div>댓글 {{ post.postReplyList.length }}개</div>
    </div>
    <hr />
    <div class="d-flex flex-column">
      <div v-for="reItem in post.postReplyList" :key="'post' + reItem.idx" :class="[reItem.groupOrd > 0 ? 're' : '']">
        <post-reply :post-user-info="post.userMemo.user" :post-reply-info="reItem" @setReply="setReply"></post-reply>
      </div>
    </div>
    <hr />
    <div>
      <b-form-textarea v-model="reply" max-rows="4" placeholder="댓글을 입력하세요..." rows="1" @keydown.enter="onEnter"></b-form-textarea>
    </div>
  </div>
</template>

<script>
import axiosUtil from '@/utils/axios-util';
import PostReply from '@/views/myPage/post/postReply';
export default {
  name: 'postDetail',
  components: { PostReply },
  props: {
    post: Object
  },
  data() {
    return {
      reply: ''
    };
  },
  methods: {
    onLike(post) {
      if (post.likeYn === 'Y') {
        this.cancelLike(post);
      } else {
        this.saveLike(post);
      }
    },
    saveLike(post) {
      const params = {
        postIdx: post.postIdx,
        userId: this.$store.state.auth.userInfo.userId
      };
      axiosUtil.post('/api/post/postLike', params, () => {
        post.likeYn = 'Y';
        post.postLikes.push({ userId: this.$store.state.auth.userInfo.userId });
      });
    },
    cancelLike(post) {
      const params = {
        postIdx: post.postIdx,
        userId: this.$store.state.auth.userInfo.userId
      };
      axiosUtil.delete('/api/post/postLike', params, () => {
        post.likeYn = 'N';
        post.postLikes.forEach((item, idx) => {
          if (this.$store.state.auth.userInfo.userId === item.userId) {
            post.postLikes.splice(idx, 1);
          }
        });
      });
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
      // this.$refs.textarea.focus();
    },
    saveReply() {
      console.log(this.post);
      const params = {
        postIdx: this.post.postIdx,
        contents: this.reply
      };
      axiosUtil.post('/api/post/postReply', params, result => {
        this.reply = '';
        this.post.postReplyList = result.data.result;
      });
    },
    setReply(replyList) {
      this.post.postReplyList = replyList;
    }
  }
};
</script>

<style scoped></style>
