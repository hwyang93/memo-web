<template>
  <section class="topMenu-a">
    <div class="title-container title-light">
      <div class="title-dash">
        <a class="title-brand">{{ this.headName }}</a>
        <a id="logoutBtn" @click="logout()"><i class="fas fa-power-off"></i></a>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: 'adminTopMenu',
  props: ['send'],
  data() {
    return {
      headName: ''
    };
  },
  methods: {
    logout() {
      axiosUtil.post('/api/main/logout', {}, () => {
        localStorage.removeItem('vuex');
        this.$router.push('/login');
        this.$store.commit('resetState');
      });
    }
  },
  beforeMount() {
    this.headName = this.$props.send;
  }
};
</script>
<style>
.title-container.title-light {
  background-color: #f4f3ef;
  box-shadow: none;
  border-bottom: 1px solid #dddddd;
}
.title-dash {
  width: 100%;
  padding-right: 15px;
  padding-left: 15px;
  margin-right: auto;
  margin-left: auto;
}
.title-brand {
  font-weight: 600;
  margin: 5px 0px;
  padding: 22px 15px;
  font-size: 14px;
  color: #66615b;
  text-transform: uppercase;
  display: inline-block;
  line-height: inherit;
  white-space: nowrap;
}
#logoutBtn {
  float: right;
  margin: 5px 0px;
  padding: 22px 15px;
}
</style>