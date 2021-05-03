package com.project.dps.controller.dto;

import com.project.dps.domain.CheckItem;
import com.project.dps.mapstruct.CheckItemMapper;
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
    private LocalDateTime createTime;

    // 생성자 메서드
    public StageDto(Long id, Long no, String comment, String title, String content,
                    List<CheckItem> checkItemList, LocalDateTime createTime) {
        this.id = id;
        this.no = no;
        this.title = title;
        this.comment = comment;
        this.content = content;
        this.createTime = createTime;

        this.checkItemDtoList = checkItemList.stream()
                .map(checkItem -> Mappers.getMapper(CheckItemMapper.class).toDto(checkItem))
                .collect(Collectors.toList());
    }


}
