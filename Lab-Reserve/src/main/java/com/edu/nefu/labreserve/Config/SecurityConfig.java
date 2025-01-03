package com.edu.nefu.labreserve.Config;

import com.edu.nefu.labreserve.dox.UserRole;
import com.edu.nefu.labreserve.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;
    private final CorsConfigurationSource corsConfigurationSource;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource)) // 应用 CORS 配置

                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/login").permitAll()
                        .requestMatchers("/api/lab/list","/api/course/list","/api/user/list").authenticated() // 所有人都可以查看课程列表
                        .requestMatchers("/api/course/add", "/api/course/{id}").hasAnyRole("SUPER_ADMIN","TEACHER") // 仅超级管理员可以添加或更新课程
                        .requestMatchers("/api/lab/add", "/api/lab/{id}").hasAnyRole("SUPER_ADMIN","LAB_ADMIN") // 仅超级管理员可以添加或更新课程
                        .requestMatchers("/api/reservation/add","/api/reservation/week").hasRole("TEACHER") // 仅教师可以发起预约
                        .requestMatchers("/api/reservation/{id}/**").hasAnyRole("SUPER_ADMIN","LAB_ADMIN","TEACHER") // 仅管理员可以审核预约
                        .requestMatchers("/api/user/**").hasRole("SUPER_ADMIN") // 仅超级管理员可以管理用户
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Swagger 相关请求无需登录
                        .anyRequest().authenticated() // 其他请求需要登录
                )
                .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

