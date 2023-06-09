import { createRouter, createWebHistory } from 'vue-router'

import PkIndexView from "@/views/pk/PkIndexView"
import RecordIndexView from "@/views/record/RecordIndexView"
import RanklistIndexView from "@/views/ranklist/RanklistIndexView"
import UserBotIndexView from "@/views/user/bot/UserBotIndexView"
import NotFund from "@/views/error/NotFound"
import RecordContentView from '../views/record/RecordContentView'

import UserAccountLoginView  from '@/views/user/account/UserAccountLoginView'
import UserAccountRegisterView  from '@/views/user/account/UserAccountRegisterView'
import store from '@/store/index'

const routes = [
  {
    path: "/",
    name: "home",
    redirect: "/pk/",
    meta:{
      requestAuth: true, 
    }
  },
  {
    path: "/pk/",
    name: "pk_index",
    component: PkIndexView,
    meta:{
      requestAuth: true,
    }
  },
  { //user_account_login
    path:"/user/account/login/",
    name:"user_account_login",
    component: UserAccountLoginView,
    meta:{
      requestAuth: false,
    },
  },
  { // user_account_register
    path:"/user/account/register/",
    name:"user_account_register",
    component: UserAccountRegisterView,
    meta:{
      requestAuth: false,
    }
  },
  { // record_index
    path: "/record/",
    name: "record_index",
    component: RecordIndexView,
    meta:{
      requestAuth: true,
    },
  },
  {
    path: "/record/:recordId/", // 在路由中使用参数的写法
    name: "record_content",
    component: RecordContentView,
    meta: {
      requestAuth: true,
    }
  },
  { // ranklist_index
    path: "/ranklist/",
    name: "ranklist_index",
    component: RanklistIndexView,
    meta:{
      requestAuth: true,
    }
  },
  { // user_bot_index
    path: "/user/bot/",
    name: "user_bot_index",
    component: UserBotIndexView,
    meta:{
      requestAuth: true,
    }
  },
  { // 404 /404/
    path: "/404/",
    name: "404",
    component: NotFund,
    meta:{
      requestAuth: false,
    }
  },
  { // 404 /:catchAll(.*)
    path: "/:catchAll(.*)",
    redirect: "/404/",
    meta:{
      requestAuth: false,
    }
  },]

const router = createRouter({
  history: createWebHistory(),
  routes
});

router.beforeEach((to, from, next) => {
  if (to.meta.requestAuth && !store.state.user.is_login) {
    console.log("重定向登录啦～")
    next({name: "user_account_login"});
  } else {
    console.log("进去啦～")
    next();
  }
})

export default router
