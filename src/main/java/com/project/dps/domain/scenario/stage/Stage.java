package com.project.dps.domain.scenario.stage;

import com.project.dps.domain.log.StageLog;
import com.project.dps.domain.scenario.Scenario;
import com.project.dps.domain.scenario.stage.check.Subject;
import com.project.dps.domain.scenario.stage.poc.TestScenario;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stage")
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
    private List<StageLog> stageLogList = new ArrayList<>(); // 채점 로그

    @OneToMany(mappedBy = "stage")
    private List<Subject> subjectList = new ArrayList<>(); // 스테이지 구성 요소

    @OneToMany(mappedBy = "stage")
    private List<TestScenario> testScenarioList = new ArrayList<>(); // 스테이지 점검 POC

    private String title;   // 스테이지 제목
    private Long no;        // 스테이지 번호


    //== Builder 메서드 ==//
    @Builder
    public Stage(Scenario scenario, Long no, String title, List<StageLog> stageLogList,
                 List<Subject> subjectList, List<TestScenario> testScenarioList) {
        this.no = no;
        this.title = title;
        this.stageLogList = stageLogList;
        this.subjectList = subjectList;
        this.testScenarioList = testScenarioList;

        // 연관관계 로직
        this.scenario = scenario;
        scenario.appendStage(this);
    }


    //== 비즈니스 로직 ==//
    public void appendStageLog(StageLog stageLog) {
        this.stageLogList.add(stageLog);
    }

    public void removeStageLog(StageLog stageLog) {
        this.stageLogList.remove(stageLog);
    }

    public void appendSubject(Subject subject) {
        this.subjectList.add(subject);
    }

    public void removeSubject(Subject subject) {
        this.subjectList.remove(subject);
    }

    public void appendTestScenario(TestScenario testScenario) {
        this.testScenarioList.add(testScenario);
    }

    public void removeTestScenario(TestScenario testScenario) {
        this.testScenarioList.remove(testScenario);
    }

    public void edit(String title) {
        this.title = title;
    }
}
