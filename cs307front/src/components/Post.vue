<template>
    <div class="post">
        <div class="username">
            <label>{{ label }}</label>
        </div>
        <div class="content">
            <!-- 可填充的内容 -->
            <slot></slot>
        </div>
        <div class="details">
            <button @click="toggleContent">{{ buttonText }}</button>
            <div v-show="showContent">
                <PostDetail></PostDetail>
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
            this.buttonText = this.showContent ? '收起' : '展开';
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

.top {
    text-align: center;
    margin-bottom: 10px;
}

.bottom {
    text-align: center;
    margin-top: 10px;
}
</style>
