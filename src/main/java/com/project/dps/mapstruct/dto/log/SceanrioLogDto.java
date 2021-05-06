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
public class SceanrioLogDto {

    private LocalDateTime createTime;


    // 생성자 메서드
    public SceanrioLogDto(LocalDateTime createTime) {
        this.createTime = createTime;

    }
}