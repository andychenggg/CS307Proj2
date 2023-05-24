<template>
    <div class="comment">
        <div class="comment-header">
            <!--            头像-->
            <!--            <el-tooltip class="item" effect="dark" content="点我更换头像" placement="top-start">-->
            <!--                <div @click="handleClick">-->
            <!--                    <input type="file" style="display: none" @change="dealWithdAvatar" ref="avatar" />-->
            <!--                    <el-avatar-->
            <!--                            :src="-->
            <!--              avatarUrl-->
            <!--                ? avatarUrl-->
            <!--                : 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'-->
            <!--            "-->
            <!--                            :size="40"-->
            <!--                    ></el-avatar>-->
            <!--                </div>-->
            <!--            </el-tooltip>-->
            <el-input
                    :placeholder="placeholderText"
                    v-model="content"
                    class="input"
                    type="textarea"
                    :autosize="{ minRows: 2, maxRows: 6 }"
                    size="mini"
            ></el-input>
            <el-button
                    type="primary"
                    style="height: 40px"
                    @click="addPostReply"
            >{{ "Send" }}
            </el-button>
        </div>
        <div class="comment-body" v-for="item in replies" :key="item.replyId">
            <!-- 一级评论 -->
            <div class="first-comment">
                <!--                <el-avatar :size="40" :src="item.avatarUrl"></el-avatar>-->
                <div class="content">

                    <div style="display: flex">
                        <el-tag type="success" style="margin-right: 10px">{{ item.authorName }}</el-tag>
                        <div style="min-width: 120px; height: 30px; justify-content: center; display: flex; font-weight: bold; align-items: center;"> Reply To: </div>
                        <el-tag type="success" style="margin-right: 10px" v-if="item.toUserName">{{ item.toUserName}}</el-tag>
                    </div>
                    <div style="white-space: pre-wrap; height: 40px; display: flex;">
                        {{item.content}}
                    </div>

                    <div class="comment-right">
                        <el-button type="warning" icon="el-icon-star-off" circle
                                   @click="giveAStar(item)"
                        >{{item.stars}}</el-button>
                        <el-button type="primary" icon="el-icon-edit" circle
                                   @click="isShowSecReply(item.replyId)"
                        ></el-button>
<!--                        <el-button type="danger" icon="el-icon-delete" circle-->
<!--                                   @click="deleteComment(item._id, undefined)"-->
<!--                                   v-if="userId === item.userId"-->
<!--                        ></el-button>-->
                    </div>
                    <!-- 回复一级评论 -->
                    <div class="reply-comment" v-show="isShowSec === item.replyId">
                        <el-input
                                :placeholder="placeholderText"
                                class="input"
                                v-model="replyContext"
                        ></el-input>
                        <el-button
                                type="primary"
                                size="mini"
                                class="reply-button"
                                @click="addComment(item)"
                        >send
                        </el-button>
                    </div>
                </div>
            </div>
        </div>
        <!-- 暂无评论的空状态 -->
        <el-empty :description="emptyText" v-show="replies.length === 0"></el-empty>
    </div>
</template>

<script>
    import axios from "axios";
    export default {
        props: {
            currentPostId: {
                type: Number
            },
            replies: {
                type: Array,
                require: true,
                default: () => []
            }
        },
        data() {
            return {
                placeholderText: "Comment now!",
                content: "",
                emptyText: "No replies yet.",
                isShowSec: '',
                isClickId: '',
                replyContext: ''
            }
        },
        methods: {
            addPostReply() {
                const url = 'http://localhost:9090/user/homepage/replies';
                const data = {
                    toReplyId: 0,
                    toPostId: this.currentPostId,
                    content: this.content,
                    isAnonymous: false
                };
                axios.post(url, data, {
                    withCredentials: true
                })
                    .then(response => {
                        // 请求成功后的处理
                        console.log(response.data);
                    })
                    .catch(error => {
                        // 请求失败后的处理
                        console.error(error);
                    });
                this.content = '';
            },
            giveAStar(item){
                axios.post('http://localhost:9090/user/homepage/replies/star',
                    {

                    }, {
                    params: {
                        replyId: item.replyId
                    },
                    withCredentials: true
                })
                    .then(response => {
                        // 请求成功后的处理
                        item.stars++;
                        this.showMess("Give a star successfully!")
                    })
                    .catch(error => {
                        // 请求失败后的处理
                        console.error(error);
                    });
            },
            isShowSecReply(id) {
                if (id) {
                    this.isShowSec = id;
                    if (this.isClickId === this.isShowSec) {
                        this.isShowSec = "";
                    } else {
                        this.isShowSec = id;
                    }
                    this.isClickId = this.isShowSec;
                } else {
                    this.isShowSec = this.isClickId = "";
                }
            },
            addComment(item){
                const url = 'http://localhost:9090/user/homepage/replies';
                const data = {
                    toReplyId: item.replyId,
                    toPostId: this.currentPostId,
                    content: this.replyContext,
                    isAnonymous: false
                };
                axios.post(url, data, {
                    withCredentials: true
                })
                    .then(response => {
                        // 请求成功后的处理
                        console.log(item.replyId);
                        console.log(response.data);
                    })
                    .catch(error => {
                        // 请求失败后的处理
                        console.error(error);
                    });
                this.replyContext = '';
            },
            showMess(message){
                this.$notify({
                    title: '提示',
                    message: message,
                    type: 'success',
                    duration: 2000, // 设置持续时间为3秒
                });
            }
        }
    }
</script>

<style>
    .comment {
        width: 90%;
        min-height: 26vh;
        border-radius: 5px;
        margin-top: 2px;



        .comment-header {
            position: relative;
            min-height: 30px;
            padding: 10px 5px;
            display: flex;
            align-items: center;

            .input {
                margin-left: 10px;
                margin-right: 20px;
                flex: 1;
                width: 80%;

                /deep/ .el-input__inner:focus {
                    border-color: #dcdfe6;
                }
            }
        }

        .comment-body {
            margin-left: 10px;
            min-height: 70px;
            width: 100%;
            padding: 10px 20px;
            font-size: 14px;
            border: 1px;
            border-radius: 4px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, .12);
            display: flex;
            justify-content: center;

            .first-comment {
                display: flex;



                .content {
                    display: flex;
                    flex-direction: column;
                    margin: 10px;
                    position: relative;
                    flex: 1;



                    .comment-right {
                        display: flex;
                        justify-content: flex-end;
                        margin-bottom: 10px;
                    }

                    .reply-comment {
                        margin-left: 30px;
                        height: 60px;
                        display: flex;
                        justify-content: center;
                        width: 80%;

                        //align-items: center;

                        .reply-button {
                            margin-left: 20px;
                            height: 35px;
                        }
                    }

                }
            }
        }
    }
</style>
