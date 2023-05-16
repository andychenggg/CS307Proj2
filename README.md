# CS307Proj2

现存问题和没做的事：

Question:
在我转发post的时候，需要我在posts这张表里新增一行，这一行其他部分和被转发的post一样，然后senderid 变成转发者的id么？这样好像不符合posts里的postid地主键约束。
已解决

Question: 
todo里面的内容需要我来完善么

-- 不需要，等我想清楚之后发新的导入文件给你

Question:
需不需要我将“后端向数据库的查询操作：”这一部分的内容补充
-- 不需要，我今天会尽量完成相应的规范
-- 同时，我尽量完成前端向后端发送请求数据的规范，今天之内尽量完成

Question:
post和postdetail的模子已经有了，但我现在没法儿进入网站，需要解决上面提到的问题
--已解决

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
