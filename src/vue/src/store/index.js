import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    auth: {
      userInfo: {},
      isLogin: false
    },
    getters: {
      isLogin: state => {
        return state.auth.isLogin;
      }
    }
  },
  mutations: {
    LOGIN(state, userInfo) {
      state.auth.isLogin = true;
      if (userInfo) {
        state.auth.userInfo = userInfo;
      }
    }
  },
  actions: {},
  modules: {}
});
