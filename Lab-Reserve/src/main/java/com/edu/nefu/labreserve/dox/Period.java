package com.edu.nefu.labreserve.dox;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "period")
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 时间段ID

    @Column(nullable = false)
    private String name; // 时间段名称，例如 "一二节"

    @Column(nullable = false)
    private LocalTime startTime; // 开始时间

    @Column(nullable = false)
    private LocalTime endTime; // 结束时间

}
