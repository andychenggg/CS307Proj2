<template>
    <div>
        <el-container>
            <el-aside style="background-color: rgb(238, 241, 246)">
                <el-menu :default-openeds="['1', '3']">
                    <el-submenu index="1">
                        <template slot="title"><i class="el-icon-message"></i>homepage</template>
                        <el-menu-item index="1-1">
                            <el-button type="primary" plain class="same_length_button">homepage</el-button>
                        </el-menu-item>
                    </el-submenu>
                    <el-submenu index="2">
                        <template slot="title"><i class="el-icon-menu"></i>Post</template>
                        <el-menu-item index="2-1">
                            <el-button type="primary" plain class="same_length_button">post</el-button>
                        </el-menu-item>
                    </el-submenu>
                    <el-submenu index="3">
                        <template slot="title">
                            <i class="el-icon-document"></i>Record
                        </template>
                        <el-menu-item index="3-1">
                            <el-button type="primary" plain class="same_length_button">yourLike</el-button>
                        </el-menu-item>
                        <el-menu-item index="3-2">
                            <el-button type="primary" plain class="same_length_button">yourFavorite</el-button>
                        </el-menu-item>
                        <el-menu-item index="3-3">
                            <el-button type="primary" plain class="same_length_button">yourPost</el-button>
                        </el-menu-item>
                        <el-menu-item index="3-4">
                            <el-button type="primary" plain class="same_length_button">yourComment</el-button>
                        </el-menu-item>
                    </el-submenu>
                    <el-submenu index="4">
                        <template slot="title">
                            <i class="el-icon-user"></i>User
                        </template>
                        <el-menu-item index="4-1">
                            <el-button type="primary" plain class="same_length_button">follow/unfollow</el-button>
                        </el-menu-item>
                        <el-menu-item index="4-2" >
                            <el-button type="danger" plain @click="logout" class="same_length_button">Log out</el-button>
                        </el-menu-item>
                    </el-submenu>
                </el-menu>
            </el-aside>
        </el-container>
    </div>
</template>

<script>
    import InfiniteStroll from "@/components/InfiniteStroll.vue";
    import axios from "axios";

    export default {
        components: {InfiniteStroll},
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
    }
</script>

<style scoped>
    .sidebar {
        width: 100%;
        background-color: #f0f0f0;
    }

    .same_length_button{
        width: 140px;
    }

</style>
