# CS307Proj2                        

## Basic Information                                           

### Names, student IDs, and the lab session

| **lab session** | **Names** | **student IDs** |
| --------------- | --------- | --------------- |
| 3               | 程嘉朗    | 12111622        |
| 3               | 伍福临    | 12110411        |

## API specification

1. `MainController.java`

   - `query`

     ```java
     @GetMapping("/query")
     @ResponseBody
     public List<Users> query()
     ```

     - HTTP request type: **get**
     - Response data format: **json**
     - Function: Get a list of all users, including all the information that needs to be rendered

   - `findCurrentUser`

     ```java
     @GetMapping("/current_user")
     @ResponseBody
     public String findCurrentUser(HttpServletRequest request)
     ```

     - HTTP request type: **get**
     - Response data format: **json**
     - Function: Gets the current **user name**

   - `findCurrentUserId`

     ```java
     @GetMapping("/current_userId")
     @ResponseBody
     public long findCurrentUserId(HttpServletRequest request)
     ```

     - HTTP request type: **get**
     - Response data format: **json**
     - Function: Gets the current **user ID**

   - `login`

     ```java
     @GetMapping("/login")
     @ResponseBody
     public String login(HttpServletRequest request, HttpServletResponse response)
     ```

     - HTTP request type: **get**
     - Response data format: **json**
     - Function: 

   - `login`

     ```java
     @PostMapping("/login")
     @ResponseBody
     public String login(@RequestBody Users user, Model model, HttpServletRequest request, HttpServletResponse response)
     ```

     - HTTP request type: **post**

     - Function: 

   - `signup`

     ```java
     @GetMapping("/signup")
     @ResponseBody
     public String signup(HttpServletRequest request, HttpServletResponse response)
     ```

     - HTTP request type: **get**
     - Response data format: **json**
     - Function: 

   - `signup`

     ```java
     @PostMapping("/signup")
     @ResponseBody
     public Map<String, String> register(@RequestBody Users user, HttpServletResponse response)
     ```

     - HTTP request type: **post**

     - Function: 

   - `logout`

     ```java
     @DeleteMapping("/logout")
     @ResponseBody
     public String logout(HttpServletRequest request, HttpServletResponse response)
     ```

     - HTTP request type: **delete**

     - Function: 

