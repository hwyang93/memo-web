<template>
  <div class="container space blog_page">
    <div class="row">
      <!--blog-sidebar-->
      <div class="col-lg-4 col-md-12">
        <div class="sidebar sidebar_left bg-gray">
          <!--search-->
          <left-search />
          <!--widgets-cat-->
          <left-menu></left-menu>
          <!--tags-sidebar-->
          <left-tag />
        </div>
      </div>
      <!--/sidebar-close-->
      <div class="col-lg-8 col-md-12">
        <div class="blog_post-details" v-for="(item, index) in postList" :key="'post' + index">
          <post-detail :post="item"></post-detail>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axiosUtil from '@/utils/axios-util';
import leftMenu from '@/views/myPage/leftMenu';
import leftSearch from '@/views/myPage/leftSearch';
import leftTag from '@/views/myPage/leftTag';
import PostDetail from '@/views/myPage/post/postDetail';
export default {
  name: 'memo',
  components: {
    PostDetail,
    leftMenu,
    leftSearch,
    leftTag
  },
  data() {
    return {
      reply: '',
      postList: {}
    };
  },
  methods: {
    getPostList() {
      axiosUtil.get('/api/post/post/' + this.$store.state.auth.userInfo.userId, {}, result => {
        this.postList = result.data.postList;
      });
    }
    // onLike(post) {
    //   if (post.likeYn === 'Y') {
    //     this.cancelLike(post);
    //   } else {
    //     this.saveLike(post);
    //   }
    // },
    // saveLike(post) {
    //   const params = {
    //     postIdx: post.postIdx,
    //     userId: this.$store.state.auth.userInfo.userId
    //   };
    //   axiosUtil.post('/api/post/postLike', params, () => {
    //     post.likeYn = 'Y';
    //     post.postLikes.push({ userId: this.$store.state.auth.userInfo.userId });
    //   });
    // },
    // cancelLike(post) {
    //   const params = {
    //     postIdx: post.postIdx,
    //     userId: this.$store.state.auth.userInfo.userId
    //   };
    //   axiosUtil.delete('/api/post/postLike', params, () => {
    //     post.likeYn = 'N';
    //     post.postLikes.forEach((item, idx) => {
    //       if (this.$store.state.auth.userInfo.userId === item.userId) {
    //         post.postLikes.splice(idx, 1);
    //       }
    //     });
    //   });
    // },
    // onEnter(e) {
    //   e.preventDefault();
    //   if (this.reply.length > 0) {
    //     if (e.ctrlKey) {
    //       this.reply += '\n';
    //     } else {
    //       // this.sendMessage();
    //     }
    //   }
    //   // this.$refs.textarea.focus();
    // }
  },
  beforeMount() {
    this.getPostList();
  }
};
</script>
