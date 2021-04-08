<template>
  <!--navbar-start-->
  <nav class="navbar navbar-default navbar-expand-md main-navbar">
    <div class="container">
      <div class="brand_toggle_cont">
        <!-- Brand -->
        <a class="navbar-brand" href="index.html">
          <img
            src="../images/all-img/logo.png"
            alt="logo_img"
            class="img-fluid"
          />
        </a>
        <!-- nav-toggler -->
        <button
          class="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon fa fa-bars"> </span>
        </button>
      </div>
      <!-- navbar-collapse -->
      <div
        class="collapse navbar-collapse justify-content-end"
        id="navbarSupportedContent"
      >
        <ul
          class="navbar-nav navbar__nav nav justify-content-end"
          id="top-menu"
        >
          <li class="nav-item">
            <a @click="goMenu('/')" class="nav-link">Home</a>
          </li>
          <li class="nav-item">
            <a @click="goMenu('/myPage/schedule')" class="nav-link"> myPage </a>
          </li>
        </ul>
        <!-- //navbar-collapse -->
      </div>
      <!-- //navbar-collapse -->
    </div>
  </nav>
  <!--navbar-end-->
</template>

<script>
export default {
  name: "TopMenu",
  methods: {
    goMenu(url) {
      this.$router.push(url);
    }
  }
}
</script>
