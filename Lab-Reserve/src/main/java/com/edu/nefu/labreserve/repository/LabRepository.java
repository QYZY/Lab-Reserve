package com.edu.nefu.labreserve.repository;

import com.edu.nefu.labreserve.dox.Lab;
import com.edu.nefu.labreserve.dto.LabDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface LabRepository extends JpaRepository<Lab, Long> {
    Optional<Lab> findByName(String name);

    // 根据指定的周数、星期几和时间段查询可用的实验室
    @Query("SELECT l FROM Lab l WHERE l.id NOT IN (" +
            "SELECT r.lab.id FROM Reservation r " +
            "WHERE r.weekNumber = :weekNumber AND r.weekDay = :weekDay AND r.period.id = :periodId " +
            "AND r.status IN ('APPENDING', 'PENDING'))")
    List<Lab> findAvailableLabs(@Param("weekNumber") Integer weekNumber,
                                @Param("weekDay") Integer weekDay,
                                @Param("periodId") Long periodId);

    List<Lab> findByAdminId(Long adminId);
}