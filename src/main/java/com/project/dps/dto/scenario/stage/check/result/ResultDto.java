package com.project.dps.dto.scenario.stage.check.result;

import com.project.dps.domain.scenario.stage.check.request.Request;
import com.project.dps.domain.scenario.stage.check.result.Result;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResultDto {

    private String content;


    //== Builder 메서드 ==//
    @Builder
    public ResultDto(String content) {
        this.content = content;
    }


    //== Mapper 메서드 ==//
    public static ResultDto toDto(Result e) {
        return ResultDto.builder()
                .content(e.getContent())
                .build();
    }

}
