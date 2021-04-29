<template>
  <transition name="page">
    <router-view />
  </transition>
</template>

<script>
export default {
  name: "content-loader",
  props: {},
  data() {
    return {};
  },
  computed: {},
  methods: {},
  created() {},
  mounted() {},
};
</script>

<style>
.page-enter-active, .page-leave-active {
  transition: opacity .3s;
}
.page-enter, .page-leave-to /* .page-leave-active below version 2.1.8 */ {
  opacity: 0;
}
</style>