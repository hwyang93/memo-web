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
  },
  {
    path: '/myPage/profile',
    name: 'Profile',
    component: () => import(/* webpackChunkName: "main" */ '../views/myPage/myProfile.vue')
  },
  {
    path: '/myPage/group/groupDetail/:id?',
    name: 'groupDetail',
    component: () => import(/* webpackChunkName: "main" */ '../views/myPage/groupDetail.vue')
  },
  {
    path: '/adminPage/admin',
    name: 'admin',
    component: () => import(/* webpackChunkName: "main" */ '../views/adminPage/admin.vue')
  }
];

const router = new VueRouter({
  routes,
  mode: 'history'
});

const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => {
    if (err.name !== 'NavigationDuplicated') throw err;
  });
};

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
