<template>
  <div class="wrapper-admin">
    <side-menu> </side-menu>
    <!-- user -->
    <div class="main-area">
      <div class="main-content">
        <admin-top-menu :send="this.pageName"></admin-top-menu>
        <div class="row">
          <div class="col-lg-4 col-md-6">
            <div class="card card-user shadow-wrap-1">
              <div class="card-content">
                <div class="c-box title">Users</div>
                <div class="c-box icon"><img src="../../images/dashboard/user.png" id="dashIcon" /></div>
                <div class="c-box data"><line-chart></line-chart></div>
                <div class="c-box total">Total : {{ this.userCnt }}</div>
              </div>
            </div>
          </div>
          <div class="col-lg-4 col-md-6">
            <div class="card card-user shadow-wrap-1">
              <div class="card-content">
                <div class="c-box title">Post</div>
                <div class="c-box icon"><img src="../../images/dashboard/post.png" id="dashIcon" /></div>
                <div class="c-box data"></div>
                <div class="c-box total">Total : {{ this.postCnt }}</div>
              </div>
            </div>
          </div>
          <div class="col-lg-4 col-md-6">
            <div class="card card-user shadow-wrap-1">
              <div class="card-content">
                <div class="c-box title">Group</div>
                <div class="c-box icon"><img src="../../images/dashboard/group.png" id="dashIcon" /></div>
                <div class="c-box data"></div>
                <div class="c-box total">Total : {{ this.groupCnt }}</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import AdminTopMenu from '../../components/adminTopMenu.vue';
import SideMenu from '../../components/sideMenu.vue';
import axiosUtil from '@/utils/axios-util';
import LineChart from '@/components/LineChart.vue';
export default {
  name: 'Dashboard',
  components: {
    SideMenu,
    AdminTopMenu,
    LineChart
  },
  data() {
    return {
      pageName: 'Dashboard',
      userCnt: 0,
      postCnt: 0,
      groupCnt: 0
    };
  },
  methods: {},
  created() {
    axiosUtil.get('/api/admin/dashboard/', {}, result => {
      this.userCnt = result.data.userCnt;
      this.postCnt = result.data.postCnt;
      this.groupCnt = result.data.groupCnt;
    });
  },
  mounted() {}
};
</script>
<style lang="css">
@import '../../css/adminStyle.css';
</style>
