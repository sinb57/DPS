package com.project.dps.dto.scenario.stage.poc;

import com.project.dps.domain.scenario.stage.poc.Solution;
import com.project.dps.domain.scenario.stage.poc.TestScenario;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TestScenarioDto {

    private SolutionDto solution;

    private String content;


    //== Builder 메서드 ==//
    @Builder
    public TestScenarioDto(String content, Solution solution) {
        this.content = content;
        this.solution = SolutionDto.toDto(solution);
    }

    //== Mapper ==//
    public static TestScenarioDto toDto(TestScenario e) {
        return TestScenarioDto.builder()
                .content(e.getContent())
                .solution(e.getSolution())
                .build();
    }

}
