<template>
  <div id="app">
<!--    <Login></Login>-->
<!--    <router-link to="/TopTrend">Go to Top Trend</router-link>-->
<!--&lt;!&ndash;    声明路由占位标签&ndash;&gt;-->
    <router-view></router-view>
  </div>
</template>

<script>
  import Login from './components/Login.vue'
  import HomePage from "@/components/HomePage.vue";
  import Signup from "@/components/Signup.vue";
  import UserHomePage from "@/components/UserHomePage.vue";
  import TopTrend from './components/TopTrend.vue';
  import Post from '@/components/Post.vue';
  import postDetail from '@/components/PostDetail.vue';
export default {
  name: 'App',
  components: {
      Login,
      HomePage,
      Signup,
      UserHomePage,
      Post,
      postDetail
    // TopTrend
  }
}
</script>

<style>
#app {
    width: 100%;
    height: 100%;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0;
}
body, html {
      height: 100%;
      width: 100%;
      margin: 0;
      padding: 0;
      display: flex;
      justify-content: center;
      align-items: center;
      background-color: #f6f8fa;
      font-family: Arial, sans-serif;
}

</style>
