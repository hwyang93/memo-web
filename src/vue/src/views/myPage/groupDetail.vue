<template>
  <section class="container space blog_page">
    <h1 class="title-type-1">Group Detail</h1>
    <!-- <div class="row"> -->
      <div class="col-sm-12 shadow-wrap-1">
        <div class="group-detail-content">
          <h3 class="title-type-2">그룹 상세</h3>
          <div class="row">
              <div class="col-lg-8">
                <div class="card">
                  <h4>이름</h4>
                  <p>정보</p>
                  <p>멤버</p>
                </div>
              </div>
            <div class="col-lg-4">
              <div class="card">hello</div>
            </div>
          </div>
        </div>
      </div>

    <!-- </div> -->
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
