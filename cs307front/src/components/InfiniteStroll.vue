<template>
    <div class="scroll-container" @scroll="handleScroll">
        <ul>
            <li v-for="item in items" :key="item">{{ item }}</li>
            <Post></Post>
        </ul>
    </div>
</template>

<script>
    import Post from '@/components/Post.vue';
    export default {
        data() {
            return {
                items: [],
                maxItems: 500,
                numLoaded: 0,
            }
        },
        mounted() {
            this.loadMore();
            window.addEventListener('scroll', this.handleScroll);
        },
        beforeDestroy() {
            window.removeEventListener('scroll', this.handleScroll);
        },
        methods: {
            loadMore() {
                let toLoad = Math.min(this.numLoaded + 50, this.maxItems);
                while (this.numLoaded < toLoad) {
                    this.items.push(this.numLoaded++);
                }
            },
            handleScroll() {
                let scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
                let windowHeight = document.documentElement.clientHeight || document.body.clientHeight;
                let scrollHeight = document.documentElement.scrollHeight || document.body.scrollHeight;
                let bottom = scrollHeight - scrollTop - windowHeight;
                if (bottom < scrollHeight * 0.02) {
                    this.loadMore();
                }
            }
        }
    }
</script>

<style scoped>
    .scroll-container {
        height: 100%;
        overflow-y: auto;
    }
</style>
