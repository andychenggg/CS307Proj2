<template>
    <div class="header">
        <div style="display: flex;justify-content: flex-start;">
            <img src="/Images/Logo.png" alt="The Forum Logo" style="width: 70px; height: 50px;
      margin: 10px;">
            <div class="logo">The Forum</div>
        </div>
        <div class="nav" style=" justify-content: flex-end; align-items: center; ">
            <el-button type="primary">Setting</el-button>
            <el-button type="primary">Log out</el-button>
        </div>
    </div>
</template>

<script>
    export default {
        methods: {
            deleteCookie() {
                document.cookie = "loginId=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
                window.location.href = "http://localhost:8080/homepage";
            }
        }
    };
</script>

<style scoped>
    .header {
        background-color: #ffffff;
        display: flex;
        justify-content: space-between;
    }

    .logo {
        font-size: 20px;
        font-weight: bold;
        display: flex;
        margin-left: 10px;
        justify-content: center;
        align-items: center;
    }

    .nav {
        display: flex;
        justify-content: flex-end;
    }

    .button {
        font-size: 15px;
        font-weight: bold;
        background-color: transparent;
        width: 80px;
        height: 40px;
        display: flex;
        justify-content: center;
        align-items: center;
        color: #206E1D;
        border: 2px solid #206E1D;
        border-radius: 2px;
        margin: 10px;
    }
</style>
