package com.edu.nefu.labreserve.controller;


import com.edu.nefu.labreserve.dox.ReservationStatus;
import com.edu.nefu.labreserve.dto.ReservationDTO;
import com.edu.nefu.labreserve.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation/")
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("add")
    public ResponseEntity<String> addReservation(@RequestBody ReservationDTO reservationDTO) {
        reservationService.addReservation(reservationDTO);
        return ResponseEntity.ok("添加成功");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.ok("取消成功");
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<String> approveReservation(@PathVariable Long id) {
        reservationService.updateReservationStatus(id, ReservationStatus.APPROVED);
        return ResponseEntity.ok("审核成功，预约已通过");
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<String> rejectReservation(@PathVariable Long id) {
        reservationService.updateReservationStatus(id, ReservationStatus.REJECTED);
        return ResponseEntity.ok("审核成功，预约已拒绝");
    }


    @GetMapping("list")
    public ResponseEntity<List<ReservationDTO>> listReservation() {
        List<ReservationDTO> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("list/approve")

    public ResponseEntity<List<ReservationDTO>> listApproveReservation() {
        List<ReservationDTO> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }


}
