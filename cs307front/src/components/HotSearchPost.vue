<template>
  <div class="container" style="height: auto;overflow: visible;">
    <el-tag type="warning" style="font-size: 20px; width: 120px;">
      <i class="el-icon-sunrise-1"></i>
      hot: {{ post.hot }}
    </el-tag>
    <div style="height: 60px; width: 100%">
      <div
          style="display: flex; justify-content: flex-start; width: 50%; height: 100%; margin-left: 20px; align-items: center; ">
        <el-tag style="margin-right: 10px;">Sender:</el-tag>
        <el-tag type="success" style="margin-right: 10px">{{ post.senderName }}</el-tag>
        <el-switch
            v-model="followSender"
            active-text="follow"
            inactive-text="unfollow" v-if="post.authorName!==post.senderName">
        </el-switch>
      </div>
    </div>
    <div style="height: 60px; width: 100%">
      <div
          style="display: flex; justify-content: flex-start; width: 50%; height: 100%; margin-left: 20px; align-items: center;">
        <el-tag style="margin-right: 10px">Author:</el-tag>
        <el-tag type="success" style="margin-right: 10px">{{ post.authorName }}</el-tag>
        <el-switch
            v-model="followAuthor"
            active-text="follow"
            inactive-text="unfollow">
        </el-switch>
      </div>
    </div>
    <div class="title">
      <label>{{ post.title }}</label>
    </div>
    <div v-for="cate in post.postCategories" :key="cate" style="display: flex; justify-content: flex-start; width: 90%">
      {{ cate }}
    </div>
    <div style="display: flex; justify-content: center; width: 90%; text-align: center">
      {{ "Post at: " + post.postingTime + ", " + post.city + ", " + post.country }}
    </div>
    <div class="content">
      <div class="content-textarea">{{ post.content }}</div>
      <!--      <textarea class="content-textarea" placeholder="content"></textarea>-->
    </div>
    <div class="details">
      <div style="width: 25%; display: flex; justify-content: center">
        <el-button
            type="primary"
            icon="el-icon-share"
            @click="toggleShare"
            class="el-btn"
            v-if="!isShared"
        >share
        </el-button>
        <el-button
            type="danger"
            icon="el-icon-share"
            @click="toggleUnShare"
            class="el-btn"
            v-if="isShared"
        >unshare
        </el-button>
      </div>
      <div style="width: 25%; display: flex; justify-content: center">
        <el-button type="primary" icon="el-icon-s-comment" @click="toggleContent" class="el-btn">comments</el-button>
      </div>
      <div style="width: 25%; display: flex; justify-content: center">
        <el-button
            type="primary"
            icon="el-icon-star-on"
            @click="toggleFavorite"
            class="el-btn"
            v-if="!isFavorited"
        >favorite
        </el-button>
        <el-button
            type="danger"
            icon="el-icon-star-on"
            @click="toggleUnFavorite"
            class="el-btn"
            v-if="isFavorited"
        >unfavorite
        </el-button>
      </div>
      <div style="width: 25%; display: flex; justify-content: center">
        <el-button
            type="primary"
            icon="el-icon-magic-stick"
            @click="toggleLike"
            class="el-btn"
            v-if="!isLiked"
        >like
        </el-button>
        <el-button
            type="danger"
            icon="el-icon-magic-stick"
            @click="toggleUnLike"
            class="el-btn"
            v-if="isLiked"
        >unlike
        </el-button>

      </div>
    </div>
    <div v-show="showContent">
      <!--      <PostDetail label="postDetail"></PostDetail>-->
      <NewComment :replies="replyData" :current-post-id="post.postId"></NewComment>
    </div>

  </div>
</template>

<script>
import PostDetail from "@/components/PostDetail.vue";
import NewComment from "@/components/NewComment.vue";
import axios, {post} from "axios";

