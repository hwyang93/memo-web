<template>
  <div class="wrapper-admin">
    <side-menu> </side-menu>
    <div class="main-content">
      <admin-top-menu :send="this.pageName"></admin-top-menu>
    </div>
  </div>
</template>
<script>
import SideMenu from '../../components/sideMenu.vue';
import AdminTopMenu from '../../components/adminTopMenu.vue';
export default {
  name: 'Users',
  components: {
    SideMenu,
    AdminTopMenu
  },
  data() {
    return {
      pageName: 'Users'
    };
  }
};
</script>