package com.edu.nefu.labreserve.controller;

import com.edu.nefu.labreserve.dox.Period;
import com.edu.nefu.labreserve.service.PeriodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/period")
@RequiredArgsConstructor
public class PeriodController {
    private final PeriodService periodService;

    @GetMapping("/add")
    public ResponseEntity<String> addPeriod(Period period) {
        try {
            periodService.addPeriod(period);
            return ResponseEntity.status(HttpStatus.CREATED).body("时间段添加成功");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("输入数据无效");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePeriod(@PathVariable Integer id, @RequestBody Period period) {

        boolean isUpdated = periodService.updatePeriod(id, period);

        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body("时间段更新成功");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("时间段不存在");
    }
}
