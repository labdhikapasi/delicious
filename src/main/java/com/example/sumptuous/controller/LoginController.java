package com.example.sumptuous.controller;


import com.example.sumptuous.bean.User;
import com.example.sumptuous.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @PostMapping("/register")
//    public String verifyEmail(@RequestBody Associates associates) {
    public User registerUser(@RequestBody User user) {
        logger.info("[registerUser] argument : user emailId : "+user.getEmailId());
        User userResponse = loginService.registerUser(user);
        logger.info("[registerUser] response : userResponse : "+userResponse.toString());
        return userResponse;
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User user){
        logger.info("[loginUser] argument : user emailId : "+user.getEmailId());
        User userResponse = loginService.loginUser(user);
        logger.info("[loginUser] response : userResponse : "+userResponse.toString());
        return userResponse;
    }
}
