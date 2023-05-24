<template>
  <div class="container">
    <div class="main-content" id="content">

      <Post v-for="post in postData" :key="post.postId" :post="post"
            :authorIsFollowed="isAuthorFollowed(post.authorId)"
            :senderIsFollowed="isSenderFollowed(post.senderId)"
            :isLike="isLiked(post.postId)"
            :isFavorite="isFavorite(post.postId)"
            :isShare="isShared(post.postId)"></Post>
      <el-button type="primary" round style="width: 200px; height: 40px; margin: 20px"
                 @click="fetchLikeData" v-if="!noMorePost">To See More...
      </el-button>
      <span v-else style="height: 40px">我可是有底线的</span>
    </div>
  </div>
</template>

<script>
import Vue, {watch} from 'vue';
import Post from "@/components/Post.vue";
import axios from "axios";

export default {
  data() {
    return {
      lastPostId: -1,
      postData: [],
      followigId: [],
      likedPostId: [],
      favoritePostId: [],
      sharePostId: [],
      controlByIsHomepage: true,
      noMorePost: false,
      busy: false
    }
  },
  components: {
    Post
  },
  mounted() {
    // 组件创建时要执行的操作
    console.log('FavoritePostContainer created!');
    axios.get('http://localhost:9090/user/follow/ids')
        .then(response => {
          // 处理请求成功的响应数据
          this.followigId = response.data;
          console.log("followigId" + response.data);
        })
        .catch(error => {
          // 处理请求失败的情况
          console.error(error);
        });
    axios.get('http://localhost:9090/user/like/ids')
        .then(response => {
          // 处理请求成功的响应数据
          this.likedPostId = response.data;
          console.log("likedPostId" + response.data);
        })
        .catch(error => {
          // 处理请求失败的情况
          console.error(error);
        });
    axios.get('http://localhost:9090/user/favorite/ids')
        .then(response => {
          // 处理请求成功的响应数据
          this.favoritePostId = response.data;
          console.log("favoritePostId" + response.data);
        })
        .catch(error => {
          // 处理请求失败的情况
          console.error(error);
        });
    axios.get('http://localhost:9090/user/share/ids')
        .then(response => {
          // 处理请求成功的响应数据
          this.sharePostId = response.data;
          console.log("favoritePostId" + response.data);
        })
        .catch(error => {
          // 处理请求失败的情况
          console.error(error);
        });
    this.fetchFavoriteData();
  },
  methods: {
    isAuthorFollowed(authorId) {
      return this.followigId.includes(authorId);
    },
    isSenderFollowed(senderId) {
      return this.followigId.includes(senderId);
    },
    isLiked(postId) {
      return this.likedPostId.includes(postId);
    },
    isFavorite(postId) {
      return this.favoritePostId.includes(postId);
    },
    isShared(postId){
      return this.sharePostId.includes(postId);
    },
    async fetchFavoriteData() {
      console.log("this.lastPostId"+this.lastPostId);
      if (!this.noMorePost && !this.busy) {
        this.busy = true;
        axios.get('http://localhost:9090/homepage/favorite', {
          params: {
            lastPostId: this.lastPostId,
            limit: 50
          },
          withCredentials: true
        }).then(response => {
          // 处理响应
          console.log(response.data)
          this.postData = this.postData.concat(response.data);
          this.lastPostId = this.postData[this.postData.length - 1]["postId"];
          if (response.data.length === 0) {
            this.noMorePost = true;
          }
          this.busy = false;
        })
            .catch(error => {
              // 处理错误
              console.error(error);
            });
      }
    },
  }
};
</script>

<style scoped>
.container {
  width: 100%;
  display: flex;
  flex-direction: column;
}

.main-content {
  overflow-y: auto;
  height: 90%;
  border: 10px solid #ccc;
}

</style>
