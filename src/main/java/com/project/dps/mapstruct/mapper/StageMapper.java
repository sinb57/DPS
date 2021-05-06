package com.project.dps.mapstruct.mapper;

import com.project.dps.domain.Stage;
import com.project.dps.mapstruct.dto.StageDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {ScenarioMapper.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StageMapper {

    StageMapper INSTANCE = Mappers.getMapper(StageMapper.class);

    @Mapping(target = "pocTestCategoryList", ignore = true)
    @Mapping(target = "content", ignore = true)
    StageDto toDto(Stage e);

    @Mapping(target = "checkItemList", ignore = true)
    StageDto toDetailDto(Stage e);

    @Mapping(source = "title", target = "title")
    Stage toEntity(StageDto d);

}
