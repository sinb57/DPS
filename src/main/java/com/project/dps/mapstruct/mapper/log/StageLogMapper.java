package com.project.dps.mapstruct.mapper.log;

import com.project.dps.domain.log.StageLog;
import com.project.dps.mapstruct.dto.log.StageLogDto;
import com.project.dps.mapstruct.mapper.StageMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {StageMapper.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StageLogMapper {

    StageLogMapper INSTANCE = Mappers.getMapper(StageLogMapper.class);

    @Mapping(source = "result", target = "result")
    StageLogDto toDto(StageLog d);

    @Mapping(source = "result", target = "result")
    StageLog toEntity(StageLogDto e);

}
