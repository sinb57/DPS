package com.project.dps.dto.scenario.stage.poc;

import com.project.dps.domain.scenario.stage.poc.TestCase;
import com.project.dps.domain.scenario.stage.poc.TestCommon;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TestCommonDto {

    private List<TestCaseDto> testCaseList;

    private String content;


    //== Builder 메서드 ==//
    @Builder
    public TestCommonDto(String content, List<TestCase> testCaseList) {
        this.content = content;

        this.testCaseList = testCaseList.stream()
                .map(testCase -> TestCaseDto.toDto(testCase))
                .collect(Collectors.toList());
    }



    //== Mapper 메서드 ==//
    public static TestCommonDto toDto(TestCommon e) {
        return TestCommonDto.builder()
                .testCaseList(e.getTestCaseList())
                .content(e.getContent())
                .build();
    }


}
