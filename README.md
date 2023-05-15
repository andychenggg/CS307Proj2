# CS307Proj2

现存问题和没做的事：

Question:
在我转发post的时候，需要我在posts这张表里新增一行，这一行其他部分和被转发的post一样，然后senderid 变成转发者的id么？这样好像不符合posts里的postid地主键约束。
已解决

Question:
![image](https://github.com/andychenggg/CS307Proj2/assets/119112896/bc912ae9-2603-4832-a520-01f
ERROR in ./src/components/Signup.vue?vue&type=script&lang=js& (./node_modules/babel-loader/lib/index.js??clonedRuleSet-40.use[0]!./node_modules/@vue/vue-loader-v15/lib/index.js??vue-loader-options!./src/components/Signup.vue?vue&type=script&lang=js&) 4:0-41
Module not found: Error: Package path ./src/core/config is not exported from package C:\Users\Wiki\IdeaProjects\CS307Proj2\cs307front\node_modules\vue (see exports field in C:\Users\Wiki\IdeaProjects\CS307Proj2\cs307front\node_modules\vue\package.json)        
 @ ./src/components/Signup.vue?vue&type=script&lang=js& 1:0-200 1:216-219 1:221-418 1:221-418
 @ ./src/components/Signup.vue 2:0-58 3:0-53 3:0-53 10:2-8
 @ ./src/router/index.js 5:0-45 25:15-21
 @ ./src/main.js 5:0-36 12:2-8
webpack compiled with 1 error
b1ba9205f)
问题好像是C:\Users\Wiki\IdeaProjects\CS307Proj2\cs307front\node_modules\vue\package.json 文件中的 exports 字段， ./src/core/config 不在导出的模块列表中。
导出模块为
"exports": {
    ".": {
      "import": {
        "node": "./dist/vue.runtime.mjs",
        "default": "./dist/vue.runtime.esm.js"
      },
      "require": "./dist/vue.runtime.common.js",
      "types": "./types/index.d.ts"
    },
    "./compiler-sfc": {
      "import": "./compiler-sfc/index.mjs",
      "require": "./compiler-sfc/index.js"
    },
    "./dist/*": "./dist/*",
    "./types/*": "./types/*",
    "./package.json": "./package.json"
  }


## todo: 
- 将postid，replyid设置为自增
- 删除posts中accessLevel
- 将插入replies数据的代码中的postid设置为对应的值，即使是reply-to-reply


## 更新习惯
每天开始做proj时从main分支pull下来

每天结束之后向自己分支push，同时合并到主分支

pull下来之后记得更改jdbc的ip，查看centos虚拟机的地址的命令是：
```
$ ip addr show
```
## 后端拦截器管理：
/    -->    /homepage

/homepage --> 检查cookie，如果已经登陆的要重定向/user/{userid}/homepage

/user/** --> Interceptor拦截未登录的

/user/{userid}/homepage --> 如果与cookie中的userid不一样返回404

/login -->
检查cookie，如果已经登陆的重定向到/user/{userid}/homepage
如果登录成功，更新cookie

/signup --> 检查cookie，如果已经登陆的重定向到/user/{userid}/homepage

cookie只需要一个: userid

CookieManager类有一切和cookie处理有关的静态方法

## 前端响应的注意事项：
- `axios` 发送请求是默认不会携带cookie的，前端向后端发送请求一定要指定： `withCredentials: true`, 比如说：
```javascript
axios.get('http://localhost:9090/login', {
    withCredentials: true
})
```
# 后端--数据库与前端--后端交互规范
## 后端向数据库的查询操作：
1. 注册账户：insert，所有列都要插入
2. 点赞，收藏：insert，所有列都要插入
3. 转发：insert，所有列都要插入
4. 查看点赞，收藏帖子列表：select，post的所有列都需要查询，还要根据authorid，senderid,查询authorname，sendername，是否anonymous由前端进行判断
5. 关注其他作者：insert，所有列都要插入
6. 取消关注： delete，删除某一行
7. 查看用户关注的列表：select，并根据id查找相应的username
8. 发布帖子，insert,所有列都要插入
9. 发布评论：insert，重点在于replyid与toreplyid
10. 查看自己发布的贴子: select, 根据时间倒序

### 注意事项
- 每次用户的成功操作都要更新cookie
- 

-------------------------------------------- bonus ---------------------------------------------------

## 前端向后端请求规范
