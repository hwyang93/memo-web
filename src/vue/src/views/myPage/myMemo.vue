<template>
  <section class="container space blog_page">
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
      <div class="col-lg-8 col-md-12 blog_page">
        <div class="row">
          <div class="blog_post col-lg-6 col-md-6 mb-3" v-for="item in memoList" :key="item.idx">
            <div class="card shadow-wrap-1" style="height: 300px">
              <div class="card-header">
                {{ item.title }}
                <div style="float: right">
                  <button @click="goPost">
                    <i class="fas fa-pencil-alt"></i>
                  </button>
                </div>
              </div>
              <div class="card-body" @click="shoeDetail(item.idx)">
                <h5 class="card-title">주소</h5>
                <p class="card-text" readonly>{{ item.memoPlace }}</p>
                <h5 class="card-title">메모</h5>
                <p class="card-text">{{ item.memo }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div v-if="detailFlag">
      <memo-detail :idx="idx" @closeModal="closeModal('detail')" />
    </div>
    <div v-if="postModalFlag">
      <memo-post-modal @closeModal="closeModal('post')" />
    </div>
  </section>
</template>

<script>
import axiosUtil from '@/utils/axios-util';
import leftMenu from '@/views/myPage/leftMenu';
import leftSearch from '@/views/myPage/leftSearch';
import leftTag from '@/views/myPage/leftTag';
import memoDetail from '@/views/myPage/memoDetail.vue';
import memoPostModal from '@/views/myPage/memoPostModal';
export default {
  name: 'memo',
  components: {
    leftMenu,
    leftSearch,
    leftTag,
    memoDetail,
    memoPostModal
  },
  data() {
    return {
      detailFlag: false,
      postModalFlag: false,
      idx: '',
      memoList: {}
    };
  },
  methods: {
    getMemo() {
      axiosUtil.get('/api/myMemo/myMemo', {}, result => {
        this.memoList = result.data.result;
      });
    },
    shoeDetail(idx) {
      this.idx = idx;
      this.detailFlag = true;
    },
    closeModal(modal) {
      if (modal === 'detail') {
        this.detailFlag = false;
      } else {
        this.postModalFlag = false;
      }
    },
    goPost() {
      this.postModalFlag = true;
    }
  },
  beforeMount() {
    this.getMemo();
  }
};
</script>
