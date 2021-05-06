package com.project.dps.mapstruct.dto;

import com.project.dps.domain.CheckItem;
import com.project.dps.domain.poc.PocTestCategory;
import com.project.dps.mapstruct.mapper.CheckItemMapper;
import com.project.dps.mapstruct.dto.poc.PocTestCategoryDto;
import com.project.dps.mapstruct.mapper.poc.PocTestCategoryMapper;
import lombok.Getter;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class StageDto {

    private Long id;
    private Long no;
    private String title;
    private String comment;
    private String content;
    private List<CheckItemDto> checkItemDtoList;
    private List<PocTestCategoryDto> pocTestCategoryDtoList;
    private LocalDateTime createTime;

    // 생성자 메서드
    public StageDto(Long id, Long no, String comment, String title, String content,
                    List<CheckItem> checkItemList, List<PocTestCategory> pocTestCategoryList, LocalDateTime createTime) {
        this.no = no;
        this.title = title;
        this.comment = comment;
        this.content = content;
        this.createTime = createTime;

        if (checkItemList != null) {
            this.checkItemDtoList = checkItemList.stream()
                    .map(checkItem -> Mappers.getMapper(CheckItemMapper.class).toDto(checkItem))
                    .collect(Collectors.toList());
        }

        if (pocTestCategoryList != null) {
            this.pocTestCategoryDtoList = pocTestCategoryList.stream()
                    .map(pocTestCategory -> Mappers.getMapper(PocTestCategoryMapper.class).toDto(pocTestCategory))
                    .collect(Collectors.toList());
        }


    }


}
