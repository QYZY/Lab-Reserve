package com.edu.nefu.labreserve.controller;

import com.edu.nefu.labreserve.dox.User;
import com.edu.nefu.labreserve.dox.UserRole;
import com.edu.nefu.labreserve.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.edu.nefu.labreserve.dox.UserRole.TEACHER;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<User>> listUser() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getUsername/{userId}")
    public ResponseEntity<String> getUsername(@PathVariable Long userId) {
        String username = userService.getUsernameById(userId);
        if (username != null) {
            return ResponseEntity.ok(username);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<User>> getAllTeachers() {
        List<User> teachers = userService.getUsersByRole(UserRole.TEACHER);
        return ResponseEntity.ok(teachers);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam UserRole role) {
        User user = userService.addUser(username, password, role);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser(
            @RequestParam Long userId,
            @RequestParam String username,
            @RequestParam UserRole role) {
        User user = userService.updateUser(userId, username, role);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.delUser(userId);
        return ResponseEntity.ok("删除成功");
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(
            @RequestParam Long userId,
            @RequestParam String newPassword) {
        userService.resetPassword(userId, newPassword);
        return ResponseEntity.ok("重置密码成功");
    }
}
