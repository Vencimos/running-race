package com.project.runningrace.repository;

import com.project.runningrace.entity.Runner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunnerRepository extends JpaRepository<Runner, Integer> {
}
