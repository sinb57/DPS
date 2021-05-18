package com.project.dps.dto.scenario.stage.check.parameter;

import com.project.dps.domain.scenario.stage.check.parameter.Group;
import com.project.dps.domain.scenario.stage.check.parameter.MethodTypeEnum;
import com.project.dps.domain.scenario.stage.check.parameter.Parameter;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GroupDto {

    private List<ParameterDto> parameterList = new ArrayList<>();

    private MethodTypeEnum type;


    //== Builder 메서드 ==//
    @Builder
    public GroupDto(MethodTypeEnum type, List<Parameter> parameterList) {
        this.type = type;
        this.parameterList = parameterList.stream()
                .map(parameter -> ParameterDto.toDto(parameter))
                .collect(Collectors.toList());
    }

    //== Mapper 메서드 ==//
    public static GroupDto toDto(Group e) {
        return GroupDto.builder()
                .type(e.getType())
                .parameterList(e.getParameterList())
                .build();
    }

}
