package com.project.dps.controller;

import com.project.dps.domain.log.StageLog;
import com.project.dps.domain.scenario.stage.poc.ValidResultEnum;
import com.project.dps.domain.scenario.stage.poc.ValidTypeEnum;
import com.project.dps.dto.member.MemberDto;
import com.project.dps.dto.scenario.ScenarioDto;
import com.project.dps.dto.scenario.stage.StageDto;
import com.project.dps.dto.log.StageLogDto;
import com.project.dps.repository.StageLogRepository;
import com.project.dps.service.MemberService;
import com.project.dps.service.PocService;
import com.project.dps.service.ScenarioService;
import com.project.dps.service.StageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/scenarios")
public class ScenarioController {

    private final MemberService memberService;
    private final ScenarioService scenarioService;
    private final StageService stageService;
    private final PocService pocService;


    @GetMapping("/{subTitle}")
    public String getMethod_detail(@PathVariable String subTitle, Model model) {
        ScenarioDto scenarioDto = scenarioService.findBySubTitle(subTitle);
        model.addAttribute("scenario", scenarioDto);
        return "scenario/scenario";
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

        return "stage/stage";
    }

    @PostMapping({"/{scenarioSubTitle}/stages/{stageNo}/solve"})
    public String postMethod_solve(@PathVariable("scenarioSubTitle") String subTitle,
                                   @PathVariable("stageNo") int stageNo,
                                   @RequestParam("targetUrl") String targetUrl,
                                   @RequestParam("targetExtension") String targetExtension,
                                   Principal principal, Model model) {

        if (targetUrl.charAt(targetUrl.length()-1) != '/') {
            targetUrl += "/";
        }

        if (targetExtension.charAt(0) != '.') {
            targetExtension = "." + targetExtension;
        }

        Long scenarioId = scenarioService.getIdBySubTitle(subTitle);
        Long stageId = stageService.getIdByScenarioIdAndStageNo(scenarioId, Long.valueOf(stageNo));

        MemberDto memberDto = memberService.findByEmail(principal.getName());
        ScenarioDto scenarioDto = scenarioService.findById(scenarioId);
        model.addAttribute("scenario", scenarioDto);

        StageDto stageDto = stageService.findById(stageId);
        model.addAttribute("stage", stageDto);

        StageLogDto stageLogDto = pocService.evaluate(principal.getName(), stageId, targetUrl, targetExtension);
        model.addAttribute("stageLog", stageLogDto);

        model.addAttribute("validResultEnum", ValidResultEnum.values());
        model.addAttribute("validTypeEnum", ValidTypeEnum.values());

        return "stage/result";
    }


    @GetMapping("/{scenarioSubTitle}/report")
    public String getMethod_report(@PathVariable("scenarioSubTitle") String subTitle, Principal principal, Model model) {

        Long scenarioId = scenarioService.getIdBySubTitle(subTitle);

        MemberDto memberDto = memberService.findByEmail(principal.getName());
        model.addAttribute("member", memberDto);

        ScenarioDto scenarioDto = scenarioService.findById(scenarioId);
        model.addAttribute("scenario", scenarioDto);

        HashMap<String, HashMap<String, Integer>> statisticsBySecureCheck = stageService.getStatisticsBySecureCheck(principal.getName(), subTitle);
        model.addAttribute("statistics", statisticsBySecureCheck);

        HashMap<String, Integer> secureCheckCountMap = stageService.getSecureCheckCount(statisticsBySecureCheck);
        model.addAttribute("secureCheckCountMap", secureCheckCountMap);

        model.addAttribute("validResultEnum", ValidResultEnum.values());
        model.addAttribute("validTypeEnum", ValidTypeEnum.values());

        return "/scenario/report";
    }

    @GetMapping("/{scenarioSubTitle}/report/detail/{stageLogId}")
    public String getMethod_reportDetail(@PathVariable("scenarioSubTitle") String subTitle,
                                         @PathVariable("stageLogId") Long stageLogId,
                                         Model model) {

        Long memberId = 1L;
        Long scenarioId = scenarioService.getIdBySubTitle(subTitle);

        ScenarioDto scenarioDto = scenarioService.findById(scenarioId);
        model.addAttribute("scenario", scenarioDto);

        StageLogDto stageLogDto = stageService.findByStageId(stageLogId);
        model.addAttribute("stageLog", stageLogDto);

        model.addAttribute("validResultEnum", ValidResultEnum.values());
        model.addAttribute("validTypeEnum", ValidTypeEnum.values());

        return "/stage/report";
    }

}