2. `usercontroller`

   - `findPostInPage`, `findShareInPage `, `findLikeInPage`, `findFavoriteInPage`

     ```java
     @GetMapping("/homepage/post")
     public List<Posts> findPostInPage(@RequestParam("lastPostId") Long lastPostId, @RequestParam("limit") int limit, HttpServletRequest request, HttpServletResponse response)
         
     @GetMapping("/homepage/share")
     public List<Posts> findShareInPage(@RequestParam("lastPostId") Long lastPostId, HttpServletRequest request, @RequestParam("limit") int limit, HttpServletResponse response)
         
     @GetMapping("/homepage/like")
     public List<Posts> findLikeInPage(@RequestParam("lastPostId") Long lastPostId, HttpServletRequest request, @RequestParam("limit") int limit, HttpServletResponse response)
         
     @GetMapping("/homepage/favorite")
     public List<Posts> findFavoriteInPage(@RequestParam("lastPostId") Long lastPostId, HttpServletRequest request, @RequestParam("limit") int limit, HttpServletResponse response)
     ```

     - HTTP request type: **get**
     - Response data format: **json**

     - Request Parameter:

       `lastPostId`:The ID of the last post 

       `limit`:Limit how many the latest posts you can get

     - Function:  Get the latest *limit* **posts**

   - `findHotPostInPage`

     ```java
     @GetMapping("/homepage/hotpost")
     public List<Posts> findHotPostInPage(@RequestParam("lastPostId") Long lastPostId, @RequestParam("limit") int limit, HttpServletRequest request, HttpServletResponse response)
     ```

     - HTTP request type: **get**
     - Response data format: **json**

     - Request Parameter:

       `lastPostId`:The ID of the hottest post

       `limit`:Limit how many the hottest posts you can get

     - Function:  Get the hottest *limit* **posts**

   -  `searchPost`

     ```java
     @PostMapping("/homepage/search")
     public List<Posts> searchPost(@RequestBody ArrayList<Filter> filters, HttpServletRequest request)
     ```

     - HTTP request type: **post**

     - Request Body: 

     - Function:  

   - `findYourPostInPage`, `findMyPost`

     ```java
     @GetMapping("/homepage/yourPost")
     public List<Posts> findYourPostInPage(@RequestParam("lastPostId") Long lastPostId, HttpServletRequest request, @RequestParam("limit") int limit, HttpServletResponse response)
         
     @GetMapping("/user/homepage/post")
     public List<Posts> findMyPost(@RequestParam long senderId, HttpServletRequest request, HttpServletResponse response)
     ```

     - HTTP request type: **get**
     - Response data format: **json**

     - Request Parameter:

       `lastPostId`:The ID of the your last post 

       `limit`:Limit how many your latest posts you can get

     - Function:  Get your latest *limit* **posts**

   - `findMyReplyPost`

     ```java
     @GetMapping("/user/homepage/yourReplyPost")
     public List<Posts> findMyReplyPost(HttpServletRequest request, HttpServletResponse response)
     ```

     - HTTP request type: **get**
     - Response data format: **json**

     - Function:  Get the your **Reply** **posts**

   - `sendPosts`

     ```java
     @PostMapping("/user/homepage/post")
     public int sendPosts(@RequestBody Posts posts, HttpServletRequest request, HttpServletResponse response)
     ```

     - HTTP request type: **post**

     - Request Body: 

     - Function:  Post articles

   - `uploadPic`

     ```java
     @PostMapping("/Files/users/{userId}")
     public void uploadPic(HttpServletRequest request, HttpServletResponse response, @RequestBody MultipartFile file, @PathVariable long userId)
     ```

     - HTTP request type: **post**

     - Request Body: 
     - Path Variable: 

     - Function:  Upload picture when post

   - `findMyReplies`

     ```java
     @GetMapping("/user/homepage/replies")
     public List<Replies> findMyReplies(HttpServletRequest request, HttpServletResponse response)
     ```

     - HTTP request type: **get**
     - Response data format: **json**

     - Function:  Find the content of my all reply

   - `findReplyByPost`

     ```java
     @GetMapping("/user/homepage/post/replies")
     public List<Replies> findReplyByPost(@RequestParam("id") Long postId, HttpServletRequest request, HttpServletResponse response)
     ```

     - HTTP request type: **get**

     - Response data format: **json**

     - Request Param: 

       `id`: The corresponding post id

     - Function:  Find the reply of the corresponding post

   - `sendReplies`

     ```java
     @PostMapping("/user/homepage/replies")
     public int sendReplies(@RequestBody Replies replies, HttpServletRequest request, HttpServletResponse response)
     ```

     - HTTP request type: **post**

     - Request Body: 

     - Function:  Send replies

   - `starReply`

     ```java
     @PostMapping("/user/homepage/replies/star")
     public int starReply(@RequestParam("replyId") long replyId, HttpServletRequest request, HttpServletResponse response)
     ```

     - HTTP request type: **post**

     - Request Param: the id of the corresponding reply

     - Function:  Star the corresponding reply

   - `likePost`

     ```java
     @PostMapping("/user/like")
     public String likePost(@RequestBody LikePostWrapper lpw, HttpServletRequest request, HttpServletResponse response)
     ```

     - HTTP request type: **post**

     - Request Body:
     - Function:  Like post

   - `checkLikes`

     ```java
     @GetMapping("/user/like")
     public List<Posts> checkLikes(HttpServletRequest request, HttpServletResponse response)
     ```

     - HTTP request type: **get**

     - Response data format: **json**
     - Function:  Get the liked posts

   - `favorPost`

     ```java
     @PostMapping("/user/favor")
     public String favorPost(@RequestBody FavorPostWrapper fpw, HttpServletRequest request, HttpServletResponse response)
     ```

     - HTTP request type: **post**

     - Request Body:
     - Function:  Favorite post

   - `checkFavors`

     ```java
     @GetMapping("/user/favor")
     public List<Posts> checkFavors(HttpServletRequest request, HttpServletResponse response)
     ```

     

   - `sharePost`

     ```java
     @PostMapping("/user/share")
     public String sharePost(@RequestBody SharePostWrapper spw, HttpServletRequest request, HttpServletResponse response)
     ```

     - HTTP request type: **post**

     - Request Body:
     - Function:  Share post

   - `unSharePost`

     ```java
     @DeleteMapping("/user/share")
     public String unSharePost(@RequestParam("postId") long originalPostId, HttpServletRequest request, HttpServletResponse response)
     ```

     - HTTP request type: **delete**

     - Request Param: the id of the corresponding post
     - Function: un Share the corresponding post















