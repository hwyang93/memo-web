<template>
  <div>
    <header class="header-one" v-if="$route.path !== '/login' && $route.path !== '/signUp'" id="header">
      <TopMenu />
    </header>
    <!-- Header-end -->
    <!-- Main-start -->
    <div>
      <ContentLoader />
    </div>
    <!-- Main-end -->
    <chat-component />
  </div>
</template>

<script>
import TopMenu from './components/TopMenu.vue';
import ContentLoader from './components/ContentLoader.vue';
import ChatComponent from './components/ChatComponent';
export default {
  name: 'App',
  components: { TopMenu, ContentLoader, ChatComponent },
  methods: {
    loginGo: function () {
      this.$router.push('/login');
    }
  }
};
</script>
<style lang="css">
@import './css/bootstrap.min.css';
@import './css/lity.min.css';
@import './css/responsive.css';
@import './css/style.css';
</style>
