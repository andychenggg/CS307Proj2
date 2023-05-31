<template xmlns="">
  <div class="wrapper">
    <ForumHeader class="forum_header"></ForumHeader>
    <div class="content-wrapper">
      <div class="leftTab-wrapper">
        <LeftTab @page-change="handlePageChange"></LeftTab>
      </div>
      <div class="postContainer-wrapper">
        <PostContainer v-if="isHomePage"></PostContainer>
        <HotSearchContainer v-if="isHotPost"></HotSearchContainer>
        <PostArticle v-if="isPost"></PostArticle>
        <SharePostContainer v-if="isShare"></SharePostContainer>
        <LikePostContainer v-if="isLike"></LikePostContainer>
        <FavoritePostContainer v-if="isFavorite"></FavoritePostContainer>
        <YourPostContainer v-if="isYourPost"></YourPostContainer>
        <YourReplyPostContainer v-if="isYourReplyPost"></YourReplyPostContainer>
        <User :users="usersData" v-if="isFollow"></User>
        <div v-if="isFollow" style="display: flex; justify-content: center">

          <el-button type="primary" round style="width: 200px; height: 40px; margin: 20px"
                     @click="fetchUserData" v-if="!noMoreFollowIg">To See More...
          </el-button>
          <span v-else style="height: 40px">我可是有底线的</span>
        </div>
        <Comment v-if="isComment" :CommentData="CommentData"></Comment>
        <SearchFrame v-if="isSearch"></SearchFrame>
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
import SharePostContainer from "@/components/SharePostContainer.vue";
import LikePostContainer from "@/components/LikePostContainer.vue";
import FavoritePostContainer from "@/components/FavoritePostContainer.vue";
import YourPostContainer from "@/components/YourPostContainer.vue";

import SearchResult from "@/components/SearchResult.vue";
import SearchFrame from "@/components/SearchFrame.vue";

import YourReplyPostContainer from "@/components/YourReplyPostContainer.vue";
import HotSearchPost from "@/components/HotSearchPost.vue";
import HotSearchContainer from "@/components/HotSearchContainer.vue";


export default {
  components: {
    HotSearchPost,
    ForumHeader,
    LeftTab,
    PostContainer,
    PostArticle,
    User,
    Post,
    Comment,
    SharePostContainer,
    LikePostContainer,
    FavoritePostContainer,
    YourPostContainer,

    SearchFrame,

    YourReplyPostContainer,
    HotSearchContainer,

  },
  data() {
    return {
      currentPage: 'homepage',
      CommentData: [],

      usersData: [],
      controlByIsFollow: true,
      noMoreFollowIg: false,


    };
  },
  computed: {
    isHomePage() {
      return this.currentPage === 'homepage';
    },
    isHotPost() {
      return this.currentPage === 'hotpost';
    },
    isPost() {
      return this.currentPage === 'post';
    },
    isFollow() {
      if (this.currentPage === 'follow' && this.controlByIsFollow) {
        this.controlByIsFollow = false;
        this.fetchUserData();
      }
      return this.currentPage === 'follow';
    },
    isShare() {
      console.log(this.currentPage === 'yourShare');
      return this.currentPage === 'yourShare';
    },
    isLike() {
      console.log(this.currentPage === 'yourLike');
      return this.currentPage === 'yourLike';
    },
    isFavorite() {
      return this.currentPage === 'yourFavorites';
    },
    isYourPost() {
      return this.currentPage === 'yourPost';
    },
    isYourReplyPost() {
      return this.currentPage === 'yourReplyPost';
    },
    isComment() {
      if (this.currentPage === 'yourComment') {
        this.fetchCommentData();
      }
      return this.currentPage === 'yourComment';
    },
    isSearch(){
      return this.currentPage === 'search'
    }

  },
  watch :{
    currentPage(newVal, oldVal){
      if(newVal !== oldVal && newVal === 'follow'){
        console.log('watch')
        this.fetchUserData();
      }
    }
  },
  mounted() {
    if (this.isFollow) {
      this.fetchUserData();
    }

  },
  methods: {
    handlePageChange(newPage) {
      this.currentPage = newPage;
      console.log(newPage)
    },
    fetchUserData() {
      if (!this.noMoreFollowIg) {
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
              if (response.data.length < 100) {
                this.noMoreFollowIg = true;
              }
              this.usersData = this.usersData.concat(response.data);
              console.log(this.usersData.at(0));
            })
            .catch(error => {
              // 处理请求失败的错误
              console.error(error);
            });
      }

    },

    fetchCommentData() {
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
  height: 90%;
  width: 100%;
  z-index: 0;
}

.forum_header {
//position: fixed; background-color: #ffffff; z-index: 0;
}

.leftTab-wrapper {
//margin-top: 5%; height: 100%; //position: fixed;
}


.postContainer-wrapper {
//margin-top: 5%; //margin-left: 20%; width: 80%; height: 100%;
  overflow: auto;
  z-index: 5;
}

.postPost-wrapper {
  height: 100%;
}

.User-wrapper {
  height: 100%;
}

</style>
