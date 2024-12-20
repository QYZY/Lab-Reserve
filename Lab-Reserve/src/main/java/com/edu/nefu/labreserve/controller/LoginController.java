package com.edu.nefu.labreserve.controller;

import com.edu.nefu.labreserve.exception.XException;
import com.edu.nefu.labreserve.service.UserService;
import com.edu.nefu.labreserve.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginRequest, HttpServletResponse response) {
        System.out.println(loginRequest);
        try {
            String username = loginRequest.get("username");
            String password = loginRequest.get("password");
            log.debug(loginRequest.toString());
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // 生成jwt
            String token = jwtUtil.generateToken(username);
            // 设置到响应头中
            response.addHeader("Authorization", "Bearer " + token);

            return ResponseEntity.ok().body("登陆成功");
        } catch (AuthenticationException e) {
            throw new XException("Invalid credentials");
        }

    }
}
