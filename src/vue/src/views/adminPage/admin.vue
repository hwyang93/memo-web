<template>
  <div class="container home-banner-container space-type-1">
    <div class="row home-banner-row shadow-wrap-1">
      <h3 class="title-back">회원관리</h3>
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
  padding: 8px 30px;
  color: #fff;
  overflow: hidden;
  margin-bottom: 30px;
  width: 30%;
  text-transform: capitalize;
  position: relative;
  background: linear-gradient(90deg, #00adb5 61%, #00adb5 2%, #00adb5 14%, #9a1f1f00 -8%);
}
.title-back:after {
  content: '';
  background: linear-gradient(90deg, #00adb5 57%, #393d46 13%, #9a1f1f00 37%);
  position: absolute;
  width: 68px;
  top: 0px;
  height: 100%;
  transform: rotate(180deg);
  bottom: 0;
  right: 28%;
  border-radius: 50% 0% 0 50%;
  height: 50px;
}
.container {
  margin-bottom: 40px;
}
</style>