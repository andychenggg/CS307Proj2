<template>
  <el-container v-loading="loading" class="post-article">
    <el-header class="header">
      <el-input v-model="article.title" placeholder="请输入标题..." style="width: 400px;margin-left: 10px"></el-input>
      <el-input
          class="input-new-tag"
          v-if="tagInputVisible"
          v-model="tagValue"
          ref="saveTagInput"
          size="small"
          @keyup.enter.native="handleInputConfirm"
          @blur="handleInputConfirm">
      </el-input>
      <el-button v-else class="button-new-tag" type="primary" size="small" @click="showInput">+Tag</el-button>
    </el-header>
    <el-main class="main">
      <div id="editor">
        <mavon-editor style="height: 100%;width: 100%;" ref=md @imgAdd="imgAdd"
                      @imgDel="imgDel" v-model="article.mdContent"></mavon-editor>
      </div>
      <div style="display: flex;align-items: center;margin-top: 15px;justify-content: flex-end">
        <el-button @click="cancelEdit" v-if="from!=undefined">放弃修改</el-button>
        <template v-if="from==undefined || from=='draft'">
          <el-button @click="saveBlog(0)">保存到草稿箱</el-button>
          <el-button type="primary" @click="saveBlog(1)">发表文章</el-button>
        </template>
        <template v-else="from==post">
          <el-button type="primary" @click="saveBlog(1)">保存修改</el-button>
        </template>
      </div>
    </el-main>
  </el-container>
</template>

<style scoped>
.post-article > .main > #editor {
  width: 100%;
  height: 450px;
  text-align: left;
}

.post-article > .header {
  background-color: #ececec;
  margin-top: 10px;
  padding-left: 5px;
  display: flex;
  justify-content: flex-start;
}

.post-article > .main {
  /*justify-content: flex-start;*/
  display: flex;
  flex-direction: column;
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

.post-article {
}
</style>