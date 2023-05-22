<template>
    <div class="container">
        <div class="main-content" id="content">

            <Post v-for="post in postData" :key="post.postId" :post="post"></Post>
            <el-button type="primary" round style="width: 200px; height: 40px; margin: 20px"
                       @click="fetchPostData" v-if="!noMorePost">To See More...
            </el-button>
            <span v-else style="height: 40px">我可是有底线的</span>
        </div>
    </div>
</template>

<script>
import Vue from 'vue';
import Post from "@/components/Post.vue";
import axios from "axios";

export default {
    data() {
        return {
            lastPostId: -1,
            postData: [],
            controlByIsHomepage: true,
            noMorePost: false,
            busy: false
        }
    },
    components: {
        Post
    },
    created() {
        // 组件创建时要执行的操作
        console.log('PostContainer created!');
        this.fetchPostData();

    },
    methods: {
        async fetchPostData() {

            if (!this.noMorePost && !this.busy) {
                this.busy = true;
                axios.get('http://localhost:9090/homepage/post', {
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
                    if (response.data.length < 50) {
                        this.noMorePost = true;
                    }
                    this.busy = false;
                })
                    .catch(error => {
                        // 处理错误
                        console.error(error);
                    });
            }
        }
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
