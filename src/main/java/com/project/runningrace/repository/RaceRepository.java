package com.project.runningrace.repository;

import com.project.runningrace.entity.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RaceRepository extends JpaRepository<Race, Integer> {

    @Modifying
    @Transactional
    @Query("UPDATE Race r SET r.name = :name, r.distance = :distance WHERE r.id = :id")
    void updateRaceDetails(Integer id, String name, Double distance);
}
