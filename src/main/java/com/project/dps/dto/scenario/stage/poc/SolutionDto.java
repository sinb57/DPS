package com.project.dps.dto.scenario.stage.poc;

import com.project.dps.domain.scenario.stage.poc.Solution;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SolutionDto {

    private String title;
    private String reason;
    private String manual;
    private String source;


    //== Builder 메서드 ==//
    @Builder
    public SolutionDto(String title, String reason, String manual, String source) {
        this.title = title;
        this.reason = reason;
        this.manual = manual;
        this.source = source;
    }


    //== Mapper 메서드 ==//
    public static SolutionDto toDto(Solution e) {
        return SolutionDto.builder()
//                .title(e.getTitle())
//                .reason(e.getReason())
//                .manual(e.getManual())
//                .source(e.getSource())
                .build();
    }
}
