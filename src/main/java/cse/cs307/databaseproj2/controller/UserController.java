package cse.cs307.databaseproj2.controller;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import cse.cs307.databaseproj2.Wrapper.FavorPostWrapper;
import cse.cs307.databaseproj2.Wrapper.FollowUserWrapper;
import cse.cs307.databaseproj2.Wrapper.LikePostWrapper;
import cse.cs307.databaseproj2.Wrapper.PostsRequestWrapper;
import cse.cs307.databaseproj2.Wrapper.SharePostWrapper;
import cse.cs307.databaseproj2.entities.Posts;
import cse.cs307.databaseproj2.entities.Replies;
import cse.cs307.databaseproj2.entities.Users;
import cse.cs307.databaseproj2.mapper.PostMapper;
import cse.cs307.databaseproj2.mapper.RepliesMapper;
import cse.cs307.databaseproj2.mapper.UserMapper;
import cse.cs307.databaseproj2.util.CookieManager;
import cse.cs307.databaseproj2.util.GeoIPService;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
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
            return postMapper.findPostByIdWithUsernamePageByPage(lastPostId, limit);
    }

    @GetMapping("/user/homepage/post")
    public List<Posts> findMyPost(@RequestParam long senderId, HttpServletRequest request, HttpServletResponse response){
        System.err.println(CookieManager.findCurrentUser(request));
        if(senderId == CookieManager.findCurrentUser(request)) {
            // update the validity
            CookieManager.updateCookieValidity(request, response, "loginId");
            // select the post
            return postMapper.findMyPosts(senderId);
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
//        System.err.println(userId);
        // update the validity
//        CookieManager.deleteCookie(response, "loginId");
        CookieManager.printAllCookie(request);
        if(-1 != CookieManager.findCurrentUser(request)) {
            CookieManager.updateCookieValidity(request, response, "loginId");
            // select the post
            return repliesMapper.searchRepliesByPostId(postId);
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




    // 点赞
    @PostMapping("/user/like")
    public String likePost(@RequestBody LikePostWrapper lpw, HttpServletRequest request, HttpServletResponse response){

        if(lpw.getLikerId() == CookieManager.findCurrentUser(request)) {
            userMapper.likePost(lpw.getPostId(), lpw.getLikerId());

            CookieManager.updateCookieValidity(request, response, "loginId");
            return "success";
        }
        return "failed";
    }

    // 收藏
    @PostMapping("/user/favor")
    public String favorPost(@RequestBody FavorPostWrapper fpw, HttpServletRequest request,HttpServletResponse response){

        if(fpw.getFavorId() == CookieManager.findCurrentUser(request)) {
            userMapper.collectPost(fpw.getPostId(), fpw.getFavorId());

            CookieManager.updateCookieValidity(request, response, "loginId");
            return "success";
        }
        return null;
    }

    // 转发
    @PostMapping("/user/share")
    public String sharePost(@RequestBody SharePostWrapper spw, HttpServletRequest request, HttpServletResponse response){

        if(spw.getShareId() == CookieManager.findCurrentUser(request)) {
            Posts posts = postMapper.findPostById(spw.getPostId());
            userMapper.sharePost(spw.getPostId(), spw.getShareId());
            posts.setSenderId(spw.getShareId());
            postMapper.insertNewPost(posts);
            CookieManager.updateCookieValidity(request, response, "loginId");
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


    @DeleteMapping("/user/follow")
    public int unfollow(@RequestParam("followigid") long followigid, HttpServletRequest request, HttpServletResponse response){
        if(-1 != CookieManager.findCurrentUser(request)) {
            CookieManager.updateCookieValidity(request, response, "loginId");
            return userMapper.deFollowOthers(CookieManager.findCurrentUser(request), followigid);
        }
        return -1;
    }

    @GetMapping("/user/follow")
    public List<Users> findFollow(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam("offset") long offset,
                                  @RequestParam("limit") long limit){
        if(-1 != CookieManager.findCurrentUser(request)){

            CookieManager.updateCookieValidity(request, response, "loginId");
            return userMapper.findFollowing(CookieManager.findCurrentUser(request), offset, limit);
        }
        return null;
    }


}
