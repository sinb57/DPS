package com.project.dps.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CheckItemDto {

    private String content;

    @Builder
    public CheckItemDto(String content) {
        this.content = content;
    }
}
