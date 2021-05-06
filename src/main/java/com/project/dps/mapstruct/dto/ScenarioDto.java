package com.project.dps.mapstruct.dto;

import com.project.dps.domain.Stage;
import com.project.dps.mapstruct.mapper.StageMapper;
import lombok.*;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ScenarioDto {

    private Long id;

    private String title;

    private String subTitle;

    private String content;

    private int stageCount;

    private List<StageDto> stageDtoList;

    private LocalDateTime createTime;

    // 생성자 메서드
    public ScenarioDto(Long id, String title, String subTitle, String content,
                       int stageCount, List<Stage> stageList, LocalDateTime createTime) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.createTime = createTime;
        this.stageCount = stageCount;

        if (stageList != null) {
            this.stageDtoList = stageList.stream()
                    .map(stage -> Mappers.getMapper(StageMapper.class).toDto(stage))
                    .collect(Collectors.toList());
        }
    }

}
