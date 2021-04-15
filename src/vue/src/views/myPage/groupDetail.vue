<template>
  <section class="container space blog_page">
    <h1 class="title-type-1">Group Detail</h1>
    <div class="row">
      <div class="col-sm-12">
        <div class="group-detail-content shadow-wrap-1">
          ja
        </div>
      </div>

    </div>
  </section>
</template>
<script>
import axiosUtil from '@/utils/axios-util';
// import leftMenu from '@/views/myPage/leftMenu';

export default {
  name: 'groupDetail',
  components: {

  },
  data() {
    return {
      groupMemoList: [],
      groupMemberList: []
    };
  },
  methods: {
    getGroupMemo() {
      axiosUtil.get('/api/myGroup/getGroupMemoList.do', groupNo, result => {
        this.groupMemoList = result.data.groupMemoList;
      });
    },
    getGroupMember() {
      axiosUtil.get('/api/myGroup/getGroupMemberList.do', groupNo, result => {
        this.groupMemberList = result.data.groupMemberList;
      });
    },
    beforeMount() {
      this.getGroupMemo();
      this.getGroupMember();
    }
  }
};
</script>
