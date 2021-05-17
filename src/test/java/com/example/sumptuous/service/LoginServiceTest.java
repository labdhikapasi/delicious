package com.example.sumptuous.service;

import com.example.sumptuous.bean.User;
import com.example.sumptuous.dao.UserRepository;
import com.example.sumptuous.enums.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testRegisterUser(){
        User user = new User(1L,"demo",null,null,0,"demo","demo", Role.USER,null,null);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        assertThat(loginService.registerUser(user)).isEqualTo(user);
    }

    @Test
    public void testLoginUser(){
        User user = new User(1L,"demo",null,null,0,"demo","demo", Role.USER,null,null);
        Mockito.when(userRepository.findByEmailIdAndPassword(Mockito.anyString(),Mockito.anyString())).thenReturn(user);
        assertThat(loginService.loginUser(user)).isEqualTo(user);
    }
}
