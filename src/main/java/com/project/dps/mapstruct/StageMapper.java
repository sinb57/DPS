package com.project.dps.mapstruct;

import com.project.dps.controller.dto.StageDto;
import com.project.dps.domain.Stage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {ScenarioMapper.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StageMapper extends GenericMapper<StageDto, Stage> {

}
