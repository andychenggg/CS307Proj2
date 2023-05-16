<template>
    <div style="height: 100%">
        <el-container style="height: 100%; border: 1px solid #eee">
            <el-header style="width: 100%">
                <ForumHeader class="ForumHeader"></ForumHeader>
            </el-header>
            <el-container>
                <el-aside width="20%" style="background-color: rgb(238, 241, 246)">
                    <el-menu :default-openeds="['1', '3']">
                        <el-submenu index="1">
                            <template slot="title"><i class="el-icon-message"></i>homepage</template>
                            <el-menu-item index="1-1">
                                <el-button type="text">homepage</el-button>
                            </el-menu-item>
                        </el-submenu>
                        <el-submenu index="2">
                            <template slot="title"><i class="el-icon-menu"></i>Post</template>
                            <el-menu-item index="2-1">
                                <el-button type="text">post</el-button>
                            </el-menu-item>
                        </el-submenu>
                        <el-submenu index="3">
                            <template slot="title">
                                <i class="el-icon-document"></i>Record
                            </template>
                            <el-menu-item index="3-1">
                                <el-button type="text">yourLike</el-button>
                            </el-menu-item>
                            <el-menu-item index="3-2">
                                <el-button type="text">yourFavorite</el-button>
                            </el-menu-item>
                            <el-menu-item index="3-3">
                                <el-button type="text">yourPost</el-button>
                            </el-menu-item>
                            <el-menu-item index="3-4">
                                <el-button type="text">yourComment</el-button>
                            </el-menu-item>
                        </el-submenu>
                        <el-submenu index="4">
                            <template slot="title">
                                <i class="el-icon-user"></i>User
                            </template>
                            <el-menu-item index="4-1">
                                <div style="display: flex; justify-content: flex-end; align-items: center; margin-top: 5px">
                                    <el-button type="text">follow/unfollow</el-button>
                                </div>
                            </el-menu-item>
                            <el-menu-item index="4-2">
                                <div style="display: flex; justify-content: flex-end; align-items: center; margin-top: 5px">
                                    <el-button type="danger" plain size="small" @click="logout">Log out</el-button>
                                </div>
                            </el-menu-item>
                        </el-submenu>

                    </el-menu>
                </el-aside>
                <el-main style="height: 100%;">
                    <InfiniteStroll></InfiniteStroll>
                </el-main>
            </el-container>
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