package cse.cs307.databaseproj2.controller;

import cse.cs307.databaseproj2.Wrapper.FavorPostWrapper;
import cse.cs307.databaseproj2.Wrapper.LikePostWrapper;
import cse.cs307.databaseproj2.Wrapper.SharePostWrapper;
import cse.cs307.databaseproj2.entities.Posts;
import cse.cs307.databaseproj2.mapper.PostMapper;
import cse.cs307.databaseproj2.mapper.UserMapper;
import cse.cs307.databaseproj2.util.CookieManager;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PostMapper postMapper;

//    @GetMapping("/user/{userId}/homepage")
//    public String userHomepage(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response){
//        System.err.println(userId);
//
//        String currentId = CookieManager.findCurrentUser(request);
//        System.err.println(currentId);
//        // Redirect to the corresponding id
//        if(currentId != null && currentId.equals(userId)){
//            // update the validity
//            CookieManager.updateCookieValidity(request, response, "loginId");
//            return "userHomepage";
//        }
////        else if("true".equals(request.getSession().getAttribute("passLogin"))){
////            // remove the attribute
////            System.err.println("came in");
////            System.err.println((String) request.getSession().getAttribute("userId"));
////            CookieManager.addCookie(response,"loginId",(String) request.getSession().getAttribute("userId"), 3600);
////            System.err.println(response.getHeader("Set-Cookie"));
////            request.getSession().removeAttribute("passLogin");
////            request.getSession().removeAttribute("userId");
////            return "userHomepage";
////        }
//        else {
//            // return 404
//            // userid和cookie中的userid不一致，返回404错误
//            return "redirect:/login";
//
//        }
//    }



    @GetMapping("/user/homepage/")
    public String operatePost(HttpServletRequest request, HttpServletResponse response){


        return "success";
    }

    // 点赞
    @GetMapping("/user/like")
    public String likePost(@RequestBody LikePostWrapper lpw, HttpServletRequest request, HttpServletResponse response){
        userMapper.likePost(lpw.getPostId(), lpw.getLikerId());

        CookieManager.updateCookieValidity(request, response, "loginId");
        return "success";
    }

    // 收藏
    @GetMapping("/user/favor")
    public String favorPost(@RequestBody FavorPostWrapper fpw, HttpServletRequest request,HttpServletResponse response){
        userMapper.collectPost(fpw.getPostId(), fpw.getFavorId());

        CookieManager.updateCookieValidity(request, response, "loginId");
        return "success";
    }

    // 转发
    @GetMapping("/user/share")
    public String sharePost(@RequestBody SharePostWrapper spw, HttpServletRequest request, HttpServletResponse response){
        Posts posts = postMapper.findPostById(spw.getPostId());
        userMapper.sharePost(spw.getPostId(), spw.getShareId());
        postMapper.insertNewPost(posts.getTitle(), posts.getContent(), posts.getPostingTime(),
            posts.getAuthorId(), posts.getCity(),
            spw.getShareId(), posts.isAnonymous());
        CookieManager.updateCookieValidity(request, response, "loginId");
        return "success";
    }

}
