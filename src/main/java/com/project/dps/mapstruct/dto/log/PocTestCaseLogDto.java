package com.project.dps.mapstruct.dto.log;

import com.project.dps.domain.poc.PocResultEnum;
import com.project.dps.domain.poc.PocTypeEnum;
import lombok.Getter;

@Getter
public class PocTestCaseLogDto {

    private Long id;

    private PocResultEnum result;

    private PocTypeEnum type;

    private String category;


    // 생성자 메서드
    public PocTestCaseLogDto(Long id, PocResultEnum result, PocTypeEnum type, String category) {
        this.id = id;
        this.result = result;
        this.type = type;
        this.category = category;
    }

}
