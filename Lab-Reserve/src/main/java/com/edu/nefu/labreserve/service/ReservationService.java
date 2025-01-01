package com.edu.nefu.labreserve.service;

import com.edu.nefu.labreserve.dox.*;
import com.edu.nefu.labreserve.dto.ReservationDTO;
import com.edu.nefu.labreserve.exception.XException;
import com.edu.nefu.labreserve.repository.CourseRepository;
import com.edu.nefu.labreserve.repository.LabRepository;
import com.edu.nefu.labreserve.repository.PeriodRepository;
import com.edu.nefu.labreserve.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CourseRepository courseRepository;
    private final LabRepository labRepository;
    private final PeriodRepository periodRepository;

    public boolean addReservation(ReservationDTO dto) {
        // 检查实验室是否存在
        Lab lab = labRepository.findById(dto.getLabId()).orElse(null);
        Course course = courseRepository.findById(dto.getCourseId()).orElse(null);
        Period period = periodRepository.findById(dto.getPeriodId()).orElse(null);
        if (lab == null || course == null || period == null) {
            throw new XException("failed to create reservation");
        }
        // 验证时间段是否冲突
        boolean isAvailable = reservationRepository.isPeriodAvailable(
                dto.getLabId(),
                dto.getWeekNumber(),
                dto.getWeekDay(),
                dto.getPeriodId()
        );
        if (!isAvailable) {
            throw new XException("There is no available reservation");
        }

        // 创建预约
        Reservation reservation = Reservation.builder()
                .lab(lab)
                .course(course)
                .period(period)
                .weekNumber(dto.getWeekNumber())
                .weekDay(dto.getWeekDay())
                .status(ReservationStatus.PENDING)
                .build();
        reservationRepository.save(reservation);
        return true;
    }

    public boolean cancelReservation(Long reservationId) {
        boolean isExist = reservationRepository.existsById(reservationId);
        if (!isExist) {
            throw new XException("reservation does not exist");
        }
        reservationRepository.deleteById(reservationId);
        return true;
    }


    public boolean updateReservationStatus(Long reservationId, ReservationStatus status) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation == null) {
            throw new XException("reservation does not exist");
        }
        reservation.setStatus(status);
        reservationRepository.save(reservation);
        return true;
    }

    public List<ReservationDTO> getReservationsByAdminId(Long adminId) {
        List<Reservation> reservations = reservationRepository.findAllByAdminId(adminId);
        return reservations.stream()
                .map(this::convert)
                .toList();
    }

    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(this::convert)
                .toList();

    }

    public List<ReservationDTO> getUserReservations(Long userId) {
        List<Reservation> reservations = reservationRepository.findAllByUserId(userId);
        return reservations.stream()
                .map(this::convert)
                .toList();
    }


    public List<ReservationDTO> getAllApprovedReservations() {
        List<Reservation> reservations = reservationRepository.findByStatus(ReservationStatus.APPROVED);
        return reservations.stream()
                .map(this::convert)
                .toList();
    }


    public ReservationDTO convert(Reservation reservation) {
        return ReservationDTO.builder()
                .id(reservation.getId())
                .userId(reservation.getCourse().getTeacher().getId())
                .courseId(reservation.getCourse().getId())
                .labId(reservation.getLab().getId())
                .periodId(reservation.getPeriod().getId())
                .weekNumber(reservation.getWeekNumber())
                .weekDay(reservation.getWeekDay())
                .status(reservation.getStatus().name())
                .build();
    }
}
