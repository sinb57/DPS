package com.project.dps.dto.log;

import com.project.dps.domain.log.TestCaseLog;
import com.project.dps.domain.log.TestScenarioLog;
import com.project.dps.domain.scenario.stage.poc.TestScenario;
import com.project.dps.domain.scenario.stage.poc.ValidResultEnum;
import com.project.dps.domain.scenario.stage.poc.ValidTypeEnum;
import com.project.dps.dto.scenario.stage.poc.TestScenarioDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TestScenarioLogDto {

    private List<TestCaseLogDto> testCaseLogList;

    private TestScenarioDto testScenario;

    private ValidResultEnum result;

    private int testCaseCount;
    private int testCasePassCount;

    //== Builder 메서드 ==//
    @Builder
    public TestScenarioLogDto(int testCaseCount, int testCasePassCount,
                              TestScenario testScenario, ValidResultEnum result,
                              List<TestCaseLog> testCaseLogList) {

        this.testCaseCount = testCaseCount;
        this.testCasePassCount = testCasePassCount;
        this.testScenario = TestScenarioDto.toDto(testScenario);
        this.result = result;
        this.testCaseLogList = testCaseLogList.stream()
                .map(testCaseLog -> TestCaseLogDto.toDto(testCaseLog))
                .collect(Collectors.toList());
    }

    //== Mapper 메서드 ==//
    public static TestScenarioLogDto toDto(TestScenarioLog e) {
        return TestScenarioLogDto.builder()
                .result(e.getResult())
                .testCaseCount(e.getTestCaseCount())
                .testCasePassCount(e.getTestCasePassCount())
                .testScenario(e.getTestScenario())
                .testCaseLogList(e.getTestCaseLogList())
                .build();
    }

}
