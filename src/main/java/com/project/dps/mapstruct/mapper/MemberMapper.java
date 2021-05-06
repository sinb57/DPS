package com.project.dps.mapstruct.mapper;

import com.project.dps.domain.Member;
import com.project.dps.mapstruct.dto.MemberDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mapping(source = "name", target = "name")
    MemberDto toDto(Member e);

    @Mapping(source = "name", target = "name")
    Member toEntity(MemberDto d);

}
