package com.edu.nefu.labreserve.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    private static final String SECRET_KEY = "Fn75jM0X8pOcJ75bUII3sy34pPG3q0iHQQ2pQQ5jJo22UJRLNfNOp1RVTsuPLVfO";
    private static final Long EXPIRE_TIME = 3600000L;

    public static String generateToken(String username,String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();
    }

    // 解析 JWT
    public static Claims parseToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 验证 JWT 是否过期
    public static boolean isTokenExpired(String token) {
        try {
            Claims claims = parseToken(token);
            return claims.getExpiration().before(new Date());
        } catch (JwtException e) {
            return true; // 无效 token
        }
    }

    public static List<GrantedAuthority> parseRolesFromToken(String token) {
        // 从 JWT Token 中解析 Claims
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY) // 使用签名密钥解析
                .parseClaimsJws(token)
                .getBody();

        // 获取 roles 字段（假设是存储角色的字段）
        String role = claims.get("role", String.class);


        // 将角色转换为 Spring Security 的 GrantedAuthority，并加上 ROLE_ 前缀
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }
}
