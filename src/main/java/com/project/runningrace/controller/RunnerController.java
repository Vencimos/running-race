package com.project.runningrace.controller;

import com.project.runningrace.entity.Runner;
import com.project.runningrace.service.RunnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RunnerController {

    @Autowired
    private RunnerService runnerService;

    @GetMapping("/getRunners")
    public String getAllRunners(Model model) {
        List<Runner> runners = runnerService.getAllRunners();
        model.addAttribute("runners", runners);
        return "runners";
    }

    @GetMapping("/add")
    public String goToRunnerForm(Model model) {
        Runner runner = new Runner();

        model.addAttribute("runner", runner);
        return "runner_form";
    }

    @PostMapping("/addRunner")
    public String addRunner(@Valid Runner newRunner, BindingResult bindingResult, RedirectAttributes rA) {
        if (bindingResult.hasErrors()) {
            rA.addFlashAttribute("message", "All the fields are required!");
            return "redirect:runner_form";
        }
        rA.addFlashAttribute("message", "New runner successfully added");
        runnerService.addRunner(newRunner);
        return "redirect:/getRunners";
    }
}