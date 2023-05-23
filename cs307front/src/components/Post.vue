<template>
  <div class="container">
    <div style="height: 60px; width: 100%">
      <div style="display: flex; justify-content: flex-start; width: 50%; height: 100%; margin-left: 20px; align-items: center; ">
        <el-tag style="margin-right: 10px;" >Sender: </el-tag>
        <el-tag type="success" style="margin-right: 10px">{{post.senderName}}</el-tag>
        <el-switch
                v-model="followSender"
                active-text="unfollow"
                inactive-text="follow" v-if="post.authorName!==post.senderName">
        </el-switch>
        <el-switch
                v-model="followAuthor"
                active-text="unfollow"
                inactive-text="follow" v-if="post.authorName===post.senderName">
        </el-switch>
      </div>
    </div>
    <div style="height: 60px; width: 100%">
      <div style="display: flex; justify-content: flex-start; width: 50%; height: 100%; margin-left: 20px; align-items: center;">
        <el-tag style="margin-right: 10px">Author: </el-tag>
        <el-tag type="success" style="margin-right: 10px">{{post.authorName}}</el-tag>
        <el-switch
                v-model="followAuthor"
                active-text="unfollow"
                inactive-text="follow" >
        </el-switch>
      </div>
    </div>
    <div class="title">
      <label>{{ post.title }}</label>
    </div>
    <div v-for="cate in post.postCategories" :key="cate" style="display: flex; justify-content: flex-start; width: 90%">></div>
    <div style="display: flex; justify-content: flex-start; width: 90%">{{"Post at: "+post.postingTime}}</div>
    <div class="content">
      <div class="content-textarea" >{{ post.content }}</div>
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
      <NewComment :replies="replyData" :current-post-id="post.postId"></NewComment>
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
  watch: {
    followSender: {
      handler(newVal) {
        // 当followSender变化时，调用fetchData方法
        if(newVal){

        }
      },
      immediate: true // 在组件初始化时立即执行一次
    },
    followAuthor: {
      handler(newVal) {
        // 当followAuthor变化时，调用fetchData方法
        if(newVal){

        }
      },
      immediate: true // 在组件初始化时立即执行一次
    }
  },
  methods: {
    toggleContent() {
      this.showContent = !this.showContent;
      if(this.showContent){
        axios.get('http://localhost:9090/user/homepage/replies')
                .then(response => {
                  this.replyData = response.data; // 将响应数据赋值给comments数组
                })
                .catch(error => {
                  console.error(error);
                });
      }
    },
    followAuthor(){
      axios.post('http://localhost:9090/user/like', {
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
    followSender(){
      axios.post('http://localhost:9090/user/like', {
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
        postCategories: [],
        authorName: '',
        senderName: '',
      })
    },
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