package com.project.dps.domain;

import com.project.dps.domain.log.ScenarioPassLog;
import com.project.dps.exception.NoMoreStageException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Scenario {

    @Id @GeneratedValue
    @Column(name = "scenario_id")
    private Long id;

    @OneToMany(mappedBy = "scenario")
    private List<Stage> stageList = new ArrayList<>();

    @OneToMany(mappedBy = "scenario")
    private List<ScenarioPassLog> logList = new ArrayList<>();

    private String title; // 시나리오 제목
    private String content; // 시나리오 내용
    private int stage_count; // 스테이지 개수



    //== 비즈니스 로직 ==//
    public void increase_stage_count() {
        this.stage_count += 1;
    }

    public void decrease_stage_count() {
        int recent_count = this.stage_count - 1;
        if (recent_count < 0) {
            throw new NoMoreStageException("There's no more stages");
        }
        this.stage_count = recent_count;
    }
}
