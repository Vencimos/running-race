package com.project.runningrace.repository;

import com.project.runningrace.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Integer> {
    List<Result> findByRaceIdOrderByTimeInMinutesAsc(Integer raceId);

    List<Result> findByRaceId(Integer raceId);
}

