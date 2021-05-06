package com.project.dps.mapstruct.mapper.poc;

import com.project.dps.domain.poc.PocSolution;
import com.project.dps.mapstruct.dto.poc.PocSolutionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {PocTestCategoryMapper.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PocSolutionMapper {

    PocSolutionMapper INSTANCE = Mappers.getMapper(PocSolutionMapper.class);

    @Mapping(source = "title", target = "title")
    PocSolutionDto toDto(PocSolution e);

    @Mapping(source = "title", target = "title")
    PocSolution toEntity(PocSolutionDto d);
}
