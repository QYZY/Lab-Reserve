package com.edu.nefu.labreserve.service;

import com.edu.nefu.labreserve.dox.Lab;
import com.edu.nefu.labreserve.dox.User;
import com.edu.nefu.labreserve.dto.LabDTO;
import com.edu.nefu.labreserve.exception.XException;
import com.edu.nefu.labreserve.repository.LabRepository;
import com.edu.nefu.labreserve.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class LabService {
    private final LabRepository labRepository;
    private final UserRepository userRepository;

    // 添加实验室
    public boolean addLab(LabDTO dto) {
        Lab lab = convert(dto);
        labRepository.save(lab);
        return true;
    }

    public boolean updateLab(Long labId, LabDTO dto) {
        // 获取管理员对象
        User admin = userRepository.findById(dto.getAdminId())
                .orElseThrow(() -> new XException("User not found"));
        // 获取实验室对象
        Lab existinglab = labRepository.findById(labId)
                .orElseThrow(() -> new XException("Lab not found"));

        // 更新实验室信息
        existinglab.setName(dto.getName());
        existinglab.setDescription(dto.getDescription());
        existinglab.setAdmin(admin);

        // 保存实验室信息
        labRepository.save(existinglab);

        return true;
    }


    public boolean deleteLab(Long labId) {
        if (!labRepository.existsById(labId)) {
            return false;
        }
        labRepository.deleteById(labId);
        return true;
    }

    public List<LabDTO> getAllLabs() {
        return labRepository.findAll()
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());

    }

    public List<LabDTO> getAvailableLabs(Integer weekNumber, Integer weekDay, Long periodId) {
        return labRepository.findAvailableLabs(weekNumber, weekDay, periodId)
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public LabDTO convert(Lab lab) {
        return LabDTO.builder()
                .id(lab.getId())
                .name(lab.getName())
                .description(lab.getDescription())
                .adminId(lab.getAdmin().getId())
                .build();
    }

    public Lab convert(LabDTO dto) {
        User admin = userRepository.findById(dto.getAdminId())
                .orElseThrow(() -> new XException("User not found"));
        return Lab.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .admin(admin)
                .build();
    }
}
