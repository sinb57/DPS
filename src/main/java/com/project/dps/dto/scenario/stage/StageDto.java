package com.project.dps.dto.scenario.stage;

import com.project.dps.domain.scenario.stage.Stage;
import com.project.dps.domain.scenario.stage.check.Subject;
import com.project.dps.dto.scenario.stage.check.SubjectDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StageDto {

    private List<SubjectDto> subjectList;

    private String title;
    private Long no;


    //== Builder 메서드 ==//
    @Builder
    public StageDto(String title, Long no, List<Subject> subjectList) {
        this.title = title;
        this.no = no;

        this.subjectList = subjectList.stream()
                .map(subject -> SubjectDto.toDto(subject))
                .collect(Collectors.toList());
    }


    //== Mapper 메서드 ==//
    public static StageDto toDto(Stage e) {
        return StageDto.builder()
                .title(e.getTitle())
                .no(e.getNo())
                .subjectList(e.getSubjectList())
                .build();
    }

    public static StageDto toSimpleDto(Stage e) {
        return StageDto.builder()
                .title(e.getTitle())
                .no(e.getNo())
                .subjectList(e.getSubjectList())
                .build();
    }


}
