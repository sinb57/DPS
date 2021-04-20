package com.project.dps.controller.dto;

import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class ScenarioDto implements Serializable {

    private String title;
    private String content;
    private int stageCount;
    private LocalDateTime createTime;

}