export default {
  components: {
    PostDetail,
    NewComment
  },
  data() {
    return {
      showContent: false,
      followSender: this.senderIsFollowed,
      followAuthor: this.authorIsFollowed,
      replyData: [],
      isLiked: this.isLike,
      isFavorited: this.isFavorite,
      isShared: this.isShare,
    };
  },
  watch: {
    followSender: {
      handler(newVal) {
        // 当followSender变化时，调用fetchData方法
        if (newVal) {
          this.toggleFollowSender();
        } else {
          this.toggleUnFollowSender();
        }
      }
    },
    followAuthor: {
      handler(newVal) {
        // 当followAuthor变化时，调用fetchData方法
        if (newVal) {
          this.toggleFollowAuthor();
        } else {
          this.toggleUnFollowAuthor();
        }
      }
    },
  },
  methods: {
    toggleContent() {
      this.showContent = !this.showContent;
      if (this.showContent) {
        axios.get('http://localhost:9090/user/homepage/post/replies', {
          params: {
            id: this.post.postId
          }
        })
            .then(response => {
              console.log(response.data)
              this.replyData = response.data; // 将响应数据赋值给comments数组
            })
            .catch(error => {
              console.error(error);
            });
      }
    },
    toggleFollowAuthor() {
      axios.post('http://localhost:9090/user/follow', {
        followigId: this.post.authorId
      }, {
        withCredentials: true
      })
          .then(response => {
            console.log(response.data); // 将响应数据赋值给comments数组
          })
          .catch(error => {
            console.error(error);
          });
    },
    toggleUnFollowAuthor() {
      axios.delete('http://localhost:9090/user/follow', {
        params: {
          followigid: this.post.authorId,
        }
      },)
          .then(response => {
          })
          .catch(error => {
            console.error(error);
          });
    },
    toggleFollowSender() {
      axios.post('http://localhost:9090/user/follow', {
        followigId: this.post.senderId
      }, {
        withCredentials: true
      })
          .then(response => {
            console.log(response.data); // 将响应数据赋值给comments数组
          })
          .catch(error => {
            console.error(error);
          });
    },
    toggleUnFollowSender() {
      axios.delete('http://localhost:9090/user/follow', {
        params: {
          followigid: this.post.senderId,
        }
      },)
          .then(response => {
            console.log("hello");
          })
          .catch(error => {
            console.error(error);
          });
    },
    toggleShare() {
      axios.post('http://localhost:9090/user/share', {
        postId: this.post.postId,
        shareId: 0
      }, {
        withCredentials: true
      })
          .then(response => {
          })
          .catch(error => {
            console.error(error);
          });
      this.isShared = !this.isShared;
      this.post.hot += 4;
    },
    toggleUnShare() {
      axios.delete('http://localhost:9090/user/share', {
        params: {
          postId: this.post.postId,
        }
      },)
          .then(response => {
          })
          .catch(error => {
            console.error(error);
          });
      this.isShared = !this.isShared;
      this.post.hot -= 4;
    },
    toggleLike() {
      axios.post('http://localhost:9090/user/like', {
        postId: this.post.postId,
        likerId: 0
      }, {
        withCredentials: true
      })
          .then(response => {
          })
          .catch(error => {
            console.error(error);
          });
      this.isLiked = !this.isLiked;
      this.post.hot += 2;
    },
    toggleUnLike() {
      axios.delete('http://localhost:9090/user/like', {
        params: {
          postId: this.post.postId,
        }
      },)
          .then(response => {
          })
          .catch(error => {
            console.error(error);
          });
      this.isLiked = !this.isLiked;
      this.post.hot -= 2;
    },
    toggleFavorite() {
      axios.post('http://localhost:9090/user/favor', {
        postId: this.post.postId,
        favorId: 0
      }, {
        withCredentials: true
      })
          .then(response => {
          })
          .catch(error => {
            console.error(error);
          });
      this.isFavorited = !this.isFavorited;
      this.post.hot += 3;
    },
    toggleUnFavorite() {
      axios.delete('http://localhost:9090/user/favorite', {
        params: {
          postId: this.post.postId,
        }
      },)
          .then(response => {
          })
          .catch(error => {
            console.error(error);
          });
      this.isFavorited = !this.isFavorited;
      this.post.hot -= 3;
    }

  },
  props: {
    post: {
      type: Object,
      require: true,
      default: () => ({
        postId: 0,
        title: '',
        content: '',
        postingTime: '',
        authorId: 0,
        senderId: 0,
        city: '',
        country: '',
        anonymous: false,
        isSenderAnonymous: false,
        postCategories: [],
        authorName: '',
        senderName: '',
        hot: 0,
      })
    },
    authorIsFollowed: {
      type: Boolean
    },
    senderIsFollowed: {
      type: Boolean
    },
    isLike: {
      type: Boolean
    },
    isFavorite: {
      type: Boolean
    },
    isShare: {
      type: Boolean
    }
  },
};
</script>

<style scoped>
.container {
  border: 1px solid #ccc;
  padding: 10px;
}

.title {
  font-weight: bold;
  margin-bottom: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
}


.content {
  margin-bottom: 10px;
  width: 100%;
  display: flex;
  justify-content: center;
  word-wrap: break-word;
}

.details {
  display: flex;
  width: 100%;
  text-align: center;
  margin-bottom: 10px;
}

.container {
  border: 1px solid #ccc;
  padding: 10px;
  display: flex;
  flex-direction: column;
}

.content-textarea {
  width: 90%;
  min-height: 50px;
  padding: 5px;
  border: 1px solid #ccc;
  resize: vertical; /* 可垂直调整大小 */
  word-wrap: break-word;
  text-align: justify;
}

.el-btn {
  width: 130px;
}

.top {
  text-align: center;
  margin-bottom: 10px;
}

.bottom {
  text-align: center;
  margin-top: 10px;
}

</style>