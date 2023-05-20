<template>
  <div class="wrapper">
    <ForumHeader></ForumHeader>
    <div class="content-wrapper">
      <div class="leftTab-wrapper">
        <LeftTab @page-change="handlePageChange"></LeftTab>
      </div>
      <div class="postContainer-wrapper">
        <PostContainer v-if="isHomePage"></PostContainer>
        <PostArticle v-if="isPost"></PostArticle>
        <User :users = "usersData" v-if="isFollow"></User>
      </div>
    </div>

  </div>
</template>

<script>
import ForumHeader from './ForumHeader.vue';
import LeftTab from './LeftTab.vue';
import PostContainer from './PostContainer.vue';
import PostArticle from './PostArticle.vue';
import User from './User.vue';
import Post from './Post.vue';
import axios from "axios";

export default {
  components: {
    ForumHeader,
    LeftTab,
    PostContainer,
    PostArticle,
    User,
    Post
  },
  data() {
    return {
      currentPage: 'homepage',
      usersData:[],
    };
  },
  computed: {
    isHomePage() {
      return this.currentPage === 'homepage';
    },
    isPost() {
      return this.currentPage === 'post';
    },
    isFollow() {
      this.fetchUserData();
      return this.currentPage === 'follow';
    },

  },
  mounted() {
    this.fetchUserData();
  },
  methods: {
    handlePageChange(newPage) {
      this.currentPage = newPage;
      console.log(newPage)
    },

    fetchUserData() {
      axios.get("http://localhost:9090/user/follow")
          .then(response => {
            // 处理请求成功的响应数据
            this.usersData = response.data;
          })
          .catch(error => {
            // 处理请求失败的错误
            console.error(error);
          });
    },
  }
};
</script>

<style>
body {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
}

.wrapper {
  width: 100%;
  height: 100%;
}

.content-wrapper {
  display: flex;
  flex-direction: row;
  height: 100vh;
  width: 100%;
}

.leftTab-wrapper {
  height: 100%;
}


.postContainer-wrapper {
  width: 120%;
  height: 100%;
}

.postPost-wrapper {
  height: 100%;
}

.User-wrapper {
  height: 100%;
}

</style>
