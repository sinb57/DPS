package com.project.dps.dto.scenario.stage.check;

import com.project.dps.domain.scenario.stage.check.Subject;
import com.project.dps.domain.scenario.stage.check.parameter.Group;
import com.project.dps.domain.scenario.stage.check.request.Request;
import com.project.dps.dto.scenario.stage.check.parameter.GroupDto;
import com.project.dps.dto.scenario.stage.check.request.RequestDto;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SubjectDto {

    private List<RequestDto> requestList;

    private String title;
    private String result;

    private GroupDto group;

    //== Builder 메서드 ==//
    @Builder
    public SubjectDto(String title, String result, Group group, List<Request> requestList) {
        this.title = title;
        this.result = result;
        this.group = GroupDto.toDto(group);
        this.requestList = requestList.stream()
                .map(request -> RequestDto.toDto(request))
                .collect(Collectors.toList());
    }


    //== Mapper 메서드 ==//
    public static SubjectDto toDto(Subject e) {
        return SubjectDto.builder()
                .title(e.getTitle())
                .result(e.getResult())
                .group(e.getGroup())
                .requestList(e.getRequestList())
                .build();
    }

    public static SubjectDto toSimpleDto(Subject e) {
        return SubjectDto.builder()
                .title(e.getTitle())
                .build();
    }

}
