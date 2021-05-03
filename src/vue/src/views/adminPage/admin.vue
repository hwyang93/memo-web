<template>
  <div class="container home-banner-container space-type-1">
    <div class="row home-banner-row shadow-wrap-1">
      <div class="title-back">
        <p class="title">회원관리</p>
      </div>
      <page-link :listArray="userList" style="width: 90%; margin: auto" />
    </div>
  </div>
</template>

<script>
import axiosUtil from '@/utils/axios-util';
import pageLink from '@/components/pageLink.vue';
export default {
  name: 'admin',
  components: {
    pageLink
  },
  data() {
    return {
      userList: []
    };
  },
  created() {
    axiosUtil.get('/api/admin/userList', {}, result => {
      this.userList = result.data.userList;
    });
  }
};
</script>

<style scoped>
.title-back {
  width: 90%;
  border-radius: 22px;
  margin: auto;
}

.title {
  font-size: 22px;
  padding: 15px 0 0 20px;
}

.container {
  margin-bottom: 40px;
}
</style>