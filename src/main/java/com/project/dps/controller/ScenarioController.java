package com.project.dps.controller;

import com.project.dps.dto.member.MemberDto;
import com.project.dps.dto.scenario.ScenarioDto;
import com.project.dps.dto.scenario.stage.StageDto;
import com.project.dps.dto.log.StageLogDto;
import com.project.dps.service.MemberService;
import com.project.dps.service.PocService;
import com.project.dps.service.ScenarioService;
import com.project.dps.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/scenarios")
public class ScenarioController {

    private final MemberService memberService;
    private final ScenarioService scenarioService;
    private final StageService stageService;
    private final PocService pocService;

//    @GetMapping("/upload")
//    public String getMethod_upload(Model model) {
//        model.addAttribute("scenarioDto", ScenarioDto.builder().build());
//        return "scenario/upload";
//    }
//
//    @PostMapping("/upload")
//    public String postMethod_upload(@Valid ScenarioDto scenarioDto, BindingResult result) {
//
//        if (result.hasErrors()) {
//            System.out.printf("scenario upload error");
//            return "scenario/upload";
//        }
//
//        return "scenario/upload-success";
//    }
//
//    @GetMapping("/list")
//    public String scenarioList(@PageableDefault Pageable pageable, Model model) {
//
//        return "scenario/scenarioList";
//    }

    @GetMapping("/make")
    public String getMethod_make() {
        return "scenario/scenarioEdit";
    }


    @GetMapping("/{subTitle}")
    public String getMethod_detail(@PathVariable String subTitle, Model model) {
        ScenarioDto scenarioDto = scenarioService.findBySubTitle(subTitle);
        model.addAttribute("scenario", scenarioDto);
        return "scenario/scenarioDetail";
    }

    @GetMapping({"/{scenarioSubTitle}/stages/{stageNo}"})
    public String getMethod_detail(@PathVariable("scenarioSubTitle") String subTitle,
                                   @PathVariable("stageNo") int stageNo,
                                   Model model) {

        Long scenarioId = scenarioService.getIdBySubTitle(subTitle);
        Long stageId = stageService.getIdByScenarioIdAndStageNo(scenarioId, Long.valueOf(stageNo));

        ScenarioDto scenarioDto = scenarioService.findById(scenarioId);
        model.addAttribute("scenario", scenarioDto);

        StageDto stageDto = stageService.findById(stageId);
        model.addAttribute("stage", stageDto);

        return "scenario/stageDetail";
    }

    @PostMapping({"/{scenarioSubTitle}/stages/{stageNo}/solve"})
    public String postMethod_solve(@PathVariable("scenarioSubTitle") String subTitle,
                                   @PathVariable("stageNo") int stageNo,
                                   @RequestParam("targetUrl") String targetUrl,
                                   @RequestParam("targetExtension") String targetExtension,
                                   Model model) {

        Long memberId = 1L;
        Long scenarioId = scenarioService.getIdBySubTitle(subTitle);
        Long stageId = stageService.getIdByScenarioIdAndStageNo(scenarioId, Long.valueOf(stageNo));

        MemberDto memberDto = memberService.findById(memberId); // 세션에서 member data 가져올 예정
        ScenarioDto scenarioDto = scenarioService.findById(scenarioId);
        model.addAttribute("scenario", scenarioDto);

        StageDto stageDto = stageService.findById(stageId);
        model.addAttribute("stage", stageDto);

        StageLogDto stageLogDto = pocService.evaluate(memberId, stageId, targetUrl, targetExtension);
        model.addAttribute("stageLog", stageLogDto);

        return "scenario/stageResult2";
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
