<template>
  <div>
    <div style="margin-top: 10px; display: flex; justify-content: center">
      <select v-model="searchMode" style="width: 200px; margin-right: 5px;">
        <option value="all">allUsers</option>
        <option value="following">followedUsers</option>
        <option value="shield">shieldUsers</option>
      </select>
      <input type="text" placeholder="Search users by username" style="width: 400px" v-model="searchText"/>
      <button type="button" style="margin-left: 3px" @click="search">搜索</button>
    </div>
    <div style="display: flex; justify-content: space-around; flex-wrap: wrap">
      <div style="width: 330px; margin-top: 10px; border: 1px solid #ccc; padding: 10px; border-radius: 5px;"
           v-for="user in filteredUsers" :key="user.id">
        <div style="display: flex; text-align: left; margin-bottom: 10px">
          <div style="font-weight: bold; font-size: 16px">{{ user.username }}</div>
          <div style="width: 10px"></div>
          <el-switch v-model="user.isFollowed" active-color="#13ce66" inactive-color="#ff4949" active-text="follow"
                     inactive-text="unfollow"
                     @change="toggleFollow(user)"></el-switch>
        </div>
        <div style="display: flex; align-items: flex-start">
          <el-switch v-model="user.isShield" active-color="#13ce66" inactive-color="#ff4949" active-text="shield"
                     inactive-text="unshield"
                     @change="toggleShield(user)"></el-switch>
        </div>
        <div>
          <div style="display: flex; align-items: center; margin-bottom: 10px">
            <!--            <img :src="user.userface" :alt="user.username" style="width: 70px; height: 70px; border-radius: 50%">-->
            <div style="margin-left: 10px">
              <div style="text-align: left; color: #20a0ff; font-size: 14px; margin-bottom: 5px">
                <span style="font-weight: bold">Name:</span><span>{{ user.username }}</span>
              </div>
              <div style="text-align: left; color: #20a0ff; font-size: 14px; margin-bottom: 5px">
                <span style="font-weight: bold">Phone:</span><span>{{ user.phone }}</span>
              </div>
              <div style="text-align: left; color: #20a0ff; font-size: 14px; margin-bottom: 5px">
                <span style="font-weight: bold">Registration Time:</span><span>{{ user.registrationtime }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import axios from "axios";

import axios from "axios";

export default {
  props: {
    users: {
      type: Array,
      required: true,
      default: () => ({
        userid: 0,
        username: '',
        registrationtime: '',
        phone: '',
        isFollowed: false,
        isShield: false,
      })
    }
  },
  data() {
    return {
      searchMode: 'all',
      searchText: '',
    };
  },
  mounted() {
    console.log("fetchUserData");
    axios.get("http://localhost:9090/user/follow", {
      params: {
        offset: 0,
        limit: 100
      },
      withCredentials: true
    })
        .then(response => {
          this.users = response.data;
          console.log(this.users.at(0));
        })
        .catch(error => {
          // 处理请求失败的错误
          console.error(error);
        });

  },
  computed: {
    filteredUsers() {
      if (this.searchMode === 'all') {
        return this.users.filter(user => user.username.toLowerCase().includes(this.searchText.toLowerCase()));
      } else if (this.searchMode === 'following') {
        return this.users.filter(user => user.isFollowed && user.username.toLowerCase().includes(this.searchText.toLowerCase()));
      } else if (this.searchMode === 'shield') {
        return this.users.filter(user => user.isShield && user.username.toLowerCase().includes(this.searchText.toLowerCase()));
      }
    },
  },
  methods: {
    deleteFollow(userid) {
      axios.delete("http://localhost:9090/user/follow", {
        params: {
          followigid: userid
        }
      })
          .then(response => {
            // 处理响应
          })
          .catch(error => {
            // 处理错误
            console.error(error);
          });
    },
    addFollow(userid) {
      axios.post("http://localhost:9090/user/follow", {
        userId: 0,
        followigId: userid
      }, {
        withCredentials: true
      })
          .then(response => {
            // 处理响应
          })
          .catch(error => {
            // 处理错误
            console.error(error);
          });
    },
    toggleFollow(user) {
      if (!user.isFollowed) {
        this.deleteFollow(user.userid);
      } else {
        this.addFollow(user.userid);
      }
      return !user.isFollowed;
    },
    search() {
      // Perform additional search logic if needed
    },
    toggleShield(user) {
      if (!user.isShield) {
        console.log(user.isShield);
        axios.delete("http://localhost:9090/user/shield", {
          params: {
            shieldid: user.userid
          }
        })
            .then(response => {
              // 处理响应
            })
            .catch(error => {
              // 处理错误
              console.error(error);
            });
      } else {
        axios.post("http://localhost:9090/user/shield", {
          userId: 0,
          shieldId: user.userid
        }, {
          withCredentials: true
        })
            .then(response => {
              // 处理响应
            })
            .catch(error => {
              // 处理错误
              console.error(error);
            });
      }
      return !user.isShield;

    },
  },
};
</script>