package com.project.dps.dto.log;

import com.project.dps.domain.log.TestCategoryLog;
import com.project.dps.domain.log.TestScenarioLog;
import com.project.dps.domain.scenario.stage.poc.ValidResultEnum;
import com.project.dps.domain.scenario.stage.poc.ValidTypeEnum;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TestCategoryLogDto {

    private List<TestScenarioLogDto> testScenarioLogList;

    private ValidResultEnum result;

    private ValidTypeEnum type;

    private int testScenarioCount;
    private int testScenarioPassCount;

    //== Builder 메서드 ==//
    @Builder
    public TestCategoryLogDto(ValidResultEnum result, ValidTypeEnum type,
                              List<TestScenarioLog> testScenarioLogList,
                              int testScenarioCount, int testScenarioPassCount) {

        this.testScenarioCount = testScenarioCount;
        this.testScenarioPassCount = testScenarioPassCount;
        this.result = result;
        this.type = type;
        this.testScenarioLogList = testScenarioLogList.stream()
                .map(testScenarioLog -> TestScenarioLogDto.toDto(testScenarioLog))
                .collect(Collectors.toList());
    }

    //== Mapper 메서드 ==//
    public static TestCategoryLogDto toDto(TestCategoryLog e) {
        return TestCategoryLogDto.builder()
                .testScenarioCount(e.getTestScenarioCount())
                .testScenarioPassCount(e.getTestScenarioPassCount())
                .result(e.getResult()).type(e.getType())
                .testScenarioLogList(e.getTestScenarioLogList())
                .build();
    }

}
