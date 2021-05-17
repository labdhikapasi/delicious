package com.example.sumptuous.controller;

import com.example.sumptuous.bean.User;
import com.example.sumptuous.controller.LoginController;
import com.example.sumptuous.enums.Role;
import com.example.sumptuous.service.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SumptuousApplicationTests {

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    @Test
    public void testRegisterUser(){
        User user = new User(1L,"demo",null,null,0,"demo","demo", Role.USER,null,null);
        when(loginService.registerUser(user)).thenReturn(user);
        assertEquals(user, loginController.registerUser(user));
    }

}
