package com.project.dps.mapstruct.dto.poc;

import com.project.dps.domain.poc.PocSolution;
import com.project.dps.domain.log.PocTestCaseLog;
import com.project.dps.mapstruct.dto.log.PocTestCaseLogDto;
import com.project.dps.mapstruct.mapper.log.PocTestCaseLogMapper;
import lombok.Getter;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PocTestCaseDto {

    private Long id;

    private List<PocTestCaseLogDto> pocTestCaseLogDtoList;

    private PocSolution pocSolution;

    private String content;

    private LocalDateTime createtime;


    // 생성자 메서드
    public PocTestCaseDto(Long id, PocSolution pocSolution, String content,
                          List<PocTestCaseLog> pocTestCaselogList, LocalDateTime createTime) {
        this.id = id;
        this.pocSolution = pocSolution;
        this.content = content;
        this.createtime = createtime;

        if (pocTestCaselogList != null) {
            this.pocTestCaseLogDtoList = pocTestCaselogList.stream()
                    .map(pocTestCaseLog -> Mappers.getMapper(PocTestCaseLogMapper.class).toDto(pocTestCaseLog))
                    .collect(Collectors.toList());
        }

    }

}
