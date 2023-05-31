<template>
    <el-container>
        <el-main >
            <el-row v-for="(input, index) in inputs" :key="index" :gutter=10 align="middle" type="flex"
                    style="margin-top: 20px;margin-left: 10px; display: flex; ustify-content: center">
                <el-col :span="20" >
                    <div style="display: flex; width: 1000px; justify-content: center">
                        <el-select v-model="input.key" placeholder="请选择" style="width: 200px; margin-right: 20px">
                            <el-option label="title" value="1"></el-option>
                            <el-option label="author" value="2"></el-option>
                            <el-option label="content" value="3"></el-option>
                            <el-option label="time" value="4"></el-option>
                            <el-option label="sender" value="5"></el-option>
                        </el-select>
                        <el-input v-model="input.value" class="input-with-select" v-if="input.key!=='4'"></el-input>
                        <div class="input-with-select" v-else>
                            <el-date-picker
                                    v-model="input.timeValue"
                                    type="daterange"
                                    align="right"
                                    unlink-panels
                                    range-separator="to"
                                    start-placeholder="begin date"
                                    end-placeholder="end date"
                                    :picker-options="pickerOptions">
                            </el-date-picker>
                        </div>
                    </div>
                </el-col>
                <el-col :span="4" style="width: 200px">
                    <el-button type="danger" icon="el-icon-delete" v-if="inputs.length > 1"  @click="removeInput(index) "></el-button>
                </el-col>
            </el-row>
        </el-main>
        <el-footer style="height: 10vh; margin-top: 2vh">
            <el-row type="flex" justify="center">
                <el-col :span="8">
                    <el-button type="primary" icon="el-icon-plus" @click="addInput"></el-button>
                </el-col>
                <el-col :span="15">
                    <el-button type="success" icon="el-icon-search" @click="search">Search Right now</el-button>
                </el-col>
            </el-row>

        </el-footer>
        <div class="container" style="height: 63vh">
            <div class="main-content" id="content">
                <Post v-for="post in postData" :key="post.postId" :post="post"
                      :authorIsFollowed="isAuthorFollowed(post.authorId)"
                      :senderIsFollowed="isSenderFollowed(post.senderId)"
                      :isLike="isLiked(post.postId)"
                      :isFavorite="isFavorite(post.postId)"
                      :isShare="isShared(post.postId)" style="width: 140vh;"></Post>
            </div>
        </div>

    </el-container>
</template>


<script>
    import axios from "axios";
    import SearchResult from "@/components/SearchResult.vue";
    import Post from "@/components/Post.vue";

    export default {
        components: {
            Post
        },
        data() {
            return {
                postData: [],
                followigId: [],
                likedPostId: [],
                favoritePostId: [],
                sharePostId: [],

                loading: false,
                inputs: [{
                    timeValue: [],
                    key: '',
                    value: '' }],
                pickerOptions: {
                    shortcuts: [{
                        text: '最近一周',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近一个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit('pick', [start, end]);
                        }
                    }, {
                        text: '最近三个月',
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit('pick', [start, end]);
                        }
                    }]
                },
            };
        },
        methods: {
            addInput() {
                this.inputs.push({ key: '', value: '' ,
                    timeValue1: 0, timeValue2: 0,});
            },
            removeInput(index) {
                this.inputs.splice(index, 1);
            },
            search() {
                if(this.inputs.length === 1 && this.inputs[0].key === ''){
                    console.log(this.inputs);
                    this.$message({
                        offset: 100,
                        message: '请输入搜索条件',
                        type: 'warning'
                    });
                    return;
                }
                this.loading = true;
                console.log('Search:');
                console.log(this.inputs);
                axios.post('http://localhost:9090/homepage/search', this.inputs,{
                    withCredentials: true,
                }).then(res => {
                    this.loading = false;
                    this.postData = res.data;
                    console.log(res.data);
                }).catch(err => {
                    console.log(err);
                });
                // Implement your search logic here
            },
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
            }
        },
        mounted() {
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
        }
    };
</script>


<style>
    .input-with-select{
        background-color: #fff;
        flex-grow: 1;
    }
    .container {
        width: 100%;
        display: flex;
        flex-direction: column;
        height: 100vh;
    }

    .main-content {
        overflow-y: auto;
        height: 90%;
        border: 10px solid #ccc;
        height: 100vh;
    }
</style>