package com.project.dps.domain.scenario.stage;

import com.project.dps.domain.log.StageLog;
import com.project.dps.domain.scenario.Scenario;
import com.project.dps.domain.scenario.stage.check.Subject;
import com.project.dps.domain.scenario.stage.poc.TestCategory;
import com.project.dps.domain.scenario.stage.poc.TestCommon;
import com.project.dps.domain.scenario.stage.poc.TestScenario;
import com.project.dps.domain.scenario.stage.poc.ValidTypeEnum;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stage_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "scenario_id")
    private Scenario scenario;

    @OneToMany(mappedBy = "stage")
    private List<StageLog> stageLogList = new ArrayList<>(); // 채점 로그

    @OneToOne(mappedBy = "stage")
    private TestCommon testCommon;

    @OneToMany(mappedBy = "stage")
    private List<Subject> subjectList = new ArrayList<>(); // 스테이지 구성 요소

    @OneToMany(mappedBy = "stage")
    private List<TestCategory> testCategoryList = new ArrayList<>() {{}}; // 스테이지 점검 POC

    private String title;   // 스테이지 제목
    private Long no;        // 스테이지 번호


    //== Builder 메서드 ==//
    @Builder
    public Stage(Scenario scenario, Long no, String title) {
        this.no = no;
        this.title = title;

        // 연관관계 로직
        this.scenario = scenario;
        scenario.appendStage(this);

        this.testCategoryList = new ArrayList<>() {{
            add(TestCategory.builder().type(ValidTypeEnum.FUNCTION).build());
            add(TestCategory.builder().type(ValidTypeEnum.SECURITY).build());
        }};
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

    public void edit(String title) {
        this.title = title;
    }


    //== Setter 메서드 ==//
    public void setTestCommon(TestCommon testCommon) {
        this.testCommon = testCommon;
    }
}
