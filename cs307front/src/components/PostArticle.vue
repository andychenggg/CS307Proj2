<template>
    <div>
        <el-container v-loading="loading" class="post-article">
            <el-header class="header">
                <el-select v-model="anonymous_value" placeholder="Anonymous or not?">
                    <el-option
                            v-for="item in options"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value">
                    </el-option>
                </el-select>
                <el-input v-model="article.title" placeholder="input title..." style="width: 400px;margin-left: 10px"></el-input>
                <el-tag
                        :key="tag"
                        v-for="tag in article.dynamicTags"
                        closable
                        :disable-transitions="false"
                        @close="handleClose(tag)" style="margin-left: 10px">
                    {{tag}}
                </el-tag>
                <el-input
                        class="input-new-tag"
                        v-if="tagInputVisible"
                        v-model="tagValue"
                        ref="saveTagInput"
                        size="small"
                        @keyup.enter.native="handleInputConfirm"
                        @blur="handleInputConfirm">
                </el-input>
                <el-button v-else class="button-new-tag" type="primary"  @click="showInput">+Category</el-button>
            </el-header>
            <el-main class="main">
                <div id="editor">
                    <el-input
                            type="textarea"
                            :autosize="{ minRows: 23, maxRows: 23}"
                            placeholder="main content"
                            v-model="article.mdContent"
                    >
                    </el-input>
                </div>
                <div style="display: flex; flex-direction: column; width: 40%; height: 100%">
                    <el-upload
                            style="height: 40%"
                            class="upload-demo"
                            drag
                            action="https://jsonplaceholder.typicode.com/posts/"
                            multiple>
                        <i class="el-icon-upload"></i>
                        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                        <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
                    </el-upload>
                    <div style="height: 250px">

                    </div>
                    <div style="height: 10%;">
                        <el-button type="success" plain @click="submitPost" style="height: 40px; width: 100px">Post</el-button>
                    </div>
                </div>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    import 'mavon-editor/dist/css/index.css'
    import axios from "axios";

    export default {
        components: {},
        data(){
            return {
                options: [{
                    value: 'Public',
                    label: 'Public'
                }, {
                    value: 'Anonymous',
                    label: 'Anonymous'
                }],
                anonymous_value: 'Public',
                article: {
                    id: '-1',
                    dynamicTags: [],
                    title: '',
                    mdContent: '',
                    cid: ''
                },
                tagInputVisible: false,
                tagValue: '',
                loading: false,
                from: '',
                value1: true
            }
        },
        methods: {
            handleClose(tag) {
                this.article.dynamicTags.splice(this.article.dynamicTags.indexOf(tag), 1);
            },
            showInput() {
                this.tagInputVisible = true;
                this.$nextTick(_ => {
                    this.$refs.saveTagInput.$refs.input.focus();
                });
            },
            handleInputConfirm() {
                let tagValue = this.tagValue;
                if (tagValue) {
                    this.article.dynamicTags.push(tagValue);
                }
                this.tagInputVisible = false;
                this.tagValue = '';
            },
            submitPost(){
                let ip;
                if(this.article.mdContent !== '' && this.article.title !== ''){
                    axios.get('https://api.ipify.org?format=json', {withCredentials: false})
                        .then(response => {
                            ip = response.data.ip;
                            axios.post("http://localhost:9090/user/homepage/post", {
                                title: this.article.title,
                                anonymous: this.anonymous_value === 'Anonymous',
                                content: this.article.mdContent,
                                ip: ip,
                                postCategories: this.article.dynamicTags

                            }, {
                                withCredentials: true
                            })
                                .then(response => {
                                    // 处理响应
                                    console.log(ip)
                                    this.article.dynamicTags = [];
                                    this.article.title = '';
                                    this.article.mdContent = '';
                                })
                                .catch(error => {
                                    // 处理错误
                                    console.error(error);
                                });
                        });
                }
            }
        }
    }
</script>

<style >
    .post-article > .main > #editor {
        width: 60%;
        height: 650px;
        text-align: left;
    }

    .post-article > .header {
        background-color: #ececec;
        margin-top: 10px;
        padding-left: 5px;
        display: flex;
        justify-content: flex-start;
        align-items: center;
    }

    .post-article > .main {
        /*justify-content: flex-start;*/
        display: flex;
        padding-left: 5px;
        background-color: #ececec;
        padding-top: 0px;
    }

    .post-article > .header > .el-tag + .el-tag {
        margin-left: 10px;
    }

    .post-article > .header > .button-new-tag {
        margin-left: 10px;
        height: 32px;
        line-height: 30px;
        padding-top: 0;
        padding-bottom: 0;
    }

    .post-article > .header > .input-new-tag {
        width: 90px;
        margin-left: 10px;
        vertical-align: bottom;
    }

</style>


