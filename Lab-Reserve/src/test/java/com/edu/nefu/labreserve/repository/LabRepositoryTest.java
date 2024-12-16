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
                .adminId(user)
                .description("大数据实验室")
                .build();
        labRepository.save(lab);

    }
    @Test
    void testFindByName() {

    }
}