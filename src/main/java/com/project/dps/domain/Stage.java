package com.project.dps.domain;

import com.project.dps.domain.log.StagePassLog;
import com.project.dps.domain.poc.Poc;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stage {

    @Id @GeneratedValue
    @Column(name = "stage_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "scenario_id")
    private Scenario scenario;

    @OneToMany(mappedBy = "stage")
    private List<Poc> pocList = new ArrayList<>();

    @OneToMany(mappedBy = "stage")
    private List<StagePassLog> logList = new ArrayList<>();

    @OneToMany(mappedBy = "stage")
    private List<CheckItem> checkItemList = new ArrayList<>(); // 기능 구성 요소

    private Long no; // 스테이지 번호
    private String title; // 스테이지 제목
    private String comment;
    private String content; // 스테이지 내용
    private LocalDateTime createTime; // 생성 날짜


    // 연관관계 메서드
    private void setScenario(Scenario scenario) {
        this.scenario = scenario;
        scenario.getStageList().add(this);
    }

    // 생성자 메서드
    @Builder
    public Stage(Scenario scenario, Long no, String title, String comment, String content,
                 List<CheckItem> checkItemList) {
        this.setScenario(scenario);
        this.no = no;
        this.title = title;
        this.comment = comment;
        this.content = content;
        this.createTime = LocalDateTime.now();
        this.checkItemList = checkItemList;
    }


    //== 비즈니스 로직 ==//
    public void removeStage() {
        scenario.decrease_stage_count();

    }
}
