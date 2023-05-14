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
                <span style="color: red" v-text="wrongMessage"></span>
                <div class="form-group">
                    <button type="button" class="submit-button" @click="submitForm">Log in</button>
                </div>
                <div class="form-group">
                    <p class="text-small">Don't have an account? <router-link to="../signup">Sign up</router-link></p>
                </div>
<!--                <div class="form-group">-->
<!--                    <p class="text-small">Don't have an account? <a href="../signup">Sign up</a></p>-->
<!--                </div>-->
            </form>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    // import router from "../router/index.js";
    export default {
        data() {
            return {
                username: '',
                password: '',
                wrongMessage: ''
            };
        },
        methods: {
            submitForm() {
                // 发送 POST 请求
                axios.post('http://localhost:9090/login', {
                    username: this.username,
                    password: this.password
                }, {
                    withCredentials: true
                })
                    .then(response => {
                        // 处理响应
                        const head = response.headers;
                        if (head['request-login'] === 'pass') {
                            // 重定向到 /user/homepage
                            this.$router.push('../user/homepage');
                        } else {
                            // 处理其他情况，例如显示错误信息
                            this.wrongMessage = response.data
                        }
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
