<template>
  <div class="wrapper-admin">
    <side-menu> </side-menu>
    <div class="main-content">
      <admin-top-menu :send="this.pageName"></admin-top-menu>
      <div class="row groups">
        <div class="col-lg-4">
          <div class="card card-group shadow-wrap-1">
            <div class="card-content">
              <div class="c-box">State</div>
              <div class="neonsign-test">active</div>
            </div>
          </div>
        </div>
        <div class="col-lg-4">
          <div class="card card-group shadow-wrap-1">
            <div class="card-content">
              <div class="c-box">List</div>
            </div>
          </div>
        </div>
        <div class="col-lg-4">
          <div class="card card-group shadow-wrap-1">
            <div class="card-content">
              <div class="c-box">Info</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import SideMenu from '../../components/sideMenu.vue';
import AdminTopMenu from '../../components/adminTopMenu.vue';
export default {
  name: 'Groups',
  components: {
    SideMenu,
    AdminTopMenu
  },
  data() {
    return {
      pageName: 'Groups'
    };
  }
};
</script>

<style lang="css">
@import '../../css/adminStyle.css';
</style>