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
                <div class="c-box icon"><img src="#" id="userIcon" /></div>
                <div class="c-box cnt">Total</div>
              </div>
            </div>
          </div>
          <div class="col-lg-4 col-md-6">
            <div class="card card-user shadow-wrap-1">
              <div class="card-content">
                <div class="c-box title">Post</div>
                <div class="c-box icon"><img src="#" id="userIcon" /></div>
                <div class="c-box cnt">Total</div>
              </div>
            </div>
          </div>
          <div class="col-lg-4 col-md-6">
            <div class="card card-user shadow-wrap-1">
              <div class="card-content">
                <div class="c-box title">Group</div>
                <div class="c-box icon"><img src="#" id="userIcon" /></div>
                <div class="c-box cnt">Total</div>
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

export default {
  name: 'Dashboard',
  components: {
    SideMenu,
    AdminTopMenu
  },
  data() {
    return {
      pageName: 'Dashboard'
    };
  },
  methods: {
    logout() {}
  }
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
.c-box.title {
  padding: 7px;
  width: 100%;
  border-bottom: 1px solid #ccc;
  font-weight: 600;
}
#userIcon {
  text-align: center;
  width: 250px;
  height: 250px;
  border: 1px solid #ccc;
}
</style>