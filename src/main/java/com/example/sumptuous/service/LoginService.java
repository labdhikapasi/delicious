package com.example.sumptuous.service;

import com.example.sumptuous.bean.User;
import com.example.sumptuous.controller.LoginController;
import com.example.sumptuous.dao.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user){
        logger.info("[registerUser] argument : user emailId : "+user.getEmailId());
        User user1 = userRepository.save(user);
        logger.info("[registerUser] response : user1 id : "+user1.getId());
        return user1;
    }

    public User loginUser(User user){
        logger.info("[loginUser] argument : user emailId : "+user.getEmailId());
        user = userRepository.findByEmailIdAndPassword(user.getEmailId(), user.getPassword());
        logger.info("[loginUser] response : user id : "+user.getId());
        return user;
    }
}
