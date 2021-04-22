package com.example.sumptuous.dao;


import com.example.sumptuous.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailIdAndPassword(String emailId, String password);
}
