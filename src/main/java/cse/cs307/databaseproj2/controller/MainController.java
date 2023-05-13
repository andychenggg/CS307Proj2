package cse.cs307.databaseproj2.controller;

import cse.cs307.databaseproj2.entities.Users;
import cse.cs307.databaseproj2.mapper.UserMapper;
import cse.cs307.databaseproj2.util.CookieManager;
import io.swagger.annotations.Api;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

// RestController will be changed to controller after we finish the html files
@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class MainController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/test2")
    @ResponseBody
    public Users test2(HttpServletRequest request, HttpServletResponse rs) throws IOException {
        return userMapper.findByUsername("holiday");
    }
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


    @GetMapping("/test1")
    @ResponseBody
    public String test1(){
        return "hello";
    }

    @GetMapping("/homepage")
    public String demo(HttpServletRequest request){
        // localhost:8080/homepage
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
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response){

        String userId = CookieManager.findCurrentUser(request);
        System.err.println(userId);
        if(userId == null) {
            response.setHeader("request-login", "failed");
            response.setHeader("Access-Control-Expose-Headers", "request-login");
            return "login";
        }
        else{
            response.setHeader("request-login", "pass");
            response.setHeader("Access-Control-Expose-Headers", "request-login");
            response.setHeader("Access-Control-Allow-Credentials", "true");

            return "redirect:/user/"+userId+"/homepage";
        }
    }
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody Users user, Model model, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

            // 进行登录验证逻辑
        System.err.println(user);
        Users u = userMapper.findByUsername(user.getUsername());
        if(u == null){
            System.out.println((Object) null);
        }
        if (u != null && u.checkPass(user.getPassword())) {

            CookieManager.addCookie(response, "loginId", u.getUserid(), 3600);
            response.setHeader("request-login", "pass");
            response.setHeader("Access-Control-Expose-Headers", "request-login");
            response.setHeader("Access-Control-Allow-Credentials", "true");

            return "Success";

        } else {
            response.setHeader("request-login", "failed");
            response.setHeader("Access-Control-Expose-Headers", "request-login");

            model.addAttribute("error", "Invalid username or password");
            return "Invalid username or password";
        }
    }


    @GetMapping("/signup")
    @ResponseBody
    public String signup(HttpServletRequest request, HttpServletResponse response){
        String userId = CookieManager.findCurrentUser(request);
        if(userId == null) {
            response.setHeader("request-login", "failed");
            response.setHeader("Access-Control-Expose-Headers", "request-login");

            return "signup";
        }
        else{
            response.setHeader("request-login", "pass");
            response.setHeader("Access-Control-Expose-Headers", "request-login");

            return "redirect:/user/"+userId+"/homepage";
        }
    }

    @PostMapping("/signup")
    @ResponseBody
    public Map<String, String> register(@RequestBody Users user, HttpServletResponse response){
        // signup logic
        System.err.println(user);
        HashMap<String, String> resp = new HashMap<>();
        Users u = userMapper.findByUsername(user.getUsername());
        boolean success = true;
        if(u != null){
            success = false;
            resp.put("wrongUsername", "Username already exists. ");
        }
        if(user.getPassword() == null || !user.getPassword().matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*])(?=.*[0-9]).{8,20}$")){
            success = false;
            resp.put("wrongPassword", "length in [8, 20], at least 1 uppercase letter, 1 lowercase letter, and 1 special character.");
        }
        if(user.getPassword() == null || !user.getPhone().matches("^1[0-9]{10}")){
            success = false;
            resp.put("wrongPhone", "Invalid phone");

        }
        if(success){
            long allocateId = 10000000000000000L+userMapper.selectCount(null);
            user.setUserid(String.valueOf(allocateId));
            user.setRegistrationtime(LocalDateTime.now());
            userMapper.insert(user);
            CookieManager.addCookie(response, "loginId", user.getUserid(), 3600);

            response.setHeader("request-login", "pass");
            response.setHeader("Access-Control-Expose-Headers", "request-login");

            return null;
        }
        else{
            response.setHeader("request-login", "failed");
            response.setHeader("Access-Control-Expose-Headers", "request-login");

            System.err.println("fail to signup");
            return resp;
        }
    }

    @DeleteMapping("/logout")
    @ResponseBody
    public String logout(HttpServletResponse response){
        CookieManager.deleteCookie(response, "loginId");
        return "Success logout";
    }



    @GetMapping("/user/following")
    @ResponseBody
    public List<Users> findAllFollowing(){
        return userMapper.findFollowing("951405199501172335");
    }
}
