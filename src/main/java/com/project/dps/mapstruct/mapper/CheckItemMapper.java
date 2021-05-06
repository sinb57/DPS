package com.project.dps.mapstruct.mapper;

import com.project.dps.domain.CheckItem;
import com.project.dps.mapstruct.dto.CheckItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = {StageMapper.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CheckItemMapper {

    CheckItemMapper INSTANCE = Mappers.getMapper(CheckItemMapper.class);

    @Mapping(source = "content", target = "content")
    CheckItemDto toDto(CheckItem e);

    @Mapping(source = "content", target = "content")
    CheckItem toEntity(CheckItemDto d);

}
