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
          <div class="shadow-wrap-2">
            <img src="" class="img-fluid" />
            <div class="post_catipon text-left">
              <ul class="nav post_nav">
                <li class="post_user">
                  <img src="" class="img_post_user" />
                  <h4>작성자</h4>
                  <span class="date"> 작성일 </span>
                </li>
              </ul>
              <p>{{ item.contents }}</p>
            </div>
            <b-carousel id="carousel-1" :interval="4000" controls indicators img-width="720" img-height="450" style="text-shadow: 1px 1px 2px #333">
              <b-carousel-slide img-height="450" v-for="(fileItem, index) in item.postFiles" :key="'postFile' + index" :img-src="'data:image/jpeg;base64,' + fileItem.fileContent"></b-carousel-slide>
            </b-carousel>
            <hr />
            <div class="d-flex justify-content-between">
              <div>
                <div @click="onLike(item)" class="icon">
                  <i :class="[item.likeYn === 'Y' ? 'fas fa-heart fa-2x' : 'far fa-heart fa-2x']"></i>
                </div>
                <div>좋아요 {{ item.postLikes.length }}개</div>
              </div>
              <div>댓글 00개</div>
            </div>
            <hr />
            <div>댓글영역</div>
            <hr />
            <div>
              <b-form-input placeholder="댓글을 입력하세요..."></b-form-input>
            </div>
          </div>
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
export default {
  name: 'memo',
  components: {
    leftMenu,
    leftSearch,
    leftTag
  },
  data() {
    return {
      postList: {}
    };
  },
  methods: {
    test() {
      debugger;
      alert(123);
      return false;
    },
    getPostList() {
      axiosUtil.get('/api/post/post/' + this.$store.state.auth.userInfo.userId, {}, result => {
        this.postList = result.data.postList;
      });
    },
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
    }
  },
  beforeMount() {
    this.getPostList();
  }
};
</script>
