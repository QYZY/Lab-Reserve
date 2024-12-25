package com.edu.nefu.labreserve.controller;

import com.edu.nefu.labreserve.dox.User;
import com.edu.nefu.labreserve.exception.XException;
import com.edu.nefu.labreserve.repository.UserRepository;
import com.edu.nefu.labreserve.service.UserService;
import com.edu.nefu.labreserve.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
        System.out.println(loginRequest);
        try {
            String username = loginRequest.get("username");
            String password = loginRequest.get("password");
            log.debug(loginRequest.toString());

            // 使用 AuthenticationManager 进行身份验证
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 生成 JWT Token
            String token = jwtUtil.generateToken(username);

            Optional<User> user = userRepository.findByUsername(username);
            if (user.isPresent()) {
                // 构建 JSON 响应
                Map<String, Object> responseBody = new HashMap<>();
                responseBody.put("message", "登录成功");
                responseBody.put("token", token);
                responseBody.put("user", user.get());

                return ResponseEntity.ok(responseBody);
            }
            else {
                throw new XException("User Not Found");
            }

        } catch (AuthenticationException e) {
            // 返回错误响应
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "用户名或密码错误");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }


}
