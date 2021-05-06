package com.project.dps.mapstruct.mapper;

import com.project.dps.domain.Scenario;
import com.project.dps.mapstruct.dto.ScenarioDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ScenarioMapper {
    ScenarioMapper INSTANCE = Mappers.getMapper(ScenarioMapper.class);

    @Mapping(source = "subTitle", target = "subTitle")
    ScenarioDto toDto(Scenario e);

    @Mapping(target = "stageList", ignore = true)
    ScenarioDto toSimpleDto(Scenario e);


    @Mapping(source = "subTitle", target = "subTitle")
    Scenario toEntity(ScenarioDto d);

}