package com.project.dps.mapstruct;

import com.project.dps.controller.dto.MemberDto;
import com.project.dps.domain.Member;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberMapper extends GenericMapper<MemberDto, Member> {

}
