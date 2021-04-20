package com.project.dps.controller.dto;

import com.project.dps.domain.Scenario;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class StageDto implements Serializable {

    private Long no;
    private String content;
    private LocalDateTime createTime;
}
