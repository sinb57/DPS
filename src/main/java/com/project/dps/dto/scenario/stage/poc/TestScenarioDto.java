package com.project.dps.dto.scenario.stage.poc;

import com.project.dps.domain.scenario.stage.poc.Solution;
import com.project.dps.domain.scenario.stage.poc.TestCommon;
import com.project.dps.domain.scenario.stage.poc.TestScenario;
import com.project.dps.domain.scenario.stage.poc.ValidTypeEnum;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TestScenarioDto {

    private List<TestCommonDto> testCommonList;

    private SolutionDto solution;

    @Enumerated(EnumType.STRING)
    private ValidTypeEnum type; //ENUM [FUNCTIONAL(기능), SECURE(보안)]

    private String content;


    //== Builder 메서드 ==//
    @Builder
    public TestScenarioDto(ValidTypeEnum type, String content, Solution solution,
                           List<TestCommon> testCommonList) {
        this.type = type;
        this.content = content;
        this.solution = SolutionDto.toDto(solution);
        this.testCommonList = testCommonList.stream()
                .map(testCommon -> TestCommonDto.toDto(testCommon))
                .collect(Collectors.toList());
    }

    //== Mapper ==//
    public static TestScenarioDto toDto(TestScenario e) {
        return TestScenarioDto.builder()
                .type(e.getType())
                .content(e.getContent())
                .solution(e.getSolution())
                .testCommonList(e.getTestCommonList())
                .build();
    }

}
