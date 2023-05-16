# CS307Proj2

## QUESTIONS

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

## 重建数据库
```postgresql
drop table replies;
drop table shares;
drop table favorites;
drop table postcategory;
drop table categories;
drop table likes;
drop table Pictures;
drop table Videos;
drop table posts;
drop table cities;
drop table FollowedBy;
drop table users;

create table Users (
    userID varchar,
    username varchar not null,
    password varchar not null,
    RegistrationTime timestamp not null,
    Phone varchar not null,
    constraint ID_pm primary key (userID),
    constraint user_un unique (username)
);
create table FollowedBy(
    userID varchar,
    FollowigID varchar,
    constraint ID_Fo_pm primary key (userID, FollowigID),
    constraint Follower foreign key (userID) references Users(userID),
    constraint Followig foreign key (FollowigID) references Users(userID)
);
create table Cities(
    City varchar unique not null,
    Country varchar not null,
    constraint cityPm primary key (City)
);
create table Posts(
    PostID bigserial,
    -- It's ok to have a null in either title or content
    Title varchar,
    Content varchar,
    PostingTime timestamp not null,
    AuthorID varchar not null,
    City varchar not null ,
    SenderID varchar,
    Anonymous boolean,
    constraint postID primary key(PostID),
    constraint SenderOfPost foreign key (SenderID) references Users(userID),
    constraint AuthorOfPost foreign key (AuthorID) references Users(userID),
    constraint cityRef foreign key (City) references Cities(City)
);
create table Pictures(
    PostID bigint,
    PicPath varchar,
    constraint PostOfPic foreign key (PostID) references Posts(PostID)
);
create table Videos(
    PostID bigint,
    VideoPath varchar,
    constraint PostOfVi foreign key (PostID) references Posts(PostID)
);

create table Categories(
    CategoryID bigserial,
    Category varchar unique,
    constraint Ca_pm primary key (CategoryID)
);
create table PostCategory(
    PostID bigint,
    CategoryID bigint,
    constraint PoCa_pm primary key (PostID, CategoryID),
    constraint PostRef foreign key (PostID) references Posts(PostID),
    constraint CategoryRef foreign key (CategoryID) references Categories(CategoryID)

);

create table Favorites(
    PostID bigint,
    CollectorID varchar,
    constraint ID_Co_pm primary key (PostID, CollectorID),
    constraint FavoriteBy foreign key (PostID) references Posts(PostID),
    constraint Collector foreign key (CollectorID) references Users(userID)
);

create table Shares(
    PostID bigint,
    SharerID varchar,
    constraint ID_Sh_pm primary key (PostID, SharerID),
    constraint SharedBy foreign key (PostID) references Posts(PostID),
    constraint Sharer foreign key (SharerID) references Users(userID)
);

create table Likes(
    PostID bigint,
    LikerID varchar,
    constraint ID_li_Co_pm primary key (PostID, LikerID),
    constraint LikedBy foreign key (PostID) references Posts(PostID),
    constraint Liker foreign key (LikerID) references Users(userID)
);
create table Replies(
    ReplyId bigserial,
    ToReplyId bigint,
    ToPostId bigint,
    Content varchar not null,
    Stars int not null,
    AuthorID varchar not null,
    Anonymous boolean,
    constraint ID_re_pm primary key (ReplyId),
    constraint AuthorOfReply foreign key (AuthorID) references Users(userID)
);
```
并添加相应的触发器
```postgresql
-- Drop the trigger
DROP TRIGGER IF EXISTS set_userid_trigger ON Users;

-- Drop the function
DROP FUNCTION IF EXISTS set_userid_function();

-- Drop the sequence
DROP SEQUENCE IF EXISTS generate_userId;

CREATE SEQUENCE generate_userId
    START WITH 110000000000
    INCREMENT BY 1;

CREATE or replace FUNCTION set_userid_function()
    RETURNS trigger AS
$$
BEGIN
    NEW.Userid = NEXTVAL('generate_userId')::varchar;
    RETURN NEW;
END;
$$
    LANGUAGE plpgsql;
CREATE TRIGGER set_userid_trigger
    BEFORE INSERT ON Users
    FOR EACH ROW
EXECUTE procedure set_userid_function();
```
## TODO: 
5/17: CJL

完成前后端的规范设置；

完成后端和数据库的请求方法设置；

完成前端的homepage.vue向后端发送请求的连通性测试

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
- 注册用户，登录 -- 已完成
- 点赞，收藏，转发，发送post请求
```json lines
// 点赞
{
    postId: ...,
    favorId: ...
}
// 收藏
{
    postId: ...,
    likerId: ...
}
// 转发
{
    postId: ...,
    shareId: ...
}
```
- 查看点赞，收藏，转发
```json lines
// 查看点赞
{
    postId: 任意值,
    favorId: ...
}
// 查看收藏
{
    postId: 任意值,
    likerId: ...
}
// 查看转发
{
    postId: 任意值,
    shareId: ...
}
// 前端拿到的数据的形式
[
    {
        postId: ...,
        title: ...,
        content: ...,
        postingTime: ...,
        authorId: ...,
        city: ...,
        senderId: ...,
        anonymous: ...,
        country: ...,
        postCategories: ...,
        authorName: ...,
        senderName: ...
    },
    {
        ...: ...
    }
]
```
- 关注，取关用户
```json lines
//发送post
{
    userId: ...,
    followigId: ...
}
//发送delete请求
{
    userId: ...,
    followigId: ...
}
```
- 查看关注列表
```json lines
// 发送get
{
  userId: ...,
  followigId: 任意值
}
```
- 发布帖子, 评论：
```json lines
//发送post请求，请求体为
// 具体请查看PostMapper.insertNewPost
{
  ...: ...
}
// 具体请查看RepliesMapper.addReply
//replyId 和 authorName 不需要填写
{
  ...: ...
}
```
- 查看自己发布的贴子, 评论
```json lines
// 发送get请求
{
  authorId: ...
}
```