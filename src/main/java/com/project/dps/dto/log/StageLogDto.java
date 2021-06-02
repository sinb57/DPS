package com.project.dps.dto.log;

import com.project.dps.domain.log.StageLog;
import com.project.dps.domain.log.TestCategoryLog;
import com.project.dps.domain.log.TestScenarioLog;
import com.project.dps.domain.scenario.stage.Stage;
import com.project.dps.domain.scenario.stage.poc.ValidResultEnum;
import com.project.dps.dto.scenario.stage.StageDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import java.time.format.DateTimeFormatter;

@Getter
public class StageLogDto {

    private Long id;

    private List<TestCategoryLogDto> testCategoryLogList;

    private StageDto stage;

    private ValidResultEnum result;

    private LocalDateTime createTime;


    //== Builder 메서드 ==//
    @Builder
    public StageLogDto(Long id, Stage stage, ValidResultEnum result, LocalDateTime createTime,
                       List<TestCategoryLog> testCategoryLogList) {
        this.id = id;
        this.stage = StageDto.toDto(stage);
        this.result = result;
        this.createTime = createTime;
        this.testCategoryLogList = testCategoryLogList.stream()
                .map(testCategoryLog -> TestCategoryLogDto.toDto(testCategoryLog))
                .collect(Collectors.toList());
    }

    //== Mapper 메서드 ==//
    public static StageLogDto toDto(StageLog e) {
        return StageLogDto.builder()
                .id(e.getId())
                .stage(e.getStage()).result(e.getResult())
                .createTime(e.getCreateTime())
                .testCategoryLogList(e.getTestCategoryLogList())
                .build();
    }
}
