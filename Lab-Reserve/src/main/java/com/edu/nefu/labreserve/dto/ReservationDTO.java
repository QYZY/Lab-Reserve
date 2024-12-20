package com.edu.nefu.labreserve.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationDTO {
    private Long id;
    private Long userId;
    private Long courseId;
    private Long labId;
    private Integer periodId;
    private Integer weekNumber;
    private Integer weekDay;
}
