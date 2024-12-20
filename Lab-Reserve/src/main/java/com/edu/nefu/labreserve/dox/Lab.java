package com.edu.nefu.labreserve.dox;

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
@Table(name = "lab")
public class Lab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 实验室ID

    @Column(nullable = false, unique = true)
    private String name; // 实验室名称

    private String description; // 实验室描述

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin;
}