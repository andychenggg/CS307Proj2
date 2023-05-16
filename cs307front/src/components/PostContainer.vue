<template>
    <div class="container">
        <div class="main-content" id="content">
            <Post label="post"></Post>
            <!-- 这里是可滚动的内容 -->
        </div>
    </div>
</template>

<script>
    import Post from "@/components/Post.vue";

    export default {
        components: {Post},
        mounted() {
            const contentDiv = document.getElementById('content');
            const totalPages = 5; // 总页数
            let currentPage = 1; // 当前页数
            const itemsPerPage = 50; // 每页显示的条目数
            let loadedItems = 0; // 已加载的条目数

            // 加载指定页数的内容
            function loadContent(page) {
                for (let i = 1; i <= itemsPerPage; i++) {
                    const div = document.createElement('div');
                    div.className = 'box';
                    div.innerText = 'Content ' + (loadedItems + i);
                    contentDiv.appendChild(div);
                }
                loadedItems += itemsPerPage;
            }

            // 添加初始内容
            loadContent(currentPage);

            // 监听滚动事件
            window.addEventListener('scroll', function () {
                // 判断滚动到底部
                if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
                    // 加载下一页内容
                    currentPage++;
                    if (currentPage <= totalPages) {
                        loadContent(currentPage);
                    }
                }
            });
        }
    };
</script>


<style scoped>
    .container {
        width: 80%;
        display: flex;
        flex-direction: column;
    }

    .main-content {
        overflow-y: auto;
        border: 10px solid #ccc;
    }

    .box {
        padding: 20px;
        margin-bottom: 10px;
        background-color: #f0f0f0;
    }
</style>
