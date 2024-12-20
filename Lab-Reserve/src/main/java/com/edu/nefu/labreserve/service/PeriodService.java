package com.edu.nefu.labreserve.service;

import com.edu.nefu.labreserve.dox.Period;
import com.edu.nefu.labreserve.exception.XException;
import com.edu.nefu.labreserve.repository.PeriodRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PeriodService {
    private final PeriodRepository periodRepository;

    // 添加时间段
    public boolean addPeriod (Period period) {
        periodRepository.save(period);
        return true;
    }

    // 更新时间段
    public boolean updatePeriod (Integer periodId , Period period) {
        // 获取对象
        Period existingPeriod = periodRepository.findById(periodId)
                .orElseThrow( () -> new XException("Period not found"));
        existingPeriod.setName(period.getName());
        existingPeriod.setStartTime(period.getStartTime());
        existingPeriod.setEndTime(period.getEndTime());

        periodRepository.save(existingPeriod);

        return true;
    }

    // 删除时间段
    public boolean deletePeriod (Integer periodId) {
        if (!periodRepository.existsById(periodId)) {
            return false;
        }
        periodRepository.deleteById(periodId); // 删除课程
        return true;
    }

    // 获取全部时间段

    public List<Period> getAllPeriods() {
        return periodRepository.findAll();
    }

}
