package com.edu.nefu.labreserve.service;

import com.edu.nefu.labreserve.dox.User;
import com.edu.nefu.labreserve.dox.UserRole;
import com.edu.nefu.labreserve.exception.XException;
import com.edu.nefu.labreserve.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User addUser(String username, String password) {
        // 检测重复用户名
        if (userRepository.findByUsername(username).isPresent()) {
            throw new XException("Username already exists");
        }
        // 添加用户
        User user = new User();
        user.setUsername(username);
        // 密码加密存储
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(UserRole.TEACHER);

        return userRepository.save(user);
    }

    public User addUser(String username, String password, UserRole role) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new XException("Username already exists");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        return userRepository.save(user);
    }

    public void delUser(Long id) {
        // 检查用户是否存在
        User user = userRepository.findById(id).orElseThrow(() -> new XException("User not found"));

        // 删除用户
        userRepository.delete(user);
    }

    // 查询所有用户
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 根据用户名查询
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new XException("User not found"));
    }

    // 根据角色查询
    public List<User> getUsersByRole(UserRole role) {
        return userRepository.findByRole(role);
    }

    // 重置密码
    public User resetPassword(Long id, String newPassword) {
        // 查找用户
        User user = userRepository.findById(id).orElseThrow(() -> new XException("User not found"));
        // 加密新密码
        String encryptedPassword = passwordEncoder.encode(newPassword);
        // 更新用户密码
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }

    // 修改密码
    public User changePassword(String username, String oldPassword, String newPassword) {
        // 查找用户
        User user = userRepository.findByUsername(username).orElseThrow(() -> new XException("User not found"));
        // 验证旧密码是否正确
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new XException("Old password is incorrect");
        }
        // 加密新密码
        String encryptedPassword = passwordEncoder.encode(newPassword);
        // 更新用户密码
        user.setPassword(encryptedPassword);
        return userRepository.save(user);
    }

    // 更新角色
    public User updateUser(Long id, String username, UserRole newRole) {
        // 查找用户
        User user = userRepository.findById(id).orElseThrow(() -> new XException("User not found"));
        // 更新角色
        user.setUsername(username);
        user.setRole(newRole);
        return userRepository.save(user);
    }


    public String getUsernameById(Long userId) {
        return userRepository.findUsernameById(userId);
    }


}