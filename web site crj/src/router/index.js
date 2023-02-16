import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: () => import('./../views/Home.vue'),
  },
  {
    path: '/NewsTeller',
    component: () => import('./../views/NewsTeller.vue'),
 
  },
  {
    path: '/OpenJobs',
    component: () => import('./../views/OpenJobs.vue'),
 
  },
  {
    path: '/OpenJobStatus',
    component: () => import('./../views/OpenJobStatus.vue'),
 
  },
  {
    path: '/InternshipApplicationForm',
    component: () => import('./../views/InternshipApplicationForm.vue'),
 
  },
  {
    path: '/ApplicationFormStatus',
    component: () => import('./../views/ApplicationFormStatus.vue'),
 
  },
  {
    path: '/SignUp',
    component: () => import('./../views/SignUp'),
 
  },
  {
    path: '/SignIn',
    component: () => import('./../views/SignIn'),
 
  },
  {
    path: '/LogOut',
    component: () => import('./../views/LogOut'),
 
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
