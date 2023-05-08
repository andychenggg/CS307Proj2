package cse.cs307.databaseproj2.controller;

import cse.cs307.databaseproj2.entities.Users;
import cse.cs307.databaseproj2.mapper.UserMapper;
import java.util.List;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

// RestController will be changed to controller after we finish the html files
@RestController
public class MainController {

    @Autowired
    private UserMapper userMapper;
    // hello
    @GetMapping("/hello")
    public String Hello(){
        return "hello 234";
    }
    @GetMapping("/query")
    public List<Users> query(){
        List<Users> list = userMapper.selectList(null);
        System.out.println(list);
        return list;
    }

//    @GetMapping("/login")
//    public int login(){
//
//    }

    @PostMapping("/SignUp")
    public int register(Users user){
        return userMapper.insert(user);
    }

    @GetMapping("/user/following")
    public List<Users> findAllFollowing(){
        return userMapper.findFollowing("951405199501172335");
    }
}
