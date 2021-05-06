package com.project.dps.mapstruct.dto.poc;

import com.project.dps.domain.poc.PocSolution;
import com.project.dps.domain.poc.PocTestFunc;
import com.project.dps.domain.poc.PocTypeEnum;
import com.project.dps.mapstruct.mapper.poc.PocSolutionMapper;
import com.project.dps.mapstruct.mapper.poc.PocTestFuncMapper;
import lombok.Getter;
import org.mapstruct.factory.Mappers;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PocTestCategoryDto {

    private Long id;

    @Enumerated(EnumType.STRING)
    private PocTypeEnum type; //ENUM [FUNCTIONAL(기능), SECURE(보안)]

    private String category;

    private List<PocTestFuncDto> pocTestFuncDtoList;

    private PocSolutionDto pocSolutionDto;


    // 생성자 메서드
    public PocTestCategoryDto(Long id, PocTypeEnum type, String category,
                              List<PocTestFunc> pocTestFuncList, PocSolution pocSolution) {
        this.id = id;
        this.type = type;
        this.category = category;
        this.pocSolutionDto = Mappers.getMapper(PocSolutionMapper.class).toDto(pocSolution);

        if (pocTestFuncList != null) {
            this.pocTestFuncDtoList = pocTestFuncList.stream()
            .map(pocTestFunc -> Mappers.getMapper(PocTestFuncMapper.class).toDto(pocTestFunc))
            .collect(Collectors.toList());
        }

    }

}
