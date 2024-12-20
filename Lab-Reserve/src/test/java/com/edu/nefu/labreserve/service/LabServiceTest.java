package com.edu.nefu.labreserve.service;

import com.edu.nefu.labreserve.dto.LabDTO;
import com.edu.nefu.labreserve.repository.LabRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class LabServiceTest {
    @Autowired
    private LabService labService;
    @Autowired
    private LabRepository labRepository;

    @Test
    void testAddLab() {
        LabDTO dto = LabDTO.builder()
                .name("907")
                .description("人工智能实验室")
                .adminId(1L)
                .build();
        labService.addLab(dto);
    }

    @Test
    void testUpdateLab() {
        LabDTO dto = LabDTO.builder()
                .name("901")
                .description("大唐杯机房")
                .adminId(1L)
                .build();
        labService.updateLab(3L, dto);

    }

    @Test
    void testDeleteLab() {
        Long labId = labRepository.findById(1L).get().getId();
        labService.deleteLab(labId);
    }

    @Test
    void testGetAllLabs() {
        List<LabDTO> labs = labService.getAllLabs();
        for (LabDTO lab : labs) {
            log.debug(lab.toString());
        }
    }
}