package com.edu.nefu.labreserve.repository;

import com.edu.nefu.labreserve.dox.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Integer> {

}
