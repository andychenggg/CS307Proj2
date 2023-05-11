package cse.cs307.databaseproj2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RestAPIController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
