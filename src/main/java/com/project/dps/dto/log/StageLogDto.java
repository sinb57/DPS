package com.project.dps.dto.log;

import com.project.dps.domain.log.StageLog;
import com.project.dps.domain.log.TestCategoryLog;
import com.project.dps.domain.log.TestScenarioLog;
import com.project.dps.domain.scenario.stage.poc.ValidResultEnum;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StageLogDto {

    private List<TestCategoryLogDto> testCategoryLogList;

    private ValidResultEnum result;

    private LocalDateTime createTime;


    //== Builder 메서드 ==//
    @Builder
    public StageLogDto(ValidResultEnum result, LocalDateTime createTime,
                       List<TestCategoryLog> testCategoryLogList) {
        this.result = result;
        this.createTime = createTime;
        this.testCategoryLogList = testCategoryLogList.stream()
                .map(testCategoryLog -> TestCategoryLogDto.toDto(testCategoryLog))
                .collect(Collectors.toList());
    }

    //== Mapper 메서드 ==//
    public static StageLogDto toDto(StageLog e) {
        return StageLogDto.builder()
                .testCategoryLogList(e.getTestCategoryLogList())
                .result(e.getResult())
                .createTime(e.getCreateTime())
                .build();
    }
}
