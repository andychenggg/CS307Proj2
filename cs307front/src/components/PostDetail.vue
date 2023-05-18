<template>
    <div class="container">
        <div class="top">
            <div class="label-button-container">
                <label>{{ label }}</label>
                <button @click="toggleButtonContent">{{ buttonText }}</button>
            </div>
            <div v-if="showButtonContent">
            </div>
            <div v-else>
            </div>
        </div>
        <div class="middle">
            <!-- 可填充的内容 -->
            <slot></slot>
        </div>
        <div class="bottom">
            <div class="button-container">
                <button @click="handleAction('share')">share</button>
                <button @click="handleAction('like')">like</button>
                <button @click="handleAction('favorite')">favorite</button>
            </div>
        </div>
        <div class="comment">
            <!-- 评论内容 -->
            <!-- ... -->
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            showButtonContent: false,
            buttonText: '+follow',
        };
    },
    methods: {
        toggleButtonContent() {
            this.showButtonContent = !this.showButtonContent;
            this.buttonText = this.showButtonContent ? '-unfollow' : '+follow';
        },
        handleAction(action) {
            // 执行按钮操作
            console.log('执行操作:', action);
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

.label-button-container {
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.middle {
    margin-bottom: 10px;
}

.button-container {
    display: flex;
    justify-content: center;
    margin-bottom: 10px;
}

.comment {
    margin-top: 10px;
}
</style>
