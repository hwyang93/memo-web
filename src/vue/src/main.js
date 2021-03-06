import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import BootstrapVue from 'bootstrap-vue';
import axios from 'axios';
import VueAxios from 'vue-axios';
import qs from 'qs';
import utils from '@/utils/axios-util.js';
import vueMoment from 'vue-moment';
import '@fortawesome/fontawesome-free/css/all.css';

Vue.use(BootstrapVue);
Vue.use(qs);
Vue.use(utils);
Vue.use(VueAxios, axios);
Vue.config.productionTip = false;
Vue.use(vueMoment);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
