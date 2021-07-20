import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/Home.vue'
import Login from '@/views/Login.vue' 
import Register from '@/views/Register.vue'
import Dashboard from '@/views/Dashboard.vue'
import Account from '@/views/Account.vue'  
import Cart from '@/views/Cart.vue'
import SignIn_SignUp from '@/views/SignIn_SignUp.vue' 
import Axios from 'axios'


Vue.use(VueRouter)

Axios.defaults.baseURL = 'http://localhost:8000/api/';

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/account',
    name: 'Dashboard',
    component: Dashboard
  },
  {
    path: '/user_account',
    name: 'Account',
    component: Account
  },
  {
    path: '/register',
    name: 'Register',
    component: Register,
  },
  {
    path: '/login_or_register',
    name: 'Login_or_Register',
    component: SignIn_SignUp,
  },
  {
    path: '/cart',
    name: 'Cart',
    component: Cart,
  }
]

const router = new VueRouter({
  routes
})

export default router

