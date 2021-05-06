package com.project.dps.mapstruct.mapper;

import com.project.dps.controller.dto.CheckItemDto;
import com.project.dps.domain.CheckItem;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        uses = {StageMapper.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CheckItemMapper extends GenericMapper<CheckItemDto, CheckItem> {

}
