package com.project.dps.mapstruct.mapper.poc;

import com.project.dps.domain.poc.PocTestCategory;
import com.project.dps.domain.poc.PocTestFunc;
import com.project.dps.mapstruct.dto.poc.PocTestFuncDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {PocTestCategory.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PocTestFuncMapper {

    PocTestFuncMapper INSTANCE = Mappers.getMapper(PocTestFuncMapper.class);

    @Mapping(source = "content", target = "content")
    PocTestFuncDto toDto(PocTestFunc d);

    @Mapping(source = "content", target = "content")
    PocTestFunc toEntity(PocTestFuncDto e);

}
