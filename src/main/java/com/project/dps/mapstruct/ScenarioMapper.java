package com.project.dps.mapstruct;

import com.project.dps.controller.dto.ScenarioDto;
import com.project.dps.domain.Scenario;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ScenarioMapper extends GenericMapper<ScenarioDto, Scenario> {

}
