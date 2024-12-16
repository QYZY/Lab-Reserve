package com.edu.nefu.labreserve.service;

import com.edu.nefu.labreserve.dox.User;
import com.edu.nefu.labreserve.dox.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testAddUser() {
        userService.addUser("admin","admin123");
//        userService.addUser("qaq","mima1234", UserRole.SUPER_ADMIN);
        // 使用断言验证是否会抛出异常
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//            userService.addUser("qyzy1", "mima1234");
//        });

//        assertEquals("Username already exists", exception.getMessage());
    }

    @Test
    void testDelUser() {
        userService.delUser("qyzy1");
    }


    @Test
    void testGetAllUsers() {
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    void testGetUserByUsername() {
        User user = userService.getUserByUsername("qyzy");
        System.out.println(user);
    }

    @Test
    void testGetUsersByRole() {
        List<User> users = userService.getUsersByRole(UserRole.SUPER_ADMIN);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    void testChangePassword() {
        userService.changePassword("miguo","mima1234","miguoqaq");
    }

    @Test
    void testResetPassword() {
        userService.resetPassword("miguo", "mima1234");
    }

    @Test
    void testUpdateRole() {
        userService.updateRole("qyzy",UserRole.SUPER_ADMIN);
    }


}