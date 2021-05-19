package com.project.dps.dto.log;

import com.project.dps.domain.log.TestCaseLog;
import com.project.dps.domain.scenario.stage.poc.ValidResultEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TestCaseLogDto {

    private ValidResultEnum result;


    //== Builder 메서드 ==//
    @Builder
    public TestCaseLogDto(ValidResultEnum result) {
        this.result = result;
    }

    //== Mapper 메서드 ==//
    public static TestCaseLogDto toDto(TestCaseLog e) {
        return TestCaseLogDto.builder()
                .result(e.getResult())
                .build();
    }

}
