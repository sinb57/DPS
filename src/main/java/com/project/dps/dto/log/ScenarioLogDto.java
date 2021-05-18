package com.project.dps.dto.log;

import com.project.dps.domain.log.ScenarioLog;
import com.project.dps.domain.scenario.stage.poc.ValidResultEnum;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScenarioLogDto {

    private ValidResultEnum result;

    private LocalDateTime createTime;

    //== Builder 메서드 ==//
    @Builder
    public ScenarioLogDto(ValidResultEnum result, LocalDateTime createTime) {
        this.result = result;
        this.createTime = createTime;
    }

    //== Mapper 메서드 ==//
    public static ScenarioLogDto toDto(ScenarioLog e) {
        return ScenarioLogDto.builder()
                .result(e.getResult())
                .createTime(e.getLocalDateTime())
                .build();
    }
}