package com.edu.nefu.labreserve.controller;

import com.edu.nefu.labreserve.dto.LabDTO;
import com.edu.nefu.labreserve.service.LabService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lab")
@RequiredArgsConstructor
public class LabController {
    private final LabService labService;

    /**
     * 添加实验室
     *
     * @param labDTO 添加的实验室信息
     * @return 添加结果
     */
    @PostMapping("/add")
    public ResponseEntity<String> addLab(@RequestBody LabDTO labDTO) {
        try {
            labService.addLab(labDTO);
            return ResponseEntity.ok().body("添加成功");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("输入数据无效");
        }
    }

    /**
     * 更新实验室
     *
     * @param id     要更新的实验室id
     * @param labDTO 要更新的实验室信息
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateLab(@PathVariable Long id, @RequestBody LabDTO labDTO) {
        boolean isUpdated = labService.updateLab(id, labDTO);

        if (isUpdated) {
            return ResponseEntity.ok().body("更新成功");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("实验室不存在");

    }

    /**
     * 删除实验室
     *
     * @param id 要删除的实验室id
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLab(@PathVariable Long id) {
        boolean isDeleted = labService.deleteLab(id);
        if (isDeleted) {
            return ResponseEntity.ok("删除成功");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("实验室不存在");
    }

    /**
     * 列出所有实验室
     *
     * @return 所有实验室列表
     */
    @GetMapping("/list")
    public ResponseEntity<List<LabDTO>> getAllLabs() {
        return ResponseEntity.ok(labService.getAllLabs());
    }

    /**
     * 列出指定时间内可用的实验室
     * @param weekNumber 周数
     * @param weekDay 星期几
     * @param periodId  时间段id
     * @return
     */
    // 获取指定周数、天数和时间段内可用的实验室
    @GetMapping("/available")
    public List<LabDTO> getAvailableLabs(@RequestParam Integer weekNumber,
                                         @RequestParam Integer weekDay,
                                         @RequestParam Long periodId) {
        return labService.getAvailableLabs(weekNumber, weekDay, periodId);
    }

    // 获取指定管理员管理的实验室
    @GetMapping("/admin/{adminId}")
    public List<LabDTO> getLabsByAdminId(@PathVariable Long adminId) {
        return labService.getLabsByAdminId(adminId);
    }
}
