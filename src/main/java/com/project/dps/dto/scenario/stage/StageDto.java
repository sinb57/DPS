package com.project.dps.dto.scenario.stage;

import com.project.dps.domain.log.StageLog;
import com.project.dps.domain.scenario.stage.Stage;
import com.project.dps.domain.scenario.stage.check.Subject;
import com.project.dps.dto.log.StageLogDto;
import com.project.dps.dto.scenario.stage.check.SubjectDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StageDto {

    private List<StageLogDto> stageLogList;
    private List<SubjectDto> subjectList;

    private String title;
    private Long no;


    //== Builder 메서드 ==//
    @Builder
    public StageDto(String title, Long no, List<Subject> subjectList, List<StageLog> stageLogList) {
        this.title = title;
        this.no = no;

        this.stageLogList = stageLogList.stream()
                .map(stageLog -> StageLogDto.toDto(stageLog))
                .collect(Collectors.toList());

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
                .stageLogList(e.getStageLogList())
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
