package com.project.runningrace.service;

import com.project.runningrace.exception.ResultNotFoundException;
import com.project.runningrace.entity.Result;
import com.project.runningrace.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public double calculateAverageResult(Integer id) throws ResultNotFoundException {
        List<Result> resultList = resultRepository.findByRaceId(id);
        if (resultList.isEmpty()) {
            throw new ResultNotFoundException("Could not find any result with race id: " + id);
        }
        int totalMinutes = resultList.stream()
                .mapToInt(Result::getTimeInMinutes)
                .sum();

        return (double) totalMinutes / resultList.size();
    }
}
