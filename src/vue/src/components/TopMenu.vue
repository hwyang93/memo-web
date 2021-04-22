<template>
  <!--navbar-start-->
  <nav class="navbar navbar-default navbar-expand-md main-navbar">
    <div class="container">
      <div class="brand_toggle_cont">
        <!-- Brand -->
        <a class="navbar-brand" href="index.html">
          <!-- <img
            src="../images/all-img/logo.png"
            alt="logo_img"
            class="img-fluid"
          /> -->
          logo
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
        class="collapse navbar-collapse justify-content-center"
        id="navbarSupportedContent"
      >
        <ul
          class="navbar-nav menu navbar__nav nav justify-content-center"
          id="top-menu"
        >
          <li class="nav-item">
            <a @click="goMenu('/')" class="nav-link">Home</a>
          </li>
          <li class="nav-item">
            <a @click="goMenu('/myPage/schedule')" class="nav-link">myPage</a>
          </li>
        </ul>
        <!-- //navbar-collapse -->
        <div class="user-area d-flex">
        <ul class="d-flex align-items-center">
          <li class="user-info"><a href="#">User</a></li>
          <li class="user-Notifications">
            <!-- <button type="button" class="btn btn-primary">
              <i class="far fa-bell"></i> <span class="badge bg-secondary">4</span>
            </button> -->
            <div class="dropdown">
              <button class="btn btn-primary no-arrow dropdown-toggle" type="button" id="dropdownMenuButton2" data-bs-toggle="dropdown" aria-expanded="false">
                <i class="far fa-bell"></i> <span class="badge bg-secondary">4</span>
              </button>
              <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="dropdownMenuButton2">
                <li><a class="dropdown-item" href="#">description</a></li>
                <li><a class="dropdown-item" href="#">description</a></li>
                <li><a class="dropdown-item" href="#">description</a></li>
                <!-- <li><hr class="dropdown-divider"></li> -->
                <li><a class="dropdown-item" href="#">description</a></li>
              </ul>
            </div>
          </li>
        </ul>
      </div>
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
