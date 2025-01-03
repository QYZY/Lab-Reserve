package com.edu.nefu.labreserve.repository;

import com.edu.nefu.labreserve.dox.Reservation;
import com.edu.nefu.labreserve.dox.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByStatus(ReservationStatus status);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN FALSE ELSE TRUE END " +
            "FROM Reservation r " +
            "WHERE r.lab.id = :labId " +
            "AND r.weekNumber = :weekNumber " +
            "AND r.weekDay = :weekDay " +
            "AND r.period.id = :periodId " +
            "AND r.status IN ('PENDING', 'APPROVED')")
    boolean isPeriodAvailable(@Param("labId") Long labId,
                              @Param("weekNumber") Integer weekNumber,
                              @Param("weekDay") Integer weekDay,
                              @Param("periodId") Integer periodId);


    // 根据用户ID查询预约记录
    @Query("SELECT r FROM Reservation r WHERE r.course.teacher.id = :userId")
    List<Reservation> findAllByUserId(Long userId);

    // 根据管理员ID查询预约记录
    @Query("SELECT r FROM Reservation r WHERE r.lab.admin.id = :adminId AND r.status = 'PENDING'")
    List<Reservation> findAllByAdminId(Long adminId);



    List<Reservation> findByLabIdAndWeekNumberAndStatusIn(Long labId, Integer weekNumber, List<ReservationStatus> approved);

    @Query("SELECT r FROM Reservation r WHERE (r.course.teacher.id = :userId) AND r.status IN ('PENDING', 'APPROVED')")
    List<Reservation> findByUserIdAndStatus(Long userId);}
