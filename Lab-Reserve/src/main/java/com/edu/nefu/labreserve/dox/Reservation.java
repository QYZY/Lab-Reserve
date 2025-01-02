package com.edu.nefu.labreserve.dox;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "reservation"
)

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 预约ID

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course; // 预约的课程

    @ManyToOne()
    @JoinColumn(name = "lab_id", nullable = false)
    private Lab lab; // 预约的实验室

    @Column(nullable = false)
    private Integer weekNumber; // 周次

    @Column(nullable = false)
    private Integer weekDay; // 星期几（1-7，1是星期一）

    @ManyToOne
    @JoinColumn(name = "period_id", nullable = false)
    private Period period; // 时间段（如 "一二节"）

    @Enumerated(EnumType.STRING)
    private ReservationStatus status; // 预约状态：PENDING, APPROVED, REJECTED

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateTime;


    // Getters and Setters
}
