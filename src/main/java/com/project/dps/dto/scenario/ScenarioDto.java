package com.project.dps.dto.scenario;

import com.project.dps.domain.log.ScenarioLog;
import com.project.dps.domain.scenario.Scenario;
import com.project.dps.domain.scenario.stage.Stage;
import com.project.dps.dto.log.ScenarioLogDto;
import com.project.dps.dto.scenario.stage.StageDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ScenarioDto {

    private List<StageDto> stageList;

    private List<ScenarioLogDto> scenarioLogList;

    private String title;

    private String subTitle;

    private String content;

    private int stageCount;


    //== Builder 메서드 ==//
    @Builder
    public ScenarioDto(String title, String subTitle, String content, int stageCount,
                       List<Stage> stageList, List<ScenarioLog> scenarioLogList) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.stageCount = stageCount;
        this.stageList = stageList.stream()
                .map(stage -> StageDto.toDto(stage))
                .collect(Collectors.toList());
        this.scenarioLogList = scenarioLogList.stream()
                .map(scenarioLog -> ScenarioLogDto.toDto(scenarioLog))
                .collect(Collectors.toList());
    }

    @Builder(builderMethodName = "simpleBuilder")
    public ScenarioDto(String title, String subTitle, String content, int stageCount, List<Stage> stageList) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.stageCount = stageCount;
        this.stageList = stageList.stream()
                .map(stage -> StageDto.toSimpleDto(stage))
                .collect(Collectors.toList());
    }



    //== Mapper 메서드 ==//
    public static ScenarioDto toDto(Scenario e) {
        return ScenarioDto.builder()
                .title(e.getTitle())
                .subTitle(e.getSubTitle())
                .content(e.getContent())
                .stageCount(e.getStageCount())
                .stageList(e.getStageList())
                .scenarioLogList(e.getScenarioLogList())
                .build();
    }

    public static ScenarioDto toSimpleDto(Scenario e) {
        return ScenarioDto.simpleBuilder()
                .title(e.getTitle())
                .subTitle(e.getSubTitle())
                .content(e.getContent())
                .stageCount(e.getStageCount())
                .stageList(e.getStageList())
                .build();
    }

}
