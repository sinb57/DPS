package com.project.dps.dto.log;

import com.project.dps.domain.log.PocLog;
import com.project.dps.domain.log.StageLog;
import com.project.dps.domain.scenario.stage.poc.ValidResultEnum;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StageLogDto {

    private List<PocLogDto> pocLogDtoList;

    private ValidResultEnum result;

    private LocalDateTime createTime;


    //== Builder 메서드 ==//
    @Builder
    public StageLogDto(ValidResultEnum result, LocalDateTime createTime,
                       List<PocLog> pocLogList) {
        this.result = result;
        this.createTime = createTime;
        this.pocLogDtoList = pocLogList.stream()
                .map(pocLog -> PocLogDto.toDto(pocLog))
                .collect(Collectors.toList());
    }

    //== Mapper 메서드 ==//
    public static StageLogDto toDto(StageLog e) {
        return StageLogDto.builder()
                .pocLogList(e.getPocLogList())
                .result(e.getResult())
                .createTime(e.getCreateTime())
                .build();
    }
}
