package com.project.dps.mapstruct.mapper;

import com.project.dps.controller.dto.ScenarioDto;
import com.project.dps.domain.Scenario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ScenarioMapper extends GenericMapper<ScenarioDto, Scenario> {

    @Mapping(target = "stageList", ignore = true)
    ScenarioDto toSimpleDto(Scenario s);

}
