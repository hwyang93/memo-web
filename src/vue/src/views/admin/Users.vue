<template>
  <div class="wrapper-admin">
    <side-menu> </side-menu>
    <div class="main-content">
      <div class="title-container">
        <div class="title-dash">
          <a>DashBoard</a>
          <a id="logout-dash" @click="logout()">logout</a>
        </div>
      </div>

      <!-- <div class="row">
        <div class="col-md-6 col-xl-3">
          <div class="card card-user shadow-wrap-1">
            <div class="cardName">
              <h4>Users</h4>
            </div>
          </div>
        </div>
      </div> -->
    </div>
  </div>
</template>
<script>
import SideMenu from '../../components/sideMenu.vue';
export default {
  name: 'Users',
  components: {
    SideMenu
  }
};
</script>