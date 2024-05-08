package com.project.runningrace.controller;

import com.project.runningrace.RaceNotFoundException;
import com.project.runningrace.entity.Race;
import com.project.runningrace.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RaceController {

    @Autowired
    RaceService service;

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
}
