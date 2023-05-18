<template>
  <div>
    <div style="margin-top: 10px; display: flex; justify-content: center">
      <select v-model="searchMode" style="width: 200px; margin-right: 5px;">
        <option value="all">allUsers</option>
        <option value="following">followedUsers</option>
      </select>
      <input type="text" placeholder="Search users by username" style="width: 400px" v-model="searchText" />
      <button type="button" style="margin-left: 3px" @click="search">搜索</button>
    </div>
    <div style="display: flex; justify-content: space-around; flex-wrap: wrap">
      <div style="width: 330px; margin-top: 10px; border: 1px solid #ccc; padding: 10px; border-radius: 5px;" v-for="user in filteredUsers" :key="user.id">
        <div style="text-align: left; margin-bottom: 10px">
          <span style="font-weight: bold; font-size: 16px">{{ user.nickname }}</span>
          <button style="float: right; padding: 3px 8px; color: #ff0509; background-color: #fff; border: 1px solid #ff0509; border-radius: 3px; cursor: pointer" @click="toggleFollow(user)">{{ user.following ? 'Unfollow' : 'Follow' }}</button>
        </div>
        <div>
          <div style="display: flex; align-items: center; margin-bottom: 10px">
            <img :src="user.userface" :alt="user.nickname" style="width: 70px; height: 70px; border-radius: 50%">
            <div style="margin-left: 10px">
              <div style="text-align: left; color: #20a0ff; font-size: 14px; margin-bottom: 5px">
                <span style="font-weight: bold">Name:</span><span>{{ user.username }}</span>
              </div>
              <div style="text-align: left; color: #20a0ff; font-size: 14px; margin-bottom: 5px">
                <span style="font-weight: bold">Phone:</span><span>{{ user.phone }}</span>
              </div>
              <div style="text-align: left; color: #20a0ff; font-size: 14px; margin-bottom: 5px">
                <span style="font-weight: bold">Registration Time:</span><span>{{ user.regTime }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      users: [
        {
          id: 1,
          nickname: 'Userid 1',
          userface: 'i/man1.jpg',
          username: 'user1',
          phone: 'user1@example.com',
          regTime: '2023-01-01',
          following: false,
        },
        {
          id: 2,
          nickname: 'Userid 2',
          userface: '/Images/accountPicture/man1.jpg',
          username: 'user2',
          phone: 'user2@example.com',
          regTime: '2023-02-01',
          following: false,
        },
        // Add more user objects here as needed
      ],
      searchMode: 'all',
      searchText: '',
    };
  },
  computed: {
    filteredUsers() {
      if (this.searchMode === 'all') {
        return this.users.filter(user => user.username.toLowerCase().includes(this.searchText.toLowerCase()));
      } else if (this.searchMode === 'following') {
        return this.users.filter(user => user.following && user.username.toLowerCase().includes(this.searchText.toLowerCase()));
      }
    },
  },
  methods: {
    toggleFollow(user) {
      user.following = !user.following;
    },
    search() {
      // Perform additional search logic if needed
    },
  },
};
</script>
