import Vue from 'vue';
import VueRouter from 'vue-router';
import store from '../store/index.js';
import Home from '../views/Home.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import(/* webpackChunkName: "main" */ '../views/Login.vue')
  },
  {
    path: '/signUp',
    name: 'SignUp',
    component: () => import(/* webpackChunkName: "main" */ '../views/SignUp.vue')
  },
  {
    path: '/myPage/schedule',
    name: 'Schedule',
    component: () => import(/* webpackChunkName: "main" */ '../views/myPage/mySchedule.vue')
  },
  {
    path: '/myPage/memo',
    name: 'Memo',
    component: () => import(/* webpackChunkName: "main" */ '../views/myPage/myMemo.vue')
  },
  {
    path: '/myPage/group',
    name: 'Group',
    component: () => import(/* webpackChunkName: "main" */ '../views/myPage/myGroup.vue')
  }
];

const router = new VueRouter({
  routes
});

router.beforeEach((to, from, next) => {
  if (!store.state.auth.isLogin) {
    if (to.path === '/login' || to.path === '/signUp') {
      next();
    } else {
      next('/login');
    }
  } else {
    next();
  }
});

export default router;
