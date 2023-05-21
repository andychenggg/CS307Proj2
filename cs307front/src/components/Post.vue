<template>
  <div class="container">
    <div style="height: 60px; width: 100%">
      <div style="display: flex; justify-content: flex-start; width: 50%; height: 100%; margin-left: 20px; align-items: center; ">
        <el-tag style="margin-right: 10px;" >Sender: </el-tag>
        <el-tag type="success" style="margin-right: 10px">hello world</el-tag>
        <el-switch
                v-model="followSender"
                active-text="unfollow"
                inactive-text="follow">
        </el-switch>
      </div>
    </div>
    <div style="height: 60px; width: 100%">
      <div style="display: flex; justify-content: flex-start; width: 50%; height: 100%; margin-left: 20px; align-items: center;">
        <el-tag style="margin-right: 10px">Author: </el-tag>
        <el-tag type="success" style="margin-right: 10px">hello world</el-tag>
        <el-switch
                v-model="followAuthor"
                active-text="unfollow"
                inactive-text="follow">
        </el-switch>
      </div>
    </div>
    <div class="username">
      <label>{{ "helloWorld" }}</label>
    </div>
    <div class="content">
      <pre class="content-textarea" readonly>{{ "hello"}}</pre>
<!--      <textarea class="content-textarea" placeholder="content"></textarea>-->
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
<!--      <PostDetail label="postDetail"></PostDetail>-->
      <NewComment :replies="replyData"></NewComment>
    </div>

  </div>
</template>

<script>
import PostDetail from "@/components/PostDetail.vue";
import NewComment from "@/components/NewComment.vue";
import axios from "axios";

export default {
  components: {
    PostDetail,
    NewComment
  },
  data() {
    return {
      showContent: false,
      followSender: false,
      followAuthor: false,
      replyData: []
    };
  },
  methods: {
    toggleContent() {
      this.showContent = !this.showContent;
      axios.get('/user/homepage/replies')
              .then(response => {
                this.replyData = response.data; // 将响应数据赋值给comments数组
              })
              .catch(error => {
                console.error(error);
              });
    },
  },
  props: {
    label: {
      type: String,
      required: true,
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