<template>
  <div class="container">
    <div style="height: 60px; width: 100%">
      <div style="display: flex; justify-content: flex-start; width: 50%; height: 100%; margin-left: 20px; align-items: center;">
        <el-tag style="margin-right: 10px;">Sender:</el-tag>
        <el-tag type="success" style="margin-right: 10px">{{ post.senderName}}</el-tag>
        <el-switch v-model="followSender" active-text="unfollow" inactive-text="follow" @change = "toggleFollowSender"></el-switch>
      </div>
    </div>
    <div style="height: 60px; width: 100%">
      <div style="display: flex; justify-content: flex-start; width: 50%; height: 100%; margin-left: 20px; align-items: center;">
        <el-tag style="margin-right: 10px;">Author:</el-tag>
        <el-tag type="success" style="margin-right: 10px">{{ post.authorName }}</el-tag>
        <el-switch v-model="followAuthor" active-text="unfollow" inactive-text="follow" @change = "toggleFollowAuthor"></el-switch>
      </div>
    </div>
    <div class="username">
      <label>{{ post.title }}</label>
    </div>
    <div class="content">
      <pre class="content-textarea" readonly>{{ post.content }}</pre>
      <!-- <textarea class="content-textarea" placeholder="content"></textarea> -->
    </div>
    <div class="details">
      <div style="width: 25%; display: flex; justify-content: center">
        <el-button type="primary" icon="el-icon-share" class="el-btn">share</el-button>
      </div>
      <div style="width: 25%; display: flex; justify-content: center">
        <el-button type="primary" icon="el-icon-s-comment" @click="toggleContent" class="el-btn">comments</el-button>
      </div>
      <div style="width: 25%; display: flex; justify-content: center">
        <el-button type="primary" icon="el-icon-collection-tag" class="el-btn">favorite</el-button>
      </div>
      <div style="width: 25%; display: flex; justify-content: center">
        <el-button type="primary" icon="el-icon-magic-stick" class="el-btn">like</el-button>
      </div>
    </div>
    <div v-show="showContent">
      <!-- <PostDetail label="postDetail"></PostDetail> -->
      <NewComment :replies="replyData"></NewComment>
    </div>
  </div>
</template>

<script>
import PostDetail from "@/components/PostDetail.vue";
import NewComment from "@/components/NewComment.vue";
import axios from "axios";

export default {
  props: {
    post: {
      type: Object,
      required: true,
    },
  },
  components: {
    PostDetail,
    NewComment,
  },
  data() {
    return {
      showContent: false,
      followSender: false,
      followAuthor: false,
      replyData: [],
      posts: [],
    };
  },
  methods: {
    toggleFollowSender() {
      // 发送向后端的请求
      if (this.followSender) {
        // 当开关打开时的操作
        // 发送关注发送者的请求
        axios.post("http://localhost:9090/user/follow", {
          userId: 0,
          followigId: this.post.senderId,
        }, {
          withCredentials: true
        })
            .then(response => {
              // 处理响应
            })
            .catch(error => {
              // 处理错误
              console.error(error);
            });
      } else {
        // 当开关关闭时的操作
        // 发送取消关注发送者的请求
        axios.delete("http://localhost:9090/user/follow", {
          params: {
            followigid: this.post.senderId,
          }
        })
            .then(response => {
              // 处理响应
            })
            .catch(error => {
              // 处理错误
              console.error(error);
            });
      }
    },
    toggleFollowAuthor() {
      // 发送向后端的请求
      if (this.followAuthor) {
        // 当开关打开时的操作
        // 发送关注发送者的请求
        axios.post("http://localhost:9090/user/follow", {
          userId: 0,
          followigId: this.post.authorId,
        }, {
          withCredentials: true
        })
            .then(response => {
              // 处理响应
            })
            .catch(error => {
              // 处理错误
              console.error(error);
            });
      } else {
        // 当开关关闭时的操作
        // 发送取消关注发送者的请求
        axios.delete("http://localhost:9090/user/follow", {
          params: {
            followigid: this.post.authorId,
          }
        })
            .then(response => {
              // 处理响应
            })
            .catch(error => {
              // 处理错误
              console.error(error);
            });
      }
    },
  },
};
</script>

<style scoped>
.container {
  border: 1px solid #ccc;
  padding: 10px;
}

.username {
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
  width: 100%;
  min-height: 50px;
  padding: 5px;
  border: 1px solid #ccc;
  resize: vertical; /* 可垂直调整大小 */
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