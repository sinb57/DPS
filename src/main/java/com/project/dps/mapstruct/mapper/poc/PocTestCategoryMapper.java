package com.project.dps.mapstruct.mapper.poc;

import com.project.dps.domain.poc.PocTestCategory;
import com.project.dps.mapstruct.dto.poc.PocTestCategoryDto;
import com.project.dps.mapstruct.mapper.StageMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {StageMapper.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PocTestCategoryMapper {

    PocTestCategoryMapper INSTANCE = Mappers.getMapper(PocTestCategoryMapper.class);

    @Mapping(source = "category", target = "category")
    PocTestCategoryDto toDto(PocTestCategory e);

    @Mapping(source = "category", target = "category")
    PocTestCategory toEntity(PocTestCategoryDto d);

}
