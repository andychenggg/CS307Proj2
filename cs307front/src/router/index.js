import VueRouter from "vue-router";
import Vue from "vue";
import TopTrend from '../components/TopTrend.vue'
import Login from "@/components/Login.vue";
import Signup from "@/components/Signup.vue";
import HomePage from "@/components/HomePage.vue";
import UserHomePage from "@/components/UserHomePage.vue";
Vue.use(VueRouter)

const router = new VueRouter({
    mode: 'history',
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

import axios from 'axios';
router.beforeEach((to, from, next) => {
    if (to.path === '/login' || to.path === '/signup' || to.path === '/homepage') {
        // 向目标服务器发送 GET 请求
        axios.get('http://localhost:9090/login', {
            withCredentials: true
        })
            .then(response => {
                console.log(response.data);
                const data = response.headers;
                if (data['request-login'] === 'pass') {
                    // 重定向到 /user/homepage
                    next('/user/homepage');
                } else {
                    // 其他处理逻辑，比如显示错误消息
                    next();
                }
            })
            .catch(error => {
                // 错误处理逻辑
                next();
            });
    } else if (to.path.startsWith('/user/') ){
        axios.get('http://localhost:9090/login', {
            withCredentials: true
        })
            .then(response => {
                console.log(response.data);
                const data = response.headers;
                if (data['request-login'] === 'failed') {
                    // 重定向到 /user/homepage

                    next('/homepage');
                } else {
                    // 其他处理逻辑，比如显示错误消息
                    next();
                }
            })
            .catch(error => {
                // 错误处理逻辑
                next();
            });
    }
});
export default router