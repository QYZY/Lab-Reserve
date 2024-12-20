package com.edu.nefu.labreserve.repository;

import com.edu.nefu.labreserve.dox.Lab;
import com.edu.nefu.labreserve.dox.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LabRepositoryTest {

    @Autowired
    private LabRepository labRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateLab() {
        User user = userRepository.findByUsername("admin").get();

        Lab lab = Lab.builder()
                .name("910")
                .admin(user)
                .description("大数据实验室")
                .build();
        labRepository.save(lab);

    }
    @Test
    void testFindByName() {

    }

    @Test
    void testInitLab() {
        Lab[] labs = new Lab[] {
                Lab.builder()
                    .name("903")
                    .admin(userRepository.findById(1L).get())
                    .description("ACM实验室")
                    .build(),
                Lab.builder()
                        .name("910")
                        .admin(userRepository.findById(2L).get())
                        .description("大数据实验室")
                        .build(),
                Lab.builder()
                        .name("901")
                        .admin(userRepository.findById(1L).get())
                        .description("机房")
                        .build(),
                Lab.builder()
                        .name("905")
                        .admin(userRepository.findById(1L).get())
                        .description("蓝桥杯机房")
                        .build(),
        };
        for (Lab lab : labs) {
            labRepository.save(lab);
        }
    }
}