package com.project.dps.mapstruct.dto.poc;

import com.project.dps.domain.poc.PocSolution;
import com.project.dps.domain.poc.PocTestCase;
import com.project.dps.mapstruct.mapper.poc.PocTestCaseMapper;
import lombok.Getter;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PocTestFuncDto {

    private String content;

    private LocalDateTime createTime;

    private List<PocTestCaseDto> pocTestCaseList;


    // 생성자 메서드
    public PocTestFuncDto (String content, LocalDateTime createTime,
                            List<PocTestCase> pocTestCaseList) {
        this.content = content;
        this.createTime = createTime;

        if (pocTestCaseList != null) {
            this.pocTestCaseList = pocTestCaseList.stream()
                    .map(pocTestCase -> Mappers.getMapper(PocTestCaseMapper.class).toDto(pocTestCase))
                    .collect(Collectors.toList());
        }
    }
}
