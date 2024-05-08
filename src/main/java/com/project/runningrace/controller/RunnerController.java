package com.project.runningrace.controller;

import com.project.runningrace.entity.Runner;
import com.project.runningrace.service.RunnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RunnerController {

    @Autowired
    private RunnerService runnerService;

    @GetMapping("/getRunners")
    public ResponseEntity<List<Runner>> getAllRunners() {
        List<Runner> runners = runnerService.getAllRunners();
        return ResponseEntity.ok().body(runners);
    }

    @PostMapping("/addRunner")
    public ResponseEntity<String> addRunner(@Valid @RequestBody Runner newRunner, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("One or more attributes are missing");
        }
        runnerService.addRunner(newRunner);
        return ResponseEntity.ok("Runner added successfully");
    }
}