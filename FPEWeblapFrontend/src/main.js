import Vue from "vue";
import App from "./App.vue";
import store from "./store";
import VueRouter from 'vue-router';
import Routes from './routes';
import moment from 'moment';

// BootstrapVue
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

// Vue Toastification
import Toast from "vue-toastification";
import "vue-toastification/dist/index.css";
import i18n from './i18n';
import VueFormulate from "@braid/vue-formulate";
import { hu } from '@braid/vue-formulate-i18n';
import '../node_modules/@braid/vue-formulate/themes/snow/snow.scss';

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.use(VueRouter);
Vue.use(VueFormulate, {
  plugins: [hu],
  locale: 'hu'
});
// Here you can define global options for Vue Toastification
const toastOptions = {
  // Example options
  position: "top-right",
  timeout: 5000,
  closeOnClick: true,
  pauseOnFocusLoss: true,
  pauseOnHover: true,
  draggable: true,
  draggablePercent: 0.6,
  showCloseButtonOnHover: false,
  hideProgressBar: false,
  closeButton: "button",
  icon: true,
  rtl: false
};

Vue.use(Toast, toastOptions);

Vue.config.productionTip = false;

Vue.prototype.$moment = moment;

const router = new VueRouter({
  routes: Routes,
  mode: 'history',
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    } else if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  }
});

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    const jwtToken = localStorage.getItem('jwt-token') || sessionStorage.getItem('jwt-token');
    if (!jwtToken) {
      next({
        name: 'LoginView',
        //query: { redirect: to.fullPath } // Optional: carry the intended route in the query
      });
    } else {
      next();
    }
  } else {
    next();
  }
});

import Axios from 'axios';

Axios.interceptors.request.use(
  function (config) {
    const localToken = localStorage.getItem('jwt-token');
    const sessionToken = sessionStorage.getItem('jwt-token');

    if (localToken || sessionToken) {
      if (localToken) {
        config.headers.authorization = localToken;
      } else {
        config.headers.authorization = sessionToken;
      }
    }

    return config;
  },
  function (error) {
    return Promise.reject(error);
  }
);

Axios.interceptors.response.use(
  response => response,
  error => {
    if (error.response && error.response.status === 405) {
      router.push({ name: 'LoginView' });
    }
    return Promise.reject(error);
  }
);

Vue.prototype.$http = Axios;

new Vue({
  router,
  store,
  i18n,
  render: (h) => h(App),
}).$mount("#app");
