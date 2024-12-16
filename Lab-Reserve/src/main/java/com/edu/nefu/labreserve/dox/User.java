package com.edu.nefu.labreserve.dox;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 用户ID

    @Column(nullable = false, unique = true)
    private String username; // 用户名

    @Column(nullable = false)
    private String password; // 密码

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role; //  ROLE_SUPER_ADMIN, ROLE_LAB_ADMIN, ROLE_TEACHER
}