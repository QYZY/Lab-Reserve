package com.edu.nefu.labreserve.repository;

import com.edu.nefu.labreserve.dox.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
