package com.edu.nefu.labreserve.controller;

import com.edu.nefu.labreserve.dox.User;
import com.edu.nefu.labreserve.dox.UserRole;
import com.edu.nefu.labreserve.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
