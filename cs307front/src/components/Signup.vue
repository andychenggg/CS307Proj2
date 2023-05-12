<template>
    <div class="wrapper">
        <div class="login-wrapper">
            <h1 class="logo">
                <img src="/Images/Logo.png" alt="TheForum" style="width: 120px; height: auto;">
            </h1>
            <form @submit.prevent="submitForm">
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" v-model="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" v-model="password" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" v-model="phone" required>
                </div>
                <div class="form-group">
                    <button type="submit" class="submit-button">Sign up</button>
                </div>
                <div class="form-group">
                    <p class="text-small">Already have an account? <a href="/login">Log in</a></p>
                </div>
            </form>
        </div>
    </div>

</template>

<script>
    export default {
        data() {
            return {
                username: '',
                password: ''
            };
        },
        methods: {
            submitForm() {
                // 这里可以添加提交表单的逻辑
            }
        }
    }
</script>

<style scoped>



    body, html {
        height: 100%;
        width: 100%;
    }
    .wrapper{
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100%;
        width: 100%;
    }

    .login-wrapper {
        width: 400px;
        padding: 20px;
        background-color: #fff;
        border-radius: 4px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        text-align: center;
    }

    .logo {
        display: flex;
        justify-content: center;
        margin-bottom: 20px;
    }

    .form-group {
        margin-bottom: 20px;
        display: flex;
        flex-direction: column;
    }

    .form-group label {
        display: block;
        font-weight: bold;
        margin-bottom: 5px;
    }

    .form-group input {
        width: 90%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        outline: none;
        text-align: center;
    }

    .form-group input[type="text"],
    .form-group input[type="password"] {
        width: 90%;
        margin: 0 auto; /* 水平居中 */
    }

    .form-group input[type="submit"] {
        background-color: #24292e;
        color: #fff;
        cursor: pointer;
        text-align: center;
        margin: 0 auto; /* 水平居中 */
    }

    .form-group input[type="submit"]:hover {
        background-color: #1c2125;
    }

    .form-group .text-small {
        font-size: 12px;
        color: #586069;
    }

    .form-group button.submit-button {
        width: 90%;
        padding: 10px;
        background-color: #000;  /* change color to black */
        color: #fff;
        cursor: pointer;
        border: none;  /* remove default button border */
        border-radius: 4px;
        outline: none;
        font-size: 16px;  /* increase font size */
        height: 50px;  /* increase button height */
        margin: 0 auto;  /* center button horizontally */
    }

    .form-group button.submit-button:hover {
        background-color: #1c2125;
    }
</style>
