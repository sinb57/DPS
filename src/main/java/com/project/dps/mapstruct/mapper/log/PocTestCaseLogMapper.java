package com.project.dps.mapstruct.mapper.log;

import com.project.dps.domain.log.PocTestCaseLog;
import com.project.dps.mapstruct.dto.log.PocTestCaseLogDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {StageLogMapper.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PocTestCaseLogMapper {

    PocTestCaseLogMapper INSTANCE = Mappers.getMapper(PocTestCaseLogMapper.class);

    @Mapping(source = "category", target = "category")
    PocTestCaseLogDto toDto(PocTestCaseLog d);

    @Mapping(source = "category", target = "category")
    PocTestCaseLog toEntity(PocTestCaseLogDto e);

}