## Advance Requirement

1. Complete the project using `opengauss` database **(task 1)**

   Install opengauss: 

   - Install a **linux os**: `centos 7`

     > [https://blog.csdn.net/m0_51545690/article/details/123238360](https://blog.csdn.net/m0_51545690/article/details/123238360)

   - Install `docker` in `centos 7`

     > https://docs.docker.com/engine/install/centos/

   - Use `docker` to pull `opengauss`

     > https://blog.csdn.net/coding0820/article/details/121703952

   - Use `docker` to run `opengauss`

     ```shell
     systemctl start docker
     docker start opengauss
     ```

   - connect to the `opengauss` in `datagrip`

     > https://blog.csdn.net/zsxbd/article/details/115143135

2. Enhance the usability of the APIs **(task 2)**

   - picture and video upload

     - use `el-upload` component in `element-ui`

     - bind a reference `upload` to the component

       ```html
       <el-upload ref="upload"></el-upload>
       ```

     - specific the location(the **static resourse directory** of the springboot)

       ```html
       <el-upload 
       	ref="upload"
       	:action= '`http://localhost:9090/Files/users/${this.userId}`'>
       </el-upload>
       ```

     - use function `submit()` to upload the source

       ```js
       this.$refs.upload.submit();
       ```

     - store the filepath in the database
     - when get the posts from the back-end, the from-end also get the filepath, hence it can access the file and show it

   - Multi-parameter search function

     - We allow the users to add **plenty** constraints as long as they want. 

     - We can seach data by: `author`, `sender`, `postingTime`, `title`, `content`

     - `title` and `content`support fuzzy search

     How to seach the data by such restrictions?

     - back-end can get a series of restriction in `List<T>`

     - use `.xml` configuration file to configurate SQL statement, `foreach` is use to iterate the `List`

       ```xml
       <foreach collection='author' item='item' index='index' separator=' OR '>
       	authorname = #{item}
       </foreach>
       ```

     - To see more, please refer to the `findPostsByFilter` in `PostMapper.java`

   - Record the user login status

     If the user does not login, webpage will be redirected to `localhost:8080/login` even if he want to access the `localhost:8080/user/homepage`

     - create `CookieManager` in `util` package, it store all static methods we use to manage the `cookies`

     - get cookies from `HttpServletRequest request`

       ```java
       Cookie[] cookies = request.getCookies();
       ```

     - add/update the cookie to 1 hour

       ```java
       cookie.setMaxAge(60*60);
       cookie.setPath("/"); 
       response.addCookie(cookie);
       ```

     - delete the cookie

       ```java
       cookie.setMaxAge(0);
       cookie.setPath("/"); 
       response.addCookie(cookie);
       ```

3. Technology stack introduction: **(task 3 & 4 & 6)**

   - Backend: `springboot`
   - Front end: `vue2+vuex+vue-router+axios`
   - Component library: `element-ui`
   - Front-end and back-end connection test: `swagger 2`
   - Connection pool: `druid`
   - ORM: `Mybatis-plus`
   - POJO to json: `Jackson`

   advantage:

   1. Separation of **front-end** and **back-end** development: the back-end receives and verifies the front-end request, and then returns the data (**Restful**) required by the front-end in json format, and renders after the front-end gets the data.
   2. springboot: Adopting the method of **convention over configuration**, it provides developers with a very suitable environment for web development. At the same time, based on dependency injection(**DI**) and inversion of control transfer(**IOC**), it helps developers manage a series of tasks.
   3. Vue2: Progressive framework, easier to learn than react, **two-way data binding**, good at making single-page web pages.
   4. druid: The connection pool developed by Alibaba provides monitoring functions to **clearly know the working conditions of the connection pool** and SQL
   5. Mybatis-plus: On the basis of Mybatis, single-table query is enhanced, and SQL statements are bound through **annotations** or configuring `.xml` files

4. create index: (**task 7**)

   To enhance the speed of Multi-parameter search function. At the same time, we find the length of the column `title` in table `posts` are always much shorter then the `content`, hence we decide to create index in column `title`

   ```postgresql
   CREATE INDEX title_index ON posts (title);
   ```

   

