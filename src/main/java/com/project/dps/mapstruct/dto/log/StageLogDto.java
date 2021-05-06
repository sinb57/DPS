package com.project.dps.mapstruct.dto.log;

import com.project.dps.domain.log.PocTestCaseLog;
import com.project.dps.domain.poc.PocResultEnum;
import com.project.dps.domain.poc.PocTypeEnum;
import com.project.dps.mapstruct.mapper.log.PocTestCaseLogMapper;
import lombok.Getter;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StageLogDto {

    private Long id;

    private PocResultEnum result;

    private LocalDateTime createTime;

    private List<PocTestCaseLogDto> pocTestCaseLogDtoList;


    // 생성자 메서드
    public StageLogDto(Long id, PocResultEnum result, LocalDateTime createTime,
                           List<PocTestCaseLog> pocTestCaseLogList) {
        this.id = id;
        this.result = result;
        this.createTime = createTime;

        if (pocTestCaseLogList != null) {
            this.pocTestCaseLogDtoList = pocTestCaseLogList.stream()
                    .map(pocTestCaseLog -> Mappers.getMapper(PocTestCaseLogMapper.class).toDto(pocTestCaseLog))
                    .collect(Collectors.toList());
        }
    }
}
