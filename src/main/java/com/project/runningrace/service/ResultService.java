package com.project.runningrace.service;

import com.project.runningrace.ResultNotFoundException;
import com.project.runningrace.entity.Result;
import com.project.runningrace.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    public Result addResult(Result result) {
        return resultRepository.save(result);
    }

    public List<Result> getRaceRunners(Integer raceId) throws ResultNotFoundException {
            return resultRepository.findByRaceIdOrderByTimeInMinutesAsc(raceId);
    }
}
