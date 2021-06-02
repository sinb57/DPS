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
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/scenarios")
public class ScenarioController {

    private final MemberService memberService;
    private final ScenarioService scenarioService;
    private final StageService stageService;
    private final PocService pocService;

    private final StageLogRepository stageLogRepository;


    @RequestMapping("/*")
    public String scenarioList(@PageableDefault Pageable pageable, Model model) {

        return "scenario/list";
    }

    @GetMapping("/edit")
    public String getMethod_make() {
        return "scenario/scenarioEdit";
    }


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
                                   Model model) {

        if (targetUrl.charAt(targetUrl.length()-1) != '/') {
            targetUrl += "/";
        }

        if (targetExtension.charAt(0) != '.') {
            targetExtension = "." + targetExtension;
        }

        Long memberId = 1L;
        Long scenarioId = scenarioService.getIdBySubTitle(subTitle);
        Long stageId = stageService.getIdByScenarioIdAndStageNo(scenarioId, Long.valueOf(stageNo));

        MemberDto memberDto = memberService.findById(memberId); // 세션에서 member data 가져올 예정
        ScenarioDto scenarioDto = scenarioService.findById(scenarioId);
        model.addAttribute("scenario", scenarioDto);

        StageDto stageDto = stageService.findById(stageId);
        model.addAttribute("stage", stageDto);

        StageLogDto stageLogDto = pocService.evaluate(memberId, stageId, targetUrl, targetExtension);
        //StageLogDto stageLogDto = StageLogDto.toDto(stageLogRepository.getOne(5L));
        model.addAttribute("stageLog", stageLogDto);

        model.addAttribute("validResultEnum", ValidResultEnum.values());
        model.addAttribute("validTypeEnum", ValidTypeEnum.values());

        return "stage/result";
    }


    @GetMapping("/{scenarioSubTitle}/report")
    public String getMethod_report(@PathVariable("scenarioSubTitle") String subTitle, Model model) {

        Long memberId = 1L;
        Long scenarioId = scenarioService.getIdBySubTitle(subTitle);

        MemberDto memberDto = memberService.findById(memberId); // 세션에서 member data 가져올 예정
        model.addAttribute("member", memberDto);

        ScenarioDto scenarioDto = scenarioService.findById(scenarioId);
        model.addAttribute("scenario", scenarioDto);

        List<StageLogDto> stagePassLogList = stageService.getStagePassLogList(memberId, scenarioId);
        model.addAttribute("stagePassLogList", stagePassLogList);

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
