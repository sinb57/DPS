package com.project.dps.controller;

import com.project.dps.controller.dto.ScenarioDto;
import com.project.dps.controller.dto.StageDto;
import com.project.dps.service.ScenarioService;
import com.project.dps.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/scenarios")
public class ScenarioController {

    private final ScenarioService scenarioService;
    private final StageService stageService;

    @GetMapping("/upload")
    public String getMethod_upload(Model model) {
        model.addAttribute("scenarioDto", ScenarioDto.builder().build());
        return "scenario/upload";
    }

    @PostMapping("/upload")
    public String postMethod_upload(@Valid ScenarioDto scenarioDto, BindingResult result) {

        if (result.hasErrors()) {
            System.out.printf("scenario upload error");
            return "scenario/upload";
        }

        return "scenario/upload-success";
    }

    @GetMapping("/list")
    public String scenarioList(@PageableDefault Pageable pageable, Model model) {

        return "scenario/scenarioList";
    }


    @GetMapping("/{subTitle}")
    public String getMethod_detail(@PathVariable String subTitle, Model model) {
        Optional<ScenarioDto> scenarioDto = scenarioService.findBySubTitle(subTitle);
        model.addAttribute("scenarioDto", scenarioDto.get());
        return "scenario/scenarioDetail";
    }


    @GetMapping({"/{scenarioSubTitle}/stages/{stageNo}"})
    public String getMethod_detail(@PathVariable("scenarioSubTitle") String subTitle,
                                   @PathVariable("stageNo") Long stageNo,
                                   Model model) {
        Optional<ScenarioDto> scenarioDto = scenarioService.findBySubTitle(subTitle);
        Optional<StageDto> stageDto = stageService.findBySubTitle(scenarioDto.get().getId(), stageNo);
        model.addAttribute("stageDto", stageDto.get());
        model.addAttribute("stageNo", stageNo);

        return "scenario/stageDetail";
    }

    @PostMapping({"/{scenarioSubTitle}/stages/{stageNo}/solve"})
    public String postMethod_solve(@PathVariable("scenarioSubTitle") String subTitle,
                                   @PathVariable("stageNo") int stageNo,
                                   Model model) {
        if (stageNo == 1) {
            model.addAttribute("isClear", true);
            return "scenario/stageResult1";
        }
        else if (stageNo == 2) {
            model.addAttribute("isClear", false);
            return "scenario/stageResult2";
        }
        return "/";
    }

    @GetMapping({"/{scenarioSubTitle}/stages/{stageNo}/solution/{solutionTitle}"})
    public String getMethod_solution(@PathVariable("solutionTitle") String solutionTitle) {

        if (solutionTitle.equals("SQL-injection")) {
            return "scenario/solutionDetail1";
        }
        else if (solutionTitle.equals("XSS")) {
            return "scenario/solutionDetail2";
        }
        return "/";
    }

    @GetMapping("/{scenarioSubTitle}/report")
    public String getMethod_report(@PathVariable("scenarioSubTitle") String subTitle) {

        return "/scenario/scenarioReport";
    }

}
