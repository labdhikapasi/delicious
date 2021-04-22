package com.example.sumptuous.controller;


import com.example.sumptuous.bean.User;
import com.example.sumptuous.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @PostMapping("/register")
//    public String verifyEmail(@RequestBody Associates associates) {
    public User registerUser(@RequestBody User user) {

        return loginService.registerUser(user);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user){
        return loginService.loginUser(user);
    }
}
