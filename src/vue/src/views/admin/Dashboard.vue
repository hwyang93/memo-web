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
                <div class="c-box data"><chart></chart></div>
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
import chart from '@/components/chart.vue';
export default {
  name: 'Dashboard',
  components: {
    SideMenu,
    AdminTopMenu,
    chart
  },
  data() {
    return {
      pageName: 'Dashboard',
      userCnt: 0,
      postCnt: 0,
      groupCnt: 0,
      datacollection: null
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

<style>
.main-area {
  position: fixed;
  width: 100%;
}
.main-content {
  background-color: #f4f3ef;
  position: relative;
  z-index: 2;
  float: right;
  width: calc(100% - 260px);
  min-height: 100%;
  overflow: auto;
}
.card {
  margin: 30px 20px 30px 20px;
}
.card .card-content {
  padding: 15px 15px 10px;
}
.c-box {
  width: 100%;
  padding: 20px;
}
.c-box.title {
  padding: 7px;
  font-weight: 600;
}
.c-box.icon {
  padding: 20px;
  margin: auto;
  border-top: 1px solid #ccc;
  text-align: center;
}
.c-box.total {
  border-top: 1px solid #ccc;
  padding: 7px;
  font-weight: 600;
  text-align: right;
  color: gray;
}
.c-box.data {
  padding: 10px;
  margin: auto;
  height: 250px;
  margin-bottom: 15px;
}
#dashIcon {
  width: 70%;
  height: 70%;
  opacity: 0.5;
}
</style>