package cse.cs307.databaseproj2.controller;

import cse.cs307.databaseproj2.entities.Users;
import cse.cs307.databaseproj2.mapper.UserMapper;
import cse.cs307.databaseproj2.util.CookieManager;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.Nullable;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// RestController will be changed to controller after we finish the html files
@Controller
public class MainController {

    @Autowired
    private UserMapper userMapper;
    // hello
    @GetMapping("/test")
    public String test(HttpServletRequest request, HttpServletResponse rs) throws IOException {
        Cookie c=  new Cookie("bbb", "aa");
        c.setMaxAge(60);
        rs.addCookie(c);
        for(Cookie ck: request.getCookies()){
            System.err.println(ck.getName()+" "+ck.getValue()+" "+ck.getMaxAge());
        }
        rs.sendRedirect("homepage");
        return "homepage";
    }

    @GetMapping("/")
    public String indexToHomepage() {
        return "redirect:/homepage";
    }
    @GetMapping("/homepage")
    public String demo(HttpServletRequest request){
//        for(Cookie ck: request.getCookies()){
//            System.err.println(ck.getName()+" "+ck.getValue()+" "+ck.getMaxAge());
//        }
        String userId = CookieManager.findCurrentUser(request);
        if(userId == null) {
            return "homepage";
        }
        else{
            return "redirect:/user/"+userId+"/homepage";
        }
    }

    @GetMapping("/query")
    @ResponseBody
    public List<Users> query(){
        List<Users> list = userMapper.selectList(null);
        System.out.println(list);
        return list;
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response){

//        Cookie cookie = new Cookie("loginId", "aaa");
//        cookie.setMaxAge(0);
//        response.addCookie(cookie);
//        System.err.println(Arrays.toString(request.getCookies()));
        String userId = CookieManager.findCurrentUser(request);
        System.err.println(userId);
        if(userId == null) {
            return "login";
        }
        else{
            return "redirect:/user/"+userId+"/homepage";
        }
    }
    @PostMapping("/login")
    public String login(Users user, Model model, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

            // 进行登录验证逻辑
        System.err.println(user);
        Users u = userMapper.findByUsername(user.getUsername());
        if(u == null){
            System.out.println((Object) null);
        }
        if (u != null && u.checkPass(user.getPassword())) {

            CookieManager.addCookie(response, "loginId", u.getUserid(), 3600);
//            Cookie c = new Cookie("loginId", u.getUserid());
//            c.setMaxAge(3600);
//            response.addCookie(c);
//            request.getSession().setAttribute("passLogin", "true");
//            request.getSession().setAttribute("userId", u.getUserid());
//            RequestDispatcher dispatcher = request.getRequestDispatcher("/homepage");
//
//            dispatcher.forward(request, response);
            return "redirect:/user/"+u.getUserid()+"/homepage";

        } else {

            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }


    @GetMapping("/signup")
    public String signup(HttpServletRequest request){
        String userId = CookieManager.findCurrentUser(request);
        if(userId == null) {
            return "signup";
        }
        else{
            return "redirect:/user/"+userId+"/homepage";
        }
    }

    @PostMapping("/signup")
    public String register(Users user, Model model, HttpServletResponse response){
        // signup logic
        System.err.println(user);
        Users u = userMapper.findByUsername(user.getUsername());
        boolean success = true;
        if(u != null){
            success = false;
            model.addAttribute("repetitiveUsername", "Username already exists. ");
        }
        if(!user.getPassword().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*])(?=.*[0-9]).{8,20}$")){
            success = false;
            model.addAttribute("invalidPassword", "length in [8, 20], at least 1 uppercase letter, 1 lowercase letter, and 1 special character");
        }
        if(!user.getPhone().matches("^1[0-9]{10}")){
            success = false;
            model.addAttribute("invalidPhone", "Invalid phone");

        }
        if(success){
            long allocateId = 10000000000000000L+userMapper.selectCount(null);
            user.setUserid(String.valueOf(allocateId));
            user.setRegistrationtime(LocalDateTime.now());
            userMapper.insert(user);
            CookieManager.addCookie(response, "loginId", user.getUserid(), 3600);
            return "redirect:/user/"+user.getUserid()+"/homepage";
        }
        else{
            System.err.println("fail to signup");
            return "signup";
        }
    }




    @GetMapping("/user/following")
    @ResponseBody
    public List<Users> findAllFollowing(){
        return userMapper.findFollowing("951405199501172335");
    }
}
