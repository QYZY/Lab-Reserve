package com.edu.nefu.labreserve.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LabDTO {
    private Long id;
    private String name;
    private String description; // 实验室描述
    private Long adminId;
    private String adminName;
}
