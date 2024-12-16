package com.edu.nefu.labreserve.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {

    @GetMapping("/public")
    public String publicApi() {
        return "This is a public API";
    }

    @GetMapping("/user")
    public String userApi() {
        return "This is a user-protected API";
    }

    @GetMapping("/admin")
    public String adminApi() {
        return "This is an admin-protected API";
    }
}
