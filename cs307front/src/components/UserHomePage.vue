<template>
  <div class="wrapper">
    <ForumHeader class="forum_header"></ForumHeader>
    <div class="content-wrapper">
      <div class="leftTab-wrapper">
        <LeftTab @page-change="handlePageChange"></LeftTab>
      </div>
      <div class="postContainer-wrapper">
        <PostContainer v-if="isHomePage" :postData="postData"></PostContainer>
        <PostArticle v-if="isPost"></PostArticle>
        <User :users = "usersData" v-if="isFollow"></User>
        <div v-if="isFollow" style="display: flex; justify-content: center">

          <el-button type="primary" round style="width: 200px; height: 40px; margin: 20px" @click="fetchUserData" v-if="!noMoreFollowIg">To See More...</el-button>
          <span v-else style="height: 40px">我可是有底线的</span>
        </div>
        <Comment v-if="isComment" :CommentData="CommentData"></Comment>
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
import Comment from "@/components/Comment.vue";

export default {
  components: {
    ForumHeader,
    LeftTab,
    PostContainer,
    PostArticle,
    User,
    Post,
    Comment
  },
  data() {
    return {
      currentPage: 'homepage',
      CommentData: [],
      usersData:[],
      controlByIsFollow: true,
      noMoreFollowIg: false,
      lastPostId: -1,
      postData: []
    };
  },
  computed: {
    isHomePage() {
      if(this.currentPage === 'homepage'){
        this.fetchPostData();
      }
      return this.currentPage === 'homepage';
    },
    isPost() {
      return this.currentPage === 'post';
    },
    isFollow() {
      if(this.currentPage === 'follow' && this.controlByIsFollow) {
        this.controlByIsFollow = false;
        this.fetchUserData();
      }
      return this.currentPage === 'follow';
    },
    isComment(){
      console.log(document.cookie);
      if(this.currentPage === 'yourComment'){
        this.fetchCommentData();
      }
      return this.currentPage === 'yourComment';
    }

  },
  mounted() {
    if(this.isFollow){
      this.fetchUserData();
    }
  },
  methods: {
    handlePageChange(newPage) {
      this.currentPage = newPage;
      console.log(newPage)
    },

    fetchUserData() {
      if(!this.noMoreFollowIg){
        console.log("fetchUserData");
        axios.get("http://localhost:9090/user/follow", {
          params: {
            offset: this.usersData.length,
            limit: 100
          },
          withCredentials: true
        })
                .then(response => {
                  // 处理请求成功的响应数据
                  if(response.data.length < 100){
                    this.noMoreFollowIg = true;
                  }
                  this.usersData = this.usersData.concat(response.data);
                })
                .catch(error => {
                  // 处理请求失败的错误
                  console.error(error);
                });
      }

    },
    fetchPostData(){
      axios.get('http://localhost:9090/homepage/post', {
        params: {
          lastPostId: -1,
          limit: 50
        },
        withCredentials: true
      }).then(response => {
        // 处理响应
        console.log(response.data)
        this.postData = response.data;
        console.log("here");
        console.log(document.cookie);
      })
              .catch(error => {
                // 处理错误
                console.error(error);
              });
    },
    fetchCommentData(){
      axios.get('http://localhost:9090/user/homepage/replies', {
        withCredentials: true
      }).then(response => {
        // 处理响应
        this.CommentData = response.data;
        console.log("here");
        console.log(document.cookie);
      })
              .catch(error => {
                // 处理错误
                console.error(error);
              });
    }
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

.forum_header{
  //position: fixed;
  background-color: #ffffff;
}
.leftTab-wrapper {
  //margin-top: 5%;
  height: 100%;
  //position: fixed;
}


.postContainer-wrapper {
  //margin-top: 5%;
  //margin-left: 20%;
  width: 80%;
  height: 100%;
  overflow: auto;
}

.postPost-wrapper {
  height: 100%;
}

.User-wrapper {
  height: 100%;
}

</style>
