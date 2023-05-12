import VueRouter from "vue-router";
import Vue from "vue";
import TopTrend from '../components/TopTrend.vue'
import Login from "@/components/Login.vue";
import Signup from "@/components/Signup.vue";
import HomePage from "@/components/HomePage.vue";
import UserHomePage from "@/components/UserHomePage.vue";
Vue.use(VueRouter)

const router = new VueRouter({

    routes: [
        { path: '/', redirect: '/login'},
        { path: '/TopTrend', component: TopTrend},
        { path: '/login', component: Login },  // 新增这一行
        { path: '/signup', component: Signup },  // 新增这一行
        { path: '/homepage', component: HomePage},
        {
            path: '/user/homepage', component: UserHomePage
        }
    ]
})

export default router