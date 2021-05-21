<template>
  <div class="wrapper-admin">
    <side-menu> </side-menu>
    <div class="main-content">
      <admin-top-menu :send="this.pageName"></admin-top-menu>
      <div class="container home-banner-container space-type-1">
        <div class="row">
          <div class="col-lg-3 home-banner-row left shadow-wrap-1">
            <img src="../../images/dashboard/post.png" id="title-icon" alt="postIcon" />
            <h4 class="title-left">Post</h4>
            <div class="userListBox"></div>
          </div>
          <div class="col-lg-8 home-banner-row right shadow-wrap-1">
            <div class="title-back">
              <img src="../../images/dashboard/post.png" id="title-icon" alt="postIcon" />
              <h3 class="title-name">게시글 관리</h3>
              <div class="userInfoBox">
                <div>
                  <!-- <span class="delBtn" @click="delPost()">Delete</span> -->
                </div>
              </div>
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
  name: 'Board',
  components: {
    SideMenu,
    AdminTopMenu
  },
  data() {
    return {
      pageName: 'Post'
    };
  },
  methods: {
    delPost() {}
  }
};
</script>