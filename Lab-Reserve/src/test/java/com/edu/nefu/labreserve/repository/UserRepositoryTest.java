package com.edu.nefu.labreserve.repository;

import com.edu.nefu.labreserve.dox.User;
import com.edu.nefu.labreserve.dox.UserRole;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateUser() {
        // 创建或更新用户
        User user = User.builder()
                .username("admin")
                .password("admin123")
                .role(UserRole.LAB_ADMIN)
                .build();
        userRepository.save(user);  // 保存用户，若已有则更新
    }

    @Test
    void testFindById() {
        Optional<User> userOptional = userRepository.findById(Long.valueOf("1"));
        if (userOptional.isPresent()) {
            User user = userOptional.get(); // 获取 User 对象
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
        } else {
            System.out.println("User not found");
        }
    }

    @Test
    void testFindByUsername() {
        Optional<User> userOptional = userRepository.findByUsername("john_doe");
        if (userOptional.isPresent()) {
            User user = userOptional.get(); // 获取 User 对象
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
        } else {
            System.out.println("User not found");
        }
    }

    @Test
    @Transactional
    public void testDeleteUser() {

        userRepository.deleteByUsername("admin");
        userRepository.findAll().forEach(System.out::println);
        userRepository.flush();  // 强制同步到数据库

//        Optional<User> user = userRepository.findByUsername("john_doe");
//        assertFalse(user.isPresent());
    }
}