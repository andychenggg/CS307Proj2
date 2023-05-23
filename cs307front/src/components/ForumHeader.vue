<template>
    <div class="header">
        <div style="width:30%; display: flex;justify-content: flex-start;">
            <img src="/Images/Logo.png" alt="The Forum Logo" style="width: 70px; height: 50px;
      margin: 10px;">
            <div class="logo">The Forum</div>
        </div >
        <div class="nav" >
            <el-input v-model="input" placeholder="请输入内容" style="width: 100%"></el-input>
            <el-button type="primary" icon="el-icon-search">搜索</el-button>

        <!--            add a div-->
            <div v-if="username" class="name">
                {{ username }}
            </div>
        </div>
    </div>
</template>

<script>
    import axios from "axios";

    export default {
        data() {
            return {
                input: '',
                username: null
            }
        },
        methods: {
            deleteCookie() {
                document.cookie = "loginId=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
                window.location.href = "http://localhost:8080/homepage";
            }
        },
        created() {
            axios.get('http://localhost:9090/current_user', {
                withCredentials: true
            })
                .then(response => {
                    // 处理响应
                    this.username = response.data;
                })
                .catch(error => {
                    // 处理错误
                    console.error(error);
                });
        }
    };
</script>

<style scoped>

    body{
        background-color: #ffffff;
        width: 100%;
        height: 10%;
    }
    .header {
        background-color: transparent;
        border: 1px #1c2125;
        width: 100%;
        display: flex;
        justify-content: space-between;
        height: 10%;
    }

    .logo {
        font-size: 20px;
        font-weight: bold;
        display: flex;
        margin-left: 10px;
        margin-right: 10px;
        justify-content: center;
        align-items: center;
    }

    .nav {
        display: flex;
        align-items: center;
        justify-content: flex-start;
        margin: 0;
    }

    .name {
        font-size: 20px;
        font-weight: bold;
        margin-left: 10px;
        margin-right: 10px;
    }

</style>
