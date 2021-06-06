package com.project.dps.controller;

import com.project.dps.dto.scenario.stage.poc.SolutionDto;
import com.project.dps.service.SolutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/solutions")
public class SolutionController {

    private final SolutionService solutionService;

    @GetMapping({"/{solutionTitle}"})
    public String getMethod_solution(@PathVariable("solutionTitle") String solutionTitle, Model model) {

        solutionTitle = solutionTitle.replace('-', ' ');

        SolutionDto solutionDto = solutionService.findBySolutionTitle(solutionTitle);
        model.addAttribute("solution", solutionDto);

        return "/solution/solution";
    }
}
