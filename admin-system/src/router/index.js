import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: () => import('./../views/Authentification'),
  },
  {
    path: '/dashboard',
    component: () => import('./../views/Dashboard'),
  },
  {
    path: '/CreateEmployeeAccount',
    component: () => import('./../views/CreateEmployeeAccount'),
 
  },
  {
    path: '/ViewEmployeeAccounts',
    component: () => import('./../views/ViewEmployeeAccounts'),
 
  },
  {
    path: '/pdf',
    component: () => import('./../components/menu'),
 
  },
  {
    path: '/CreateJobOffer',
    component: () => import('./../views/CreateJobOffer'),
 
  },
  {
    path: '/ViewJobOffers',
    component: () => import('./../views/ViewJobOffers'),
 
  },
  {
    path: '/CreateNewsTeller',
    component: () => import('./../views/CreateNewsTeller'),
 
  },
  {
    path: '/ViewNewsTeller',
    component: () => import('./../views/ViewNewsTeller'),
 
  },
  {
    path: '/ViewJobsApplications',
    component: () => import('./../views/ViewJobsApplication'),
 
  },
  {
    path: '/ViewInternshipApplications',
    component: () => import('./../views/ViewInternshipApplication'),
 
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
