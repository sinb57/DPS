package com.project.dps.dto.scenario.stage.check;

import com.project.dps.domain.scenario.stage.check.Subject;
import com.project.dps.domain.scenario.stage.check.parameter.Group;
import com.project.dps.domain.scenario.stage.check.request.Request;
import com.project.dps.domain.scenario.stage.check.result.Result;
import com.project.dps.dto.scenario.stage.check.parameter.GroupDto;
import com.project.dps.dto.scenario.stage.check.request.RequestDto;
import com.project.dps.dto.scenario.stage.check.result.ResultDto;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SubjectDto {

    private List<RequestDto> requestList;

    private List<ResultDto> resultList;

    private String title;

    private GroupDto group;

    //== Builder 메서드 ==//
    @Builder
    public SubjectDto(String title, Group group, List<Request> requestList, List<Result> resultList) {
        this.title = title;
        this.group = GroupDto.toDto(group);
        this.requestList = requestList.stream()
                .map(request -> RequestDto.toDto(request))
                .collect(Collectors.toList());
        this.resultList = resultList.stream()
                .map(result -> ResultDto.toDto(result))
                .collect(Collectors.toList());

    }


    //== Mapper 메서드 ==//
    public static SubjectDto toDto(Subject e) {
        return SubjectDto.builder()
                .title(e.getTitle())
                .group(e.getGroup())
                .requestList(e.getRequestList())
                .resultList(e.getResultList())
                .build();
    }

    public static SubjectDto toSimpleDto(Subject e) {
        return SubjectDto.builder()
                .title(e.getTitle())
                .build();
    }

}
