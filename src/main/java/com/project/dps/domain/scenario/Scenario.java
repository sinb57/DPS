package com.project.dps.domain.scenario;

import com.project.dps.domain.log.ScenarioLog;
import com.project.dps.domain.log.StageLog;
import com.project.dps.domain.scenario.stage.Stage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "scenario")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Scenario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scenario_id")
    private Long id;

    @OneToMany(mappedBy = "scenario")
    private List<Stage> stageList = new ArrayList<>();

    @OneToMany(mappedBy = "scenario")
    private List<ScenarioLog> scenarioLogList = new ArrayList<>();

    @OneToMany(mappedBy = "scenario")
    private List<StageLog> stageLogList = new ArrayList<>();

    private String title;       // 시나리오 제목
    private String subTitle;    // 시나리오 부제목
    private String content;     // 시나리오 내용
    private int stageCount = 0; // 스테이지 개수


    //== Builder 메서드 ==//
    @Builder
    public Scenario(String title, String subTitle, String content, List<Stage> stageList,
                    List<ScenarioLog> scenarioLogList, List<StageLog> stageLogList) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.stageList = stageList;
        this.stageCount = stageList.size();
    }


    //== 비즈니스 로직 ==//
    public void appendStage(Stage stage) {
        this.stageList.add(stage);
        this.stageCount++;
    }

    public void removeStage(Stage stage) {
        if (this.stageList.remove(stage)) {
            this.stageCount--;
        }
    }

    public void appendScenarioLog(ScenarioLog scenarioLog) {
        this.scenarioLogList.add(scenarioLog);
    }

    public void removeScenarioLog(ScenarioLog scenarioLog) {
        this.scenarioLogList.remove(scenarioLog);
    }

    public void appendStageLog(StageLog stageLog) {
        this.stageLogList.add(stageLog);
    }

    public void removeStageLog(StageLog stageLog) {
        this.stageLogList.remove(stageLog);
    }

    public void edit(String title, String subTitle, String content) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
    }

}
