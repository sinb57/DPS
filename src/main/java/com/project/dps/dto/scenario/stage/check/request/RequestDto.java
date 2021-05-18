package com.project.dps.dto.scenario.stage.check.request;

import com.project.dps.domain.scenario.stage.check.request.Request;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestDto {

    private String content;


    //== Builder 메서드 ==//
    @Builder
    public RequestDto(String content) {
        this.content = content;
    }


    //== Mapper 메서드 ==//
    public static RequestDto toDto(Request e) {
        return RequestDto.builder()
                .content(e.getContent())
                .build();
    }

}
