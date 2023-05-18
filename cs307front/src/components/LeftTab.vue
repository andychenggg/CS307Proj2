<template>
    <div class ="wrapper">
        <el-container class="el-container-wrapper">
            <el-aside class="el-aside-wrapper" style="background-color: rgb(238, 241, 246)">
                <el-menu :default-openeds="['1', '4']">
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
        </el-container>
    </div>
</template>

<script>
    // import InfiniteStroll from "@/components/InfiniteStroll.vue";
    //
    // export default {
    //     components: {InfiniteStroll}
    // };
</script>

<style scoped>
    .wrapper{
      width: 100%;
    }

    .el-container-wrapper{
      width: inherit;
    }

    .el-aside-wrapper{
      width: inherit;
    }
</style>
