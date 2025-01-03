package com.edu.nefu.labreserve.service;

import com.edu.nefu.labreserve.dox.User;
import com.edu.nefu.labreserve.dox.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testAddUser() {
        userService.addUser("teacher01", "teacher01");
//        userService.addUser("qaq","mima1234", UserRole.SUPER_ADMIN);
        // 使用断言验证是否会抛出异常
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//            userService.addUser("qyzy1", "mima1234");
//        });

//        assertEquals("Username already exists", exception.getMessage());
    }

    @Test
    void testDelUser() {
        userService.delUser(1L);
    }


    @Test
    void testGetAllUsers() {
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            log.debug(user.toString());
        }
    }

    @Test
    void testGetUserByUsername() {
        User user = userService.getUserByUsername("qyzy");
        log.debug(user.toString());
    }

    @Test
    void testGetUsersByRole() {
        List<User> users = userService.getUsersByRole(UserRole.SUPER_ADMIN);
        for (User user : users) {
            log.debug(user.toString());
        }
    }

    @Test
    void testChangePassword() {
        userService.changePassword("miguo", "mima1234", "miguoqaq");
    }

    @Test
    void testResetPassword() {
        userService.resetPassword(1L, "mima1234");
    }

    @Test
    void testUpdateRole() {
        userService.updateUser(1L,"qyzy", UserRole.SUPER_ADMIN);
    }

    @Test
    void testInitUser() {
        User[] users = new User[]{
                User.builder()
                        .username("admin")
                        .password("admin123")
                        .role(UserRole.LAB_ADMIN)
                        .build(),
                User.builder()
                        .username("test")
                        .password("test123")
                        .role(UserRole.TEACHER)
                        .build(),
                User.builder()
                        .username("qyzy")
                        .password("mima1234")
                        .role(UserRole.SUPER_ADMIN)
                        .build()
        };
        for (User user : users) {
            userService.addUser(user.getUsername(), user.getPassword());
        }

    }

}