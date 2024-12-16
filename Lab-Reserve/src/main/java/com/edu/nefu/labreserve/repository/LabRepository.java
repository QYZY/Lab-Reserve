package com.edu.nefu.labreserve.repository;

import com.edu.nefu.labreserve.dox.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LabRepository extends JpaRepository<Lab, Long> {
    Optional<Lab> findByName(String name);
}