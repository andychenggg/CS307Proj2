import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from "./router/index";
import axios from "axios";
Vue.config.productionTip = false

Vue.use(ElementUI);


new Vue({
  el: '#app',
  render: h => h(App),
  router
}).$mount('#app')

axios.defaults.withCredentials = true