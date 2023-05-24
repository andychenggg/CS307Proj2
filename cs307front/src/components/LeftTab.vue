<template>
  <div style="z-index: 0;">
    <el-container style="display: flex; flex-direction: column;">
      <el-aside style=" background-color: transparent">
        <el-menu  background-color="transparent">
          <el-submenu index="1" >
            <template slot="title"><i class="el-icon-message"></i>homepage</template>
            <el-menu-item index="1-1" >
              <el-button type="primary" plain class="same_length_button" @click="changeToHomepage">homepage</el-button>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="2" style="background-color: transparent">
            <template slot="title"><i class="el-icon-menu"></i>Post</template>
            <el-menu-item index="2-1">
              <el-button type="primary" plain class="same_length_button" @click="changeToPost">post</el-button>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="3" style="background-color: transparent">
            <template slot="title">
              <i class="el-icon-document"></i>Record
            </template>
            <el-menu-item index="3-1">
              <el-button type="primary" plain class="same_length_button" @click="changeToYourLike">yourLike</el-button>
            </el-menu-item>
            <el-menu-item index="3-2">
              <el-button type="primary" plain class="same_length_button" @click="changeToYourFavorite">yourFavorite
              </el-button>
            </el-menu-item>
            <el-menu-item index="3-3">
              <el-button type="primary" plain class="same_length_button" @click="changeToYourShare">yourShare
              </el-button>
            </el-menu-item>
            <el-menu-item index="3-4">
              <el-button type="primary" plain class="same_length_button" @click="changeToYourPost">yourPost</el-button>
            </el-menu-item>
            <el-menu-item index="3-5">
              <el-button type="primary" plain class="same_length_button" @click="changeToYourReplyPost">yourReplyPost</el-button>
            </el-menu-item>
            <el-menu-item index="3-6">
              <el-button type="primary" plain class="same_length_button" @click="changeToYourComment">yourComment
              </el-button>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title">
              <i class="el-icon-user"></i>User
            </template>
            <el-menu-item index="4-1">
              <el-button type="primary" plain class="same_length_button" @click="changeToFollow">follow/unfollow
              </el-button>
            </el-menu-item>
            <el-menu-item index="4-2">
              <el-button type="danger" plain @click="logout" class="same_length_button">Log out</el-button>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>

    </el-container>
  </div>

</template>

<script>
import axios from "axios";

export default {

  data() {
    return {
      currentPage: 'homepage'
    };
  },
  components: {},
  methods: {
    logout() {
      axios.delete('http://localhost:9090/logout', {
        withCredentials: true
      })
          .then(response => {
            let cookies = document.cookie.split(";");

            for (let i = 0; i < cookies.length; i++) {
              let cookie = cookies[i];
              while (cookie.charAt(0) === " ") {
                cookie = cookie.substring(1);
              }
              if (cookie.indexOf("loginId" + "=") === 0) {
                let cookieValue = cookie.substring(("loginId" + "=").length);
                let domain = window.location.hostname;
                let path = "/";

                // 删除同名 cookie
                document.cookie = "loginId" + "=" + cookieValue + "; expires=Thu, 01 Jan 1970 00:00:00 UTC; domain=" + domain + "; path=" + path;
              }
            }
            // 处理响应
            console.log(response.data)
            this.$router.push('../homepage');
          })
          .catch(error => {
            // 处理错误
            console.error(error);
          });
    },
    changeToHomepage() {
      this.currentPage = 'homepage';
    },
    changeToPost() {
      this.currentPage = 'post';
    },
    changeToYourLike() {
      this.currentPage = 'yourLike';
    },
    changeToYourFavorite() {
      this.currentPage = 'yourFavorites';
    },
    changeToYourShare() {
      this.currentPage = 'yourShare'
    },
    changeToYourPost() {
      this.currentPage = 'yourPost';
    },
    changeToYourReplyPost() {
      this.currentPage = 'yourReplyPost';
    },
    changeToYourComment() {
      this.currentPage = 'yourComment';
    },
    changeToFollow() {
      this.currentPage = 'follow';
    }
  },
  watch: {
    currentPage(newPage, oldPage) {
      if (newPage !== oldPage) {
        this.$emit('page-change', newPage);
      }
    }
  }
}

</script>

<style scoped>
.wrapper {
  width: 100%;
}

.el-container-wrapper {
  width: inherit;
}

.el-aside-wrapper {
  width: inherit;
}
</style>
