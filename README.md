# CS307 Project 2

## API specification

1. `MainController.java`

   - `query`

     ```java
     @GetMapping("/query")
     @ResponseBody
     public List<Users> query()
     ```

     http请求类型: **get**

     响应数据格式: **json**

     功能：获取所有用户的列表，包含所有需要渲染的信息













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

   
