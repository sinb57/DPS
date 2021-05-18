package com.project.dps.dto.log;

import com.project.dps.domain.log.PocLog;
import com.project.dps.domain.scenario.stage.poc.ValidResultEnum;
import com.project.dps.domain.scenario.stage.poc.ValidTypeEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PocLogDto {

    private ValidResultEnum result;

    private ValidTypeEnum type;

    private String category;


    //== Builder 메서드 ==//
    @Builder
    public PocLogDto(ValidResultEnum result, ValidTypeEnum type, String category) {
        this.result = result;
        this.type = type;
        this.category = category;
    }

    //== Mapper 메서드 ==//
    public static PocLogDto toDto(PocLog e) {
        return PocLogDto.builder()
                .result(e.getResult())
                .type(e.getType())
                .category(e.getCategory())
                .build();
    }

}
