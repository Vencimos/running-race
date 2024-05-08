package com.project.runningrace.initializer;

import com.project.runningrace.entity.Race;
import com.project.runningrace.entity.Result;
import com.project.runningrace.entity.Runner;
import com.project.runningrace.service.RaceService;
import com.project.runningrace.service.ResultService;
import com.project.runningrace.service.RunnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RunnerService runnerService;

    @Autowired
    private RaceService raceService;

    @Autowired
    private ResultService resultService;

    @Override
    public void run(String... args) {
        List<Runner> runners = Arrays.asList(
                new Runner("John", 25, "Male"),
                new Runner("Alice", 30, "Female"),
                new Runner("Bob", 28, "Male"),
                new Runner("Emma", 22, "Female"),
                new Runner("Joe", 45, "Male"),
                new Runner("Susan", 37, "Female")
        );
        runners.forEach(runnerService::addRunner);

        List<Race> races = Arrays.asList(
                new Race("Race 1", 10.0),
                new Race("Race 2", 5.4),
                new Race("Race 3", 7.3),
                new Race("Race 4", 8.5)
        );
        races.forEach(raceService::addRace);

        generateResults(runners, races);
    }

    private void generateResults(List<Runner> runners, List<Race> races) {
        Random random = new Random();
        for (Race race : races) {
            for (Runner runner : runners) {
                if (random.nextBoolean()) {
                    int time = random.nextInt(100) + 1;
                    Result result = new Result(runner, race, time);
                    resultService.addResult(result);
                }
            }
        }
    }
}