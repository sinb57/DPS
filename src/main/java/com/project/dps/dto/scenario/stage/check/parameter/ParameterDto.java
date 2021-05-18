package com.project.dps.dto.scenario.stage.check.parameter;

import com.project.dps.domain.scenario.stage.check.parameter.Group;
import com.project.dps.domain.scenario.stage.check.parameter.Parameter;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ParameterDto {

    private String name;
    private String value;

    //== Builder 메서드 ==//
    @Builder
    public ParameterDto(String name, String value) {
        this.name = name;
        this.value = value;
    }

    //== Mapper 메서드 ==//
    public static ParameterDto toDto(Parameter e) {
        return ParameterDto.builder()
                .name(e.getName())
                .value(e.getValue())
                .build();
    }

}
