package com.edu.nefu.labreserve.controller;

import com.edu.nefu.labreserve.dox.User;
import com.edu.nefu.labreserve.exception.XException;
import com.edu.nefu.labreserve.repository.UserRepository;
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
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginRequest, HttpServletResponse response) {


        String username = loginRequest.get("username");
        String password = loginRequest.get("password");
        log.debug(loginRequest.toString());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            // 生成jwt
            String token = jwtUtil.generateToken(username);
            // 设置到响应头中
            response.addHeader("Authorization", "Bearer " + token);
            Map<String, Object> map = new HashMap<>();

            map.put("message", "Login success");
            map.put("token", token);
            map.put("user", user.get());

            return ResponseEntity.ok().body(map);
        } else {
            throw new XException("User not found");
        }
    }
}
