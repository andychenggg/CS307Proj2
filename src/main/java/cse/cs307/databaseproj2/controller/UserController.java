package cse.cs307.databaseproj2.controller;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import cse.cs307.databaseproj2.Wrapper.*;
import cse.cs307.databaseproj2.entities.Posts;
import cse.cs307.databaseproj2.entities.Replies;
import cse.cs307.databaseproj2.entities.Users;
import cse.cs307.databaseproj2.mapper.PostMapper;
import cse.cs307.databaseproj2.mapper.RepliesMapper;
import cse.cs307.databaseproj2.mapper.UserMapper;
import cse.cs307.databaseproj2.util.CookieManager;
import cse.cs307.databaseproj2.util.FileManager;
import cse.cs307.databaseproj2.util.Filter;
import cse.cs307.databaseproj2.util.GeoIPService;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private RepliesMapper repliesMapper;

    @GetMapping("/homepage/post")
    public List<Posts> findPostInPage(@RequestParam("lastPostId") Long lastPostId,  @RequestParam("limit") int limit, HttpServletRequest request, HttpServletResponse response){
//        System.err.println(userId);
            // update the validity

            // select the post
            if (lastPostId == -1) {
                lastPostId = postMapper.findMaxPostId();
            }
            return postMapper.findPostByIdWithUsernamePageByPage(lastPostId, CookieManager.findCurrentUser(request), limit);
    }

    @GetMapping("/homepage/hotpost")
    public List<Posts> findHotPostInPage(@RequestParam("lastPostId") Long lastPostId,  @RequestParam("limit") int limit, HttpServletRequest request, HttpServletResponse response){
//        System.err.println(userId);
        // update the validity

        // select the post
        if (lastPostId == -1) {
            lastPostId = postMapper.findMaxPostId();
        }
        return postMapper.findHotPostByIdWithUsernamePageByPage(lastPostId, CookieManager.findCurrentUser(request), limit);
    }

    @PostMapping("/homepage/search")
    public List<Posts> searchPost(@RequestBody ArrayList<Filter> filters,
                                                HttpServletRequest request){
        List<String> title = new ArrayList<>();
        List<String> content = new ArrayList<>();
        List<String> author = new ArrayList<>();
        List<String> sender = new ArrayList<>();
        Timestamp start = null;
        Timestamp end = null;
        for (Filter filter : filters){
            String key = filter.getKey();
            switch (key) {
                case "1" -> title.add(filter.getValue());
                case "2" -> author.add(filter.getValue());
                case "3" -> content.add(filter.getValue());
                case "4" -> {
                    if(start == null || start.before(filter.getTimeValue().get(0)))
                        start = filter.getTimeValue().get(0);
                    if(end == null || end.after(filter.getTimeValue().get(1)))
                        end = filter.getTimeValue().get(1);
                }
                case "5" -> sender.add(filter.getValue());
            }
        }
        System.err.println(filters);
        return postMapper.findPostsByFilter(title, content, author, sender, start, end);
    }



    @GetMapping("/homepage/share")
    public List<Posts> findShareInPage(@RequestParam("lastPostId") Long lastPostId, HttpServletRequest request, @RequestParam("limit") int limit,  HttpServletResponse response){
//        System.err.println(userId);
        // update the validity

        // select the post
        if (lastPostId == -1) {
            lastPostId = postMapper.findMaxPostId();
        }
        return postMapper.findShareByIdWithUsernamePageByPage(lastPostId, CookieManager.findCurrentUser(request), limit);
    }

    @GetMapping("/homepage/like")
    public List<Posts> findLikeInPage(@RequestParam("lastPostId") Long lastPostId, HttpServletRequest request, @RequestParam("limit") int limit,  HttpServletResponse response){
//        System.err.println(userId);
        // update the validity

        // select the post
        if (lastPostId == -1) {
            lastPostId = postMapper.findMaxPostId();
        }
        return postMapper.findLikeByIdWithUsernamePageByPage(lastPostId, CookieManager.findCurrentUser(request), limit);
    }

    @GetMapping("/homepage/favorite")
    public List<Posts> findFavoriteInPage(@RequestParam("lastPostId") Long lastPostId, HttpServletRequest request, @RequestParam("limit") int limit,  HttpServletResponse response){
//        System.err.println(userId);
        // update the validity

        // select the post
        if (lastPostId == -1) {
            lastPostId = postMapper.findMaxPostId();
        }
        return postMapper.findFavoriteByIdWithUsernamePageByPage(lastPostId, CookieManager.findCurrentUser(request), limit);
    }

    @GetMapping("/homepage/yourPost")
    public List<Posts> findYourPostInPage(@RequestParam("lastPostId") Long lastPostId, HttpServletRequest request, @RequestParam("limit") int limit,  HttpServletResponse response){
//        System.err.println(userId);
        // update the validity

        // select the post
        if (lastPostId == -1) {
            lastPostId = postMapper.findMaxPostId();
        }
        return postMapper.findYourPostByIdWithUsernamePageByPage(lastPostId, CookieManager.findCurrentUser(request), limit);
    }



    @GetMapping("/user/homepage/post")
    public List<Posts> findMyPost(@RequestParam long senderId, HttpServletRequest request, HttpServletResponse response){
        System.err.println(CookieManager.findCurrentUser(request));
        if(senderId == CookieManager.findCurrentUser(request)) {
            // update the validity
            CookieManager.updateCookieValidity(request, response, "loginId");
            // select the post
            List<Posts> posts = postMapper.findMyPosts(senderId);
            posts.forEach(p -> {
                p.setPostCategories(postMapper.findPostCate(p.getPostId()));
            });
            return posts;
        }
        return null;
    }
    @GetMapping("/user/homepage/yourReplyPost")
    public List<Posts> findMyReplyPost(HttpServletRequest request, HttpServletResponse response){
        System.err.println(CookieManager.findCurrentUser(request));
        if(-1 != CookieManager.findCurrentUser(request)) {
            // update the validity
            CookieManager.updateCookieValidity(request, response, "loginId");
            // select the post
            List<Posts> posts = postMapper.findMyReplyPost(CookieManager.findCurrentUser(request));
            posts.forEach(p -> {
                p.setPostCategories(postMapper.findPostCate(p.getPostId()));
            });
            System.err.println(posts);
            return posts;
        }
        return null;
    }

    @CrossOrigin(origins = "http://localhost:8080/user/homepage")
    @PostMapping("/user/homepage/post")
    public int sendPosts(@RequestBody Posts posts, HttpServletRequest request, HttpServletResponse response)
        throws IOException, GeoIp2Exception {
//        System.err.println(userId);
        // update the validity
        if (-1 != CookieManager.findCurrentUser(request)) {
            CookieManager.updateCookieValidity(request, response, "loginId");
            // select the post
            System.err.println(posts);
            long id = CookieManager.findCurrentUser(request);
            if(posts.getFilename() != null){
                posts.setFilepath(String.format("/Files/users/%d/%s", id, posts.getFilename()));
            }

            String [] locations;
            try{
                locations = GeoIPService.getLocation(posts.getIp());
            }catch (GeoIp2Exception e){
                locations = new String[]{"unknown", "unknown"};
            }
            posts.setAuthorId(CookieManager.findCurrentUser(request));
            posts.setSenderId(CookieManager.findCurrentUser(request));
            posts.setCity(locations[1]);
            posts.setCountry(locations[0]);
            posts.setPostingTime(LocalDateTime.now());
            posts.setOriginPostId(-1);
//            FileManager.saveFile(posts.getFile(), CookieManager.findCurrentUser(request));
            postMapper.insertNewPost(posts);
            System.err.println(posts.getPostId());
            posts.getPostCategories().forEach(e -> {
                postMapper.addCate(e);
                postMapper.addPostCate(posts.getPostId(), postMapper.findCateIdByName(e));
            });
//
            return 1;
        }
        return -1;
    }
    @PostMapping("/Files/users/{userId}")
    public void uploadPic(HttpServletRequest request, HttpServletResponse response, @RequestBody MultipartFile file, @PathVariable long userId){
        System.err.println("here");
        System.err.println("uploadPic"+(file == null));
        System.err.println(CookieManager.findCurrentUser(request));
        System.err.println("Id"+userId);
        try {
            if(file != null)
                FileManager.saveFile(file, userId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/user/homepage/replies")
    public List<Replies> findMyReplies(HttpServletRequest request, HttpServletResponse response){
//        System.err.println(userId);
        // update the validity
//        CookieManager.deleteCookie(response, "loginId");
        System.err.println("findMyReplies");
        CookieManager.printAllCookie(request);
        if(-1 != CookieManager.findCurrentUser(request)) {
            CookieManager.updateCookieValidity(request, response, "loginId");
            // select the post
            return repliesMapper.searchRepliesByAuthorId(CookieManager.findCurrentUser(request));
        }
        return null;
    }

    @GetMapping("/user/homepage/post/replies")
    public List<Replies> findReplyByPost(@RequestParam("id") Long postId, HttpServletRequest request, HttpServletResponse response){

        if(-1 != CookieManager.findCurrentUser(request)) {
            CookieManager.updateCookieValidity(request, response, "loginId");
            List<Replies> replies = repliesMapper.searchRepliesByPostId(postId);
            // select the post
            System.err.println(replies);
            return replies;
        }
        return null;
    }

    @PostMapping("/user/homepage/replies")
    public int sendReplies(@RequestBody Replies replies, HttpServletRequest request, HttpServletResponse response){
//        System.err.println(userId);
        // update the validity
        if(-1 != CookieManager.findCurrentUser(request)) {
            CookieManager.updateCookieValidity(request, response, "loginId");
            // select the replies
            return repliesMapper.addReply(replies.getToReplyId(), replies.getToPostId(),
                replies.getContent(),
                0, CookieManager.findCurrentUser(request),
                replies.isAnonymous());
        }
        return -1;
    }

    @PostMapping("/user/homepage/replies/star")
    public int starReply(@RequestParam("replyId") long replyId, HttpServletRequest request, HttpServletResponse response){

        // update the validity
        if(-1 != CookieManager.findCurrentUser(request)) {
            CookieManager.updateCookieValidity(request, response, "loginId");
            // select the replies
            return repliesMapper.starReply(replyId);
        }
        return -1;
    }




    // 点赞
    @PostMapping("/user/like")
    public String likePost(@RequestBody LikePostWrapper lpw, HttpServletRequest request, HttpServletResponse response){

        if(-1 != CookieManager.findCurrentUser(request)) {
            userMapper.likePost(lpw.getPostId(), CookieManager.findCurrentUser(request));

            CookieManager.updateCookieValidity(request, response, "loginId");
            return "success";
        }
        return "failed";
    }

    // 收藏
    @PostMapping("/user/favor")
    public String favorPost(@RequestBody FavorPostWrapper fpw, HttpServletRequest request,HttpServletResponse response){

        if(-1 != CookieManager.findCurrentUser(request)) {
            userMapper.collectPost(fpw.getPostId(), CookieManager.findCurrentUser(request));

            CookieManager.updateCookieValidity(request, response, "loginId");
            return "success";
        }
        return null;
    }

    // 转发
    @PostMapping("/user/share")
    public String sharePost(@RequestBody SharePostWrapper spw, HttpServletRequest request, HttpServletResponse response){

        if(-1 != CookieManager.findCurrentUser(request)) {
            Posts posts = postMapper.findPostById(spw.getPostId());
            userMapper.sharePost(spw.getPostId(), CookieManager.findCurrentUser(request));
            posts.setSenderId(CookieManager.findCurrentUser(request));
            postMapper.insertNewPost(posts);
            Long newPostId = postMapper.findNewSharePostId(spw.getPostId(), CookieManager.findCurrentUser(request));
                System.err.println("newPostId"+newPostId);
            postMapper.copyCate(newPostId, spw.getPostId());
            CookieManager.updateCookieValidity(request, response, "loginId");
            return "success";
        }
        return null;
    }

    @DeleteMapping("/user/share")
    public String unSharePost(@RequestParam("postId") long originalPostId, HttpServletRequest request, HttpServletResponse response){

        long currentUser = CookieManager.findCurrentUser(request);
        Integer affected = postMapper.deShares(currentUser, originalPostId);
        if(affected != null){
            System.err.println("affected"+affected);
            postMapper.deSharePost(originalPostId, currentUser);
            return "success";
        }
        return null;
    }

    @GetMapping("/user/like")
    public List<Posts> checkLikes( HttpServletRequest request, HttpServletResponse response){
        if(-1 != CookieManager.findCurrentUser(request)) {
            CookieManager.updateCookieValidity(request, response, "loginId");
            List<Posts> posts = postMapper.findLikePosts(CookieManager.findCurrentUser(request));
            posts.forEach(e -> {
                e.setPostCategories(postMapper.findPostCate(e.getPostId()));
            });
            return posts;
        }
        return null;
    }

    @GetMapping("/user/favor")
    public List<Posts> checkFavors(HttpServletRequest request, HttpServletResponse response){
        if(-1 != CookieManager.findCurrentUser(request)) {
            CookieManager.updateCookieValidity(request, response, "loginId");
            List<Posts> posts = postMapper.findFavoritePosts(CookieManager.findCurrentUser(request));
            posts.forEach(e -> {
                e.setPostCategories(postMapper.findPostCate(e.getPostId()));
            });
            return posts;
        }
        return null;
    }

    @GetMapping("/user/share")
    public List<Posts> checkShares(HttpServletRequest request, HttpServletResponse response){
        if(-1 != CookieManager.findCurrentUser(request)) {
            CookieManager.updateCookieValidity(request, response, "loginId");
            List<Posts> posts = postMapper.findSharePosts(CookieManager.findCurrentUser(request));
            posts.forEach(e -> {
                e.setPostCategories(postMapper.findPostCate(e.getPostId()));
            });
            return posts;
        }
        return null;
    }

    // follow someone
    @PostMapping("/user/follow")
    public int follow(@RequestBody FollowUserWrapper fuw, HttpServletRequest request, HttpServletResponse response){
        if(-1 != CookieManager.findCurrentUser(request)) {
            CookieManager.updateCookieValidity(request, response, "loginId");
            return userMapper.followOthers(CookieManager.findCurrentUser(request), fuw.getFollowigId());
        }
        return -1;
    }

    @PostMapping("/user/shield")
    public int shield(@RequestBody ShieldUserWrapper fuw, HttpServletRequest request, HttpServletResponse response){
        if(-1 != CookieManager.findCurrentUser(request)) {
            CookieManager.updateCookieValidity(request, response, "loginId");
            return userMapper.shieldOthers(CookieManager.findCurrentUser(request), fuw.getShieldId());
        }
        return -1;
    }


    @DeleteMapping("/user/follow")
    public int unfollow(@RequestParam("followigid") long followigid, HttpServletRequest request, HttpServletResponse response){
        if(-1 != CookieManager.findCurrentUser(request)) {
            CookieManager.updateCookieValidity(request, response, "loginId");
            return userMapper.deFollowOthers(CookieManager.findCurrentUser(request), followigid);
        }
        return -1;
    }

    @DeleteMapping("/user/shield")
    public int unshield(@RequestParam("shieldid") long shieldid, HttpServletRequest request, HttpServletResponse response){
        if(-1 != CookieManager.findCurrentUser(request)) {
            CookieManager.updateCookieValidity(request, response, "loginId");
            return userMapper.deShieldOthers(CookieManager.findCurrentUser(request), shieldid);
        }
        return -1;
    }

    @DeleteMapping("/user/like")
    public int unLike(@RequestParam("postId") long postId, HttpServletRequest request, HttpServletResponse response){
        if(-1 != CookieManager.findCurrentUser(request)) {
            CookieManager.updateCookieValidity(request, response, "loginId");
            return userMapper.deLikePosts(CookieManager.findCurrentUser(request), postId);
        }
        return -1;
    }

    @DeleteMapping("/user/favorite")
    public int unFavorite(@RequestParam("postId") long postId, HttpServletRequest request, HttpServletResponse response){
        if(-1 != CookieManager.findCurrentUser(request)) {
            CookieManager.updateCookieValidity(request, response, "loginId");
            return userMapper.deFavoritePosts(CookieManager.findCurrentUser(request), postId);
        }
        return -1;
    }

    @GetMapping("/user/follow")
    public List<Users> findFollow(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam("offset") long offset,
                                  @RequestParam("limit") long limit){
        if(-1 != CookieManager.findCurrentUser(request)){

            CookieManager.updateCookieValidity(request, response, "loginId");
            List<Users> users = userMapper.findFollowing(CookieManager.findCurrentUser(request), offset, limit);
            System.err.println(users.get(0).isIsShield());
            return users;
        }
        return null;
    }

    @GetMapping("user/follow/ids")
    public List<Long> findFollowIds(HttpServletRequest request, HttpServletResponse response){
        if(-1 != CookieManager.findCurrentUser(request)){

            CookieManager.updateCookieValidity(request, response, "loginId");

            return userMapper.findFollowed(CookieManager.findCurrentUser(request));
        }
        return null;
    }

    @GetMapping("user/like/ids")
    public List<Long> findLikeIds(HttpServletRequest request, HttpServletResponse response){
        if(-1 != CookieManager.findCurrentUser(request)){

            CookieManager.updateCookieValidity(request, response, "loginId");

            return userMapper.findLiked(CookieManager.findCurrentUser(request));
        }
        return null;
    }

    @GetMapping("user/favorite/ids")
    public List<Long> findFavoriteIds(HttpServletRequest request, HttpServletResponse response){
        if(-1 != CookieManager.findCurrentUser(request)){

            CookieManager.updateCookieValidity(request, response, "loginId");

            return userMapper.findFavorite(CookieManager.findCurrentUser(request));
        }
        return null;
    }

    @GetMapping("user/share/ids")
    public List<Long> findShareIds(HttpServletRequest request, HttpServletResponse response){
        if(-1 != CookieManager.findCurrentUser(request)){

            CookieManager.updateCookieValidity(request, response, "loginId");

            return userMapper.findShare(CookieManager.findCurrentUser(request));
        }
        return null;
    }

}
