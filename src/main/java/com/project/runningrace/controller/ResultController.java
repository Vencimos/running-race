package com.project.runningrace.controller;

import com.project.runningrace.ResultNotFoundException;
import com.project.runningrace.entity.Result;
import com.project.runningrace.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
