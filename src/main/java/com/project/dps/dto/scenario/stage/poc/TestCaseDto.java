package com.project.dps.dto.scenario.stage.poc;

import com.project.dps.domain.log.PocLog;
import com.project.dps.domain.scenario.stage.poc.TestCase;
import com.project.dps.dto.log.PocLogDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class TestCaseDto {

    private List<PocLogDto> pocLogList;

    private String content;


    //== Builder 메서드 ==//
    @Builder
    public TestCaseDto(String content, List<PocLog> pocLogList) {
        this.content = content;
        this.pocLogList = pocLogList.stream()
                .map(pocLog -> PocLogDto.toDto(pocLog))
                .collect(Collectors.toList());
    }

    //== Mapper 메서드 ==//
    public static TestCaseDto toDto(TestCase e) {
        return TestCaseDto.builder()
                .pocLogList(e.getPocLogList())
                .content(e.getContent())
                .build();
    }

}
