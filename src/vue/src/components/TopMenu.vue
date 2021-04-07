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
            <a @click="goMenu('/mySchedule/main')" class="nav-link"> 내일정 </a>
          </li>
          <li class="nav-item">
            <a href="service.html" class="nav-link"> Services </a>
          </li>
          <li class="nav-item">
            <a href="team.html" class="nav-link"> Team </a>
          </li>
          <li class="nav-item dropdown mega_menu">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdownMenuLink"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Pages
            </a>
            <ul
              class="dropdown-menu row dropdown-menu container"
              aria-labelledby="navbarDropdownMenuLink"
            >
              <li class="dropdown-submenu col-lg-3">
                <a class="dropdown-item dropdown-toggle lable_title" href="#">
                  Other Pages
                </a>
                <ul class="dropdown-menu">
                  <li class="nav-item">
                    <a class="nav-link" href="service-detail.html">
                      Service Details
                    </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="team-detail.html">
                      team Details
                    </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="blog-detail.html">
                      Blog Details
                    </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="404.html"> Error 404 </a>
                  </li>
                </ul>
              </li>
              <li class="dropdown-submenu col-lg-3">
                <a class="dropdown-item dropdown-toggle lable_title" href="#">
                  Footer
                </a>
                <ul class="dropdown-menu">
                  <li class="nav-item">
                    <a class="nav-link" href="#"> Footer version 1 </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#"> Footer version 3 </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">Footer version 3 </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#"> Footer version 4</a>
                  </li>
                </ul>
              </li>
              <li class="dropdown-submenu col-lg-3">
                <a class="dropdown-item dropdown-toggle lable_title" href="#">
                  Banner
                </a>
                <ul class="dropdown-menu">
                  <li class="nav-item"></li>
                  <li class="nav-item">
                    <a class="nav-link" href="#"> Banner video Version </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#"> Banner Zoom Version </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#"> Banner Clean Version </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#"> Banner Single Image </a>
                  </li>
                </ul>
              </li>
              <li class="dropdown-submenu col-lg-3">
                <a class="dropdown-item dropdown-toggle lable_title" href="#"
                  >Subsubmenu 3</a
                >
                <ul class="dropdown-menu">
                  <li class="nav-item">
                    <a class="nav-link" href="#"> Theme 1</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#"> Theme 2 </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#"> Theme 3 </a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#"> Theme 4 </a>
                  </li>
                </ul>
              </li>
            </ul>
          </li>
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Blog
            </a>
            <ul class="dropdown-menu">
              <li class="nav-item">
                <a class="nav-link" href="blog.html"> Blog</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="blog-detail.html">Blog Details</a>
              </li>
            </ul>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="contact.html"> Contact </a>
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
