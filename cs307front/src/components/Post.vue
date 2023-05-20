<template>
  <div class="container">
    <div class="username">
      <label>{{ "helloWorld" }}</label>
    </div>
    <div class="content">
      <textarea class="content-textarea" placeholder="content"></textarea>
    </div>
    <div class="details">
      <button @click="toggleContent">{{ buttonText }}</button>
      <div v-show="showContent">
        <PostDetail label="postDetail"></PostDetail>
      </div>
    </div>
  </div>
</template>

<script>
import PostDetail from "@/components/PostDetail.vue";

export default {
  components: {PostDetail},
  data() {
    return {
      showContent: false,
      buttonText: 'details',
    };
  },
  methods: {
    toggleContent() {
      this.showContent = !this.showContent;
      this.buttonText = this.showContent ? 'contract' : 'details';
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
}


.content {
  margin-bottom: 10px;
}

.details {
  text-align: center;
}
.container {
  border: 1px solid #ccc;
  padding: 10px;
}

.content-textarea {
  width: 100%;
  height: 150px;
  padding: 5px;
  border: 1px solid #ccc;
  resize: vertical; /* 可垂直调整大小 */
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