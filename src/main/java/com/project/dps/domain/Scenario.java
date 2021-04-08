package com.project.dps.domain;

import com.project.dps.domain.log.ScenarioPassLog;
import com.project.dps.exception.NoMoreStageException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private int stageCount; // 스테이지 개수
    private LocalDateTime createTime; // 생성 날짜


    // 생성자 메서드
    public Scenario(String title, String content, Stage... stages) {
        this.title = title;
        this.content = content;
        this.createTime = LocalDateTime.now();

        for(Stage stage: stages) {
            this.stageList.add(stage);
        }
        this.stageCount = stages.length;
    }


    //== 비즈니스 로직 ==//
    public void increase_stage_count() {
        this.stageCount += 1;
    }

    public void decrease_stage_count() {
        int recent_count = this.stageCount - 1;
        if (recent_count < 0) {
            throw new NoMoreStageException("There's no more stages");
        }
        this.stageCount = recent_count;
    }
}
