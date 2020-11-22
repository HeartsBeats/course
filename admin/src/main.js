import Vue from 'vue'
import App from './app'
import router from './router'
import axios from "axios";
import filter from "./filter/filter"

Vue.config.productionTip = false;
Vue.prototype.$ajax = axios;
/**
 * axios拦截器
 */

// 解决每次ajax请求，对应的sessionId不一致的问题
axios.defaults.withCredentials = true;
axios.interceptors.request.use(function (config) {
  console.log("请求：", config);
  return config;
}, error => {
});
axios.interceptors.response.use(function (response) {
  console.log("返回结果：", response);
  return response;
}, error => {
});

// 全局过滤器
Object.keys(filter).forEach(key => {
  Vue.filter(key, filter[key])
});

// 路由登录拦截
router.beforeEach((to, from, next) => {
  // 要不要对meta.loginRequire属性做监控拦截
  if (to.matched.some(function (item) {
    return item.meta.loginRequire
  })) {
    let loginUser = Tool.getLoginUser();
    if (Tool.isEmpty(loginUser)) {
      next('/login');
    } else {
      next();
    }
  } else {
    next();
  }
});
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')