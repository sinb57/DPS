package com.project.dps.dto.scenario.stage.poc;

import com.project.dps.domain.log.TestCaseLog;
import com.project.dps.domain.scenario.stage.poc.TestCase;
import com.project.dps.dto.log.TestCaseLogDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TestCaseDto {

    private List<TestCaseLogDto> testCaseLogList;

    private String content;


    //== Builder 메서드 ==//
    @Builder
    public TestCaseDto(String content, List<TestCaseLog> testCaseLogList) {
        this.content = content;
        this.testCaseLogList = testCaseLogList.stream()
                .map(pocLog -> TestCaseLogDto.toDto(pocLog))
                .collect(Collectors.toList());
    }

    //== Mapper 메서드 ==//
    public static TestCaseDto toDto(TestCase e) {
        return TestCaseDto.builder()
                .testCaseLogList(e.getTestCaseLogList())
                .content(e.getContent())
                .build();
    }

}
