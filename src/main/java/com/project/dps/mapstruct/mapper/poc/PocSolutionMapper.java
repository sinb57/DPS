package com.project.dps.mapstruct.mapper.poc;

import com.project.dps.domain.poc.PocSolution;
import com.project.dps.domain.poc.PocTestCase;
import com.project.dps.domain.poc.PocTestCategory;
import com.project.dps.domain.poc.PocTestFunc;
import com.project.dps.mapstruct.dto.poc.PocSolutionDto;
import com.project.dps.mapstruct.dto.poc.PocTestCaseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {PocTestCategory.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PocSolutionMapper {

    PocSolutionMapper INSTANCE = Mappers.getMapper(PocSolutionMapper.class);

    @Mapping(source = "pocSolution", target = "pocSolution")
    PocSolutionDto toDto(PocSolution e);

    @Mapping(source = "pocSolution", target = "pocSolution")
    PocSolution toEntity(PocSolutionDto d);
}
