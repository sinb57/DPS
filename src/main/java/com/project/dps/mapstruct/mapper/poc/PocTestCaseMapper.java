package com.project.dps.mapstruct.mapper.poc;

import com.project.dps.domain.poc.PocTestCase;
import com.project.dps.domain.poc.PocTestFunc;
import com.project.dps.mapstruct.dto.poc.PocTestCaseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {PocTestFunc.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PocTestCaseMapper {

    PocTestCaseMapper INSTANCE = Mappers.getMapper(PocTestCaseMapper.class);

    @Mapping(source = "pocSolution", target = "pocSolution")
    PocTestCaseDto toDto(PocTestCase d);

    @Mapping(source = "pocSolution", target = "pocSolution")
    PocTestCase toEntity(PocTestCaseDto e);

}
