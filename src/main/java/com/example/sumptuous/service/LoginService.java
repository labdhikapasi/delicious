package com.example.sumptuous.service;

import com.example.sumptuous.bean.User;
import com.example.sumptuous.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user){
        userRepository.save(user);
        return user;
    }

    public User loginUser(User user){
        user = userRepository.findByEmailIdAndPassword(user.getEmailId(), user.getPassword());
        return user;
    }
}
