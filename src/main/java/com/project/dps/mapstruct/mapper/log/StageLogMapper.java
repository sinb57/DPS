package com.project.dps.mapstruct.mapper.log;

import com.project.dps.domain.Stage;
import com.project.dps.domain.log.PocTestCaseLog;
import com.project.dps.domain.log.StageLog;
import com.project.dps.mapstruct.dto.log.PocTestCaseLogDto;
import com.project.dps.mapstruct.dto.log.StageLogDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {Stage.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StageLogMapper {

    StageLogMapper INSTANCE = Mappers.getMapper(StageLogMapper.class);

    @Mapping(source = "result", target = "result")
    StageLogDto toDto(StageLog d);

    @Mapping(source = "result", target = "result")
    StageLog toEntity(StageLogDto e);

}
