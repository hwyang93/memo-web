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
        <div class="reply">
          <div>{{ post.userMemo.user.userName }}</div>
          <div>{{ reItem.contents }}ㄹㅇ남러ㅣㅏㄴㅇ럼;ㅣㅏㅇ널;ㅣㅏ너라ㅣ;ㄴ런;ㅣ아런아ㅣ;런ㅇ;ㅏㅣㄹ넝;ㅣㅏ</div>
        </div>
        <div>
          <a>답글달기</a>
        </div>
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

export default {
  name: 'postDetail',
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
    onEnter(e, params) {
      e.preventDefault();
      if (this.reply.length > 0) {
        if (e.ctrlKey) {
          this.reply += '\n';
        } else {
          this.saveReply(params);
        }
      }
      // this.$refs.textarea.focus();
    },
    saveReply(params) {
      console.log(params.groupOrd);
    }
  }
};
</script>

<style scoped></style>
