import Vue from 'vue';
import Vuex from 'vuex';
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

export default new Vuex.Store({
  plugins: [createPersistedState()],
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
    },
    resetState(state) {
      state.auth.isLogin = false
      state.auth.userInfo = {}
    }
  },
  actions: {},
  modules: {}
});
