import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from './router'
import VueSession from 'vue-session'
import VuePdf from 'vue-pdf'
import "@/plugins/echarts";
Vue.use(VueSession)
Vue.use(VuePdf)

Vue.config.productionTip = false

new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')
