package com.project.runningrace.controller;

import com.project.runningrace.exception.ResultNotFoundException;
import com.project.runningrace.entity.Result;
import com.project.runningrace.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ResultController {

    @Autowired
    private ResultService service;

    @GetMapping("/getRaceRunners/{id}")
    public String getRaceRunnersWithResults(@PathVariable("id") Integer id, Model model, RedirectAttributes rA) {
        try {
            List<Result> raceRunners = service.getRaceRunners(id);
            if (raceRunners.isEmpty()) {
                throw new ResultNotFoundException("Could not find any race result with race id: " + id);
            }
            model.addAttribute("raceRunners", raceRunners);
            return "race_results";
        } catch (ResultNotFoundException e) {
            rA.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }
    }

    @PostMapping("/addResult")
    public String addResult(@RequestBody Result result) {
        service.addResult(result);
        return "redirect:/getRaceRunners/" + result.getRace().getId();
    }

    @GetMapping("/getAverageTime/{id}")
    public void getAverageTimeResultOfRace(@PathVariable Integer id, Model model) {
        try {
            double average = service.calculateAverageResult(id);
            model.addAttribute("average", average);
        } catch (ResultNotFoundException e) {
            model.addAttribute("message", "Could not calculate average time for the race!");
        }
    }
}
