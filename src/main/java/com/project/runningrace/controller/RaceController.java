package com.project.runningrace.controller;

import com.project.runningrace.exception.RaceNotFoundException;
import com.project.runningrace.entity.Race;
import com.project.runningrace.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RaceController {

    @Autowired
    RaceService service;

    @GetMapping("/add")
    public String goToRunnerForm(Model model) {
        Race race = new Race();

        model.addAttribute("race", race);
        return "race_form";
    }

    @PostMapping("/addRace")
    public String addRace(@Valid Race newRace, BindingResult bindingResult, RedirectAttributes rA) {
        if (bindingResult.hasErrors()) {
            rA.addFlashAttribute("message", "All the fields are required!");
            return "redirect:race_form";
        }
        service.addRace(newRace);
        rA.addFlashAttribute("message", "New race successfully added");
        return "redirect:/allRaces";

    }

    @PostMapping("/updateRace")
    public String updateRace(@RequestBody Race raceToUpdate, RedirectAttributes rA) {
        try {
            service.updateRace(raceToUpdate);
            return "redirect:/";
        } catch (RaceNotFoundException e) {
            rA.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }
    }

    @GetMapping("/allRaces")
    public String listAllRaces(Model model) {
        List<Race> raceList = service.getAllRaces();
        model.addAttribute("raceList", raceList);
        return "races";
    }
}
