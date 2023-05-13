<template>
    <div style="height: 100%">
        <el-container style="height: 100%; border: 1px solid #eee">
            <el-header style="width: 100%">
                <ForumHeader class="ForumHeader" ></ForumHeader>
            </el-header>
            <el-container >
                <el-aside width="20%" style="background-color: rgb(238, 241, 246)">
                    <el-menu :default-openeds="['1', '3']">
                        <el-submenu index="1">
                            <template slot="title"><i class="el-icon-message"></i>导航一</template>
                            <el-menu-item-group>
                                <template slot="title">分组一</template>
                                <el-menu-item index="1-1">选项1</el-menu-item>
                                <el-menu-item index="1-2">选项2</el-menu-item>
                            </el-menu-item-group>
                            <el-menu-item-group title="分组2">
                                <el-menu-item index="1-3">选项3</el-menu-item>
                            </el-menu-item-group>
                            <el-submenu index="1-4">
                                <template slot="title">选项4</template>
                                <el-menu-item index="1-4-1">选项4-1</el-menu-item>
                            </el-submenu>
                        </el-submenu>
                        <el-submenu index="2">
                            <template slot="title"><i class="el-icon-menu"></i>导航二</template>
                            <el-menu-item-group>
                                <template slot="title">分组一</template>
                                <el-menu-item index="2-1">选项1</el-menu-item>
                                <el-menu-item index="2-2">选项2</el-menu-item>
                            </el-menu-item-group>
                            <el-menu-item-group title="分组2">
                                <el-menu-item index="2-3">选项3</el-menu-item>
                            </el-menu-item-group>
                            <el-submenu index="2-4">
                                <template slot="title">选项4</template>
                                <el-menu-item index="2-4-1">选项4-1</el-menu-item>
                            </el-submenu>
                        </el-submenu>
                        <el-submenu index="3">
                            <template slot="title">
                                <i class="el-icon-setting"></i>Setting
                            </template>
                            <el-menu-item index="3-1">
                                <div style="display: flex; justify-content: flex-end; align-items: center; margin-top: 5px">
                                    <el-button type="danger" plain @click="logout">Log out</el-button>
                                </div>
                            </el-menu-item>
                            <el-menu-item index="3-2">选项2</el-menu-item>

                            <el-menu-item-group title="分组2">
                                <el-menu-item index="3-3">选项3</el-menu-item>
                            </el-menu-item-group>
                            <el-submenu index="3-4">
                            </el-submenu>
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
                axios.delete('http://localhost:9090/logout')
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