package com.project.dps.controller;

import com.project.dps.dto.scenario.ScenarioDto;
import com.project.dps.service.ScenarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class indexController {

    private final ScenarioService scenarioService;

    @RequestMapping("/")
    public String scenarioList(Model model) {

        List<ScenarioDto> scenarioDtoList = scenarioService.getScenarioList();

        model.addAttribute("scenarioList", scenarioDtoList);

        return "scenario/list";
    }

}
