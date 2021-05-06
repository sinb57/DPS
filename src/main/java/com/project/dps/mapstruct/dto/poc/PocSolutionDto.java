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
public class PocSolutionDto {

    private Long id;

    private String title;

    private String content;


    // 생성자 메서드
    public PocSolutionDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
