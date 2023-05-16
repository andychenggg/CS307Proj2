<template>
    <div style="height: 100%">
        <el-container style="height: 100%; border: 1px solid #eee">
            <el-header style="width: 100%">
                <ForumHeader class="ForumHeader"></ForumHeader>
            </el-header>
        </el-container>
    </div>
</template>


<style>
.el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
    display: flex;
}

.el-aside {
    color: #333;
}

.ForumHeader {
    width: 100%;
}

.custom-button {
    color: black !important;
}

</style>

<script>
import InfiniteStroll from "@/components/InfiniteStroll.vue";
import ForumHeader from "@/components/ForumHeader.vue";
import axios from "axios";

export default {
    data() {
        const item = {
            date: '2016-05-02',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
        };
        return {
            tableData: Array(20).fill(item)
        }
    },
    components: {
        ForumHeader,
        InfiniteStroll
    },
    methods: {
        logout() {
            axios.delete('http://localhost:9090/logout', {
                withCredentials: true
            })
                .then(response => {
                    // 处理响应
                    console.log(response.data)
                    this.$router.push('../homepage');
                })
                .catch(error => {
                    // 处理错误
                    console.error(error);
                });
        }
    }
};
</script>