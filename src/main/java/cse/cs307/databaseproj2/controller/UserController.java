package cse.cs307.databaseproj2.controller;

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
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private RepliesMapper repliesMapper;

    @GetMapping("/homepage/post")
    public List<Posts> findPostInPage(@RequestBody PostsRequestWrapper prw, HttpServletRequest request, HttpServletResponse response){
//        System.err.println(userId);
            // update the validity
            CookieManager.updateCookieValidity(request, response, "loginId");
            // select the post
            if(prw.getLastPostId() == -1){
                prw.setLastPostId(postMapper.findMaxPostId());
            }
            return postMapper.findPostByIdWithUsernamePageByPage(prw.getLastPostId(), prw.getLimit());
    }

    @GetMapping("/user/homepage/post/receive")
    public List<Posts> findMyPost(@RequestBody long authorId, HttpServletRequest request, HttpServletResponse response){
//        System.err.println(userId);
        // update the validity
        CookieManager.updateCookieValidity(request, response, "loginId");
        // select the post
        return postMapper.findMyPosts(authorId);
    }

    @PostMapping("/user/homepage/post/send")
    public int sendPosts(@RequestBody Posts posts, HttpServletRequest request, HttpServletResponse response){
//        System.err.println(userId);
            // update the validity
            CookieManager.updateCookieValidity(request, response, "loginId");
            // select the post
            return postMapper.insertNewPost(posts.getTitle(), posts.getContent(),
                posts.getPostingTime(), posts.getAuthorId(), posts.getCity(), posts.getCountry(), posts.getSenderId(), posts.isAnonymous());
    }

    @GetMapping("/user/homepage/replies/receive")
    public List<Replies> findMyReplies(@RequestBody long authorId, HttpServletRequest request, HttpServletResponse response){
//        System.err.println(userId);
        // update the validity
        CookieManager.updateCookieValidity(request, response, "loginId");
        // select the post
        return repliesMapper.searchRepliesByAuthorId(authorId);
    }

    @PostMapping("/user/homepage/replies/send")
    public int sendReplies(@RequestBody Replies replies, HttpServletRequest request, HttpServletResponse response){
//        System.err.println(userId);
        // update the validity
        CookieManager.updateCookieValidity(request, response, "loginId");
        // select the replies
        return repliesMapper.addReply(replies.getToReplyId(), replies.getToPostId(), replies.getContent(),
            replies.getStars(), replies.getAuthorId(),
            replies.isAnonymous());
    }




    // 点赞
    @PostMapping("/user/like")
    public String likePost(@RequestBody LikePostWrapper lpw, HttpServletRequest request, HttpServletResponse response){
        userMapper.likePost(lpw.getPostId(), lpw.getLikerId());

        CookieManager.updateCookieValidity(request, response, "loginId");
        return "success";
    }

    // 收藏
    @PostMapping("/user/favor")
    public String favorPost(@RequestBody FavorPostWrapper fpw, HttpServletRequest request,HttpServletResponse response){
        userMapper.collectPost(fpw.getPostId(), fpw.getFavorId());

        CookieManager.updateCookieValidity(request, response, "loginId");
        return "success";
    }

    // 转发
    @PostMapping("/user/share")
    public String sharePost(@RequestBody SharePostWrapper spw, HttpServletRequest request, HttpServletResponse response){
        Posts posts = postMapper.findPostById(spw.getPostId());
        userMapper.sharePost(spw.getPostId(), spw.getShareId());
        postMapper.insertNewPost(posts.getTitle(), posts.getContent(), posts.getPostingTime(),
            posts.getAuthorId(), posts.getCity(), posts.getCountry(),
            spw.getShareId(), posts.isAnonymous());
        CookieManager.updateCookieValidity(request, response, "loginId");
        return "success";
    }

    @GetMapping("/user/like")
    public List<Posts> checkLikes(@RequestParam long userId, HttpServletRequest request, HttpServletResponse response){
        if(userId == CookieManager.findCurrentUser(request)) {
            CookieManager.updateCookieValidity(request, response, "loginId");
            List<Posts> posts = postMapper.findLikePosts(userId);
            posts.forEach(e -> {
                e.setPostCategories(postMapper.findPostCate(e.getPostId()));
            });
            return posts;
        }
        return null;
    }

    @GetMapping("/user/favor")
    public List<Posts> checkFavors(@RequestParam long userId, HttpServletRequest request, HttpServletResponse response){
        if(userId == CookieManager.findCurrentUser(request)) {
            CookieManager.updateCookieValidity(request, response, "loginId");
            List<Posts> posts = postMapper.findFavoritePosts(userId);
            posts.forEach(e -> {
                e.setPostCategories(postMapper.findPostCate(e.getPostId()));
            });
            return posts;
        }
        return null;
    }

    @GetMapping("/user/share")
    public List<Posts> checkShares(@RequestParam long userId, HttpServletRequest request, HttpServletResponse response){
        if(userId == CookieManager.findCurrentUser(request)) {
            CookieManager.updateCookieValidity(request, response, "loginId");
            List<Posts> posts = postMapper.findSharePosts(userId);
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
        CookieManager.updateCookieValidity(request, response, "loginId");
        return userMapper.followOthers(fuw.getUserId(), fuw.getFollowigId());
    }


    @DeleteMapping("/user/follow")
    public int unfollow(@RequestBody FollowUserWrapper fuw, HttpServletRequest request, HttpServletResponse response){
        CookieManager.updateCookieValidity(request, response, "loginId");
        return userMapper.deFollowOthers(fuw.getUserId(), fuw.getFollowigId());
    }

    @GetMapping("/user/follow")
    public List<Users> findFollow(@RequestParam long userId, HttpServletRequest request, HttpServletResponse response){
        if(userId == CookieManager.findCurrentUser(request)){

            CookieManager.updateCookieValidity(request, response, "loginId");
            return userMapper.findFollowing(userId);
        }
        return null;
    }


}
