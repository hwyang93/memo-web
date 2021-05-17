<template>
  <div class="sidebar">
    <div class="sidebar-wrapper">
      <div class="logo">
        <a href="#" class="title-text">
          <div class="logoImg"><img src="#" /></div>
          title
        </a>
      </div>

      <slot> </slot>
      <ul class="side-nav">
        <li class="nav-item"><a class="nav-link" @click="change('/admin/DashBoard')">DashBoard</a></li>
        <li class="nav-item"><a class="nav-link" @click="change('/admin/Users')">Users</a></li>
        <li class="nav-item"><a class="nav-link" @click="change('/admin/Post')">Post</a></li>
        <li class="nav-item"><a class="nav-link" @click="change('/admin/Groups')">Groups</a></li>
      </ul>
    </div>
  </div>
</template>
<script>
export default {
  name: 'sideMenu',
  data() {
    return {
      name: '',
      isActive: true
    };
  },
  methods: {
    change(url) {
      this.$router.push(url);
    }
  }
};
</script>

<style>
a:not([href]):not([tabindex]).nav-link.active {
  color: #053c4b;
  opacity: 1;
}
.sidebar {
  max-height: 100%;
  height: 100%;
  overflow: hidden;
  overflow-y: hidden;
  width: 260px;
  display: block;
  font-weight: 200;
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  z-index: 1;
  background: #00adb5;
  background-size: cover;
  background-position: center center;
  transition-property: top, bottom;
  transition-duration: 0.2s, 0.2s;
}
.sidebar-wrapper {
  position: relative;
  height: 100%;
  overflow-y: auto;
  overflow-x: hidden;
  width: 260px;
  z-index: 4;
  box-shadow: inset -1px 0px 0px 0px #dddddd;
}
.logo {
  padding: 13px 0;
  margin: 0 20px;
  border-bottom: 1px solid #ccc;
}
img {
  vertical-align: middle;
  border-style: none;
}
.logoImg {
  width: 40px;
  display: inline-block;
  height: 40px;
  margin-left: 0px;
  margin-right: 10px;
  background: white;
  border-radius: 40px;
  text-align: center;
}
.side-nav li {
  cursor: pointer;
  margin: 0;
  line-height: 30px;
  font-size: 14px;
  font-weight: 600;
  text-transform: uppercase;
}
a:not([href]):not([tabindex]).nav-link {
  color: #fff;
  margin: 10px 0px;
  padding-left: 25px;
  padding-right: 25px;
  opacity: 0.6;
}
a:not([href]):not([tabindex]).nav-link:hover,
a:not([href]):not([tabindex]).nav-link:focus {
  color: #000;
  opacity: 0.7;
}
.title-text {
  padding: 5px 0px;
  display: block;
  font-size: 16px;
  text-align: center;
  font-weight: 600;
  line-height: 40px;
  text-align: left;
}
</style>