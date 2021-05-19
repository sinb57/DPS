package com.project.dps.dto.log;

import com.project.dps.domain.log.StageLog;
import com.project.dps.domain.log.TestScenarioLog;
import com.project.dps.domain.scenario.stage.poc.ValidResultEnum;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StageLogDto {

    private List<TestScenarioLogDto> testScenarioLogList;

    private ValidResultEnum result;

    private LocalDateTime createTime;


    //== Builder 메서드 ==//
    @Builder
    public StageLogDto(ValidResultEnum result, LocalDateTime createTime,
                       List<TestScenarioLog> testScenarioLogList) {
        this.result = result;
        this.createTime = createTime;
        this.testScenarioLogList = testScenarioLogList.stream()
                .map(testScenarioLog -> TestScenarioLogDto.toDto(testScenarioLog))
                .collect(Collectors.toList());
    }

    //== Mapper 메서드 ==//
    public static StageLogDto toDto(StageLog e) {
        return StageLogDto.builder()
                .testScenarioLogList(e.getTestScenarioLogList())
                .result(e.getResult())
                .createTime(e.getCreateTime())
                .build();
    }
}
