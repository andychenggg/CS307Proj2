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

