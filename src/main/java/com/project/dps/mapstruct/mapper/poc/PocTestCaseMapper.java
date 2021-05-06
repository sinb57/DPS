package com.project.dps.mapstruct.mapper.poc;

import com.project.dps.domain.poc.PocTestCase;
import com.project.dps.mapstruct.dto.poc.PocTestCaseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {PocTestFuncMapper.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PocTestCaseMapper {

    PocTestCaseMapper INSTANCE = Mappers.getMapper(PocTestCaseMapper.class);

    @Mapping(source = "content", target = "content")
    PocTestCaseDto toDto(PocTestCase e);

    @Mapping(source = "content", target = "content")
    PocTestCase toEntity(PocTestCaseDto d);

}
