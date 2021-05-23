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


    //== Builder 메서드 ==//
    @Builder
    public TestCategoryLogDto(ValidResultEnum result, ValidTypeEnum type,
                              List<TestScenarioLog> testScenarioLogList) {

        this.result = result;
        this.type = type;
        this.testScenarioLogList = testScenarioLogList.stream()
                .map(testScenarioLog -> TestScenarioLogDto.toDto(testScenarioLog))
                .collect(Collectors.toList());
    }

    //== Mapper 메서드 ==//
    public static TestCategoryLogDto toDto(TestCategoryLog e) {
        return TestCategoryLogDto.builder()
                .result(e.getResult()).type(e.getType())
                .testScenarioLogList(e.getTestScenarioLogList())
                .build();
    }

}
