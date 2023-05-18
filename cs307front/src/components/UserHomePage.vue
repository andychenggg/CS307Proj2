<template>
  <div class="wrapper">
    <ForumHeader></ForumHeader>
    <div class="content-wrapper">
      <div class="leftTab-wrapper" style="width: 30%;">
        <LeftTab></LeftTab>
      </div>
      <div class="User-wrapper" style="width: 70%;">
        <User></User>
      </div>
    </div>
  </div>
</template>

<script>
import ForumHeader from './ForumHeader.vue';
import LeftTab from './LeftTab.vue';
import User from './User.vue';
import Post from './Post.vue';

export default {
  components: {
    ForumHeader,
    LeftTab,
    User,
    Post
  }

};
</script>

<style>
body {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
}

.wrapper {
  width: 100%;
  height: 100%;
}

.content-wrapper {
  display: flex;
  flex-direction: row;
  height: 100vh;
  width: 100%;
}

.leftTab-wrapper {
  height: 100%;
}

.postContainer-wrapper {
  height: 100%;
}

.postPost-wrapper {
  height: 100%;
}

.User-wrapper {
  height: 100%;
}

</style>
