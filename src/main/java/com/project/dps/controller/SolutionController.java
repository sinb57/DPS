package com.project.dps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SolutionController {

    @GetMapping({"/solutions/{solutionTitle}"})
    public String getMethod_solution(@PathVariable("solutionTitle") String solutionTitle) {

        if (solutionTitle.equals("SQL-Injection")) {
            return "solution/solution1";
        }
        else if (solutionTitle.equals("XSS")) {
            return "solution/solution2";
        }
        return "/";
    }
}
