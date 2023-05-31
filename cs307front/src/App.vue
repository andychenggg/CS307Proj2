<template>
  <div id="app">
<!--    <Login></Login>-->
<!--    <router-link to="/TopTrend">Go to Top Trend</router-link>-->
<!--&lt;!&ndash;    声明路由占位标签&ndash;&gt;-->
      <div class="background">
          <div ref='vantaRef' style="width: 100%; height: 100%">

          </div>
      </div>
    <router-view class="router"></router-view>
  </div>
</template>

<script>
  import Login from './components/Login.vue'
  import HomePage from "@/components/HomePage.vue";
  import Signup from "@/components/Signup.vue";
  import UserHomePage from "@/components/UserHomePage.vue";
  import Post from '@/components/Post.vue';
  import postDetail from '@/components/PostDetail.vue';
  import NewComment from "@/components/NewComment.vue";
  import NET from "vanta/src/vanta.net";
  import CLOUDS from "vanta/src/vanta.clouds";
  import * as THREE from "three";
export default {
  name: 'App',
  components: {
      Login,
      HomePage,
      Signup,
      UserHomePage,
      Post,
      postDetail,
      NewComment
    // TopTrend
  },
    mounted() {
        this.vantaEffect = NET({
            el: this.$refs.vantaRef,
            THREE: THREE,
            mouseControls: true,
            touchControls: true,
            gyroControls: false,
            minHeight: 200.00,
            minWidth: 200.00,
            scale: 1.00,
            scaleMobile: 1.00,
            color: 0x496fe8,
            backgroundColor: 0xebe9ed
        })
    },
    beforeDestroy() {
        if (this.vantaEffect) {
            this.vantaEffect.destroy()
        }
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
.background{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 0;
}
.router{
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 1;
}

</style>
