package com.project.runningrace.controller;

import com.project.runningrace.entity.Race;
import com.project.runningrace.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    RaceService raceService;

    @GetMapping("")
    public String viewHomePage(Model model) {
        List<Race> allRaces = raceService.getAllRaces();
        model.addAttribute("races", allRaces);
        return "index1";
    }
}
