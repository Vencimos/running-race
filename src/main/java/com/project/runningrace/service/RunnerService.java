package com.project.runningrace.service;

import com.project.runningrace.entity.Runner;
import com.project.runningrace.repository.RunnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunnerService {

    @Autowired
    private RunnerRepository runnerRepository;

    public List<Runner> getAllRunners() {
        return runnerRepository.findAll();
    }

    public void addRunner(Runner runner) {
        runnerRepository.save(runner);
    }
}
