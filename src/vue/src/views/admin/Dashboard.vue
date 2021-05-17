<template>
  <div class="wrapper-admin">
    <side-menu> </side-menu>
    <!-- user -->
    <div class="main-content">
      <admin-top-menu></admin-top-menu>
      <div class="row">
        <div class="col-lg-5 col-md-5">
          <div class="card card-user shadow-wrap-1">
            <div class="card-content">
              <div>Users</div>
              <div>icon</div>
              <div>Total</div>
            </div>
          </div>
        </div>
        <div class="col-lg-5 col-md-5">
          <div class="card card-user shadow-wrap-1">
            <div class="card-content">
              <div>Post</div>
              <div>icon</div>
              <div>Total</div>
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
      name: ''
    };
  },
  methods: {
    logout() {}
  }
};
</script>

<style scoped>
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
</style>