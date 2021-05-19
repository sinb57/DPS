package com.project.dps.domain.scenario.stage.poc;

import com.project.dps.domain.log.TestScenarioLog;
import com.project.dps.domain.scenario.stage.Stage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "poc_scenario")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestScenario {

    @Id @GeneratedValue
    @Column(name = "poc_scenario_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @OneToMany(mappedBy = "testScenario")
    private List<TestCommon> testCommonList = new ArrayList<>();

    @OneToMany(mappedBy = "testScenario")
    private List<TestScenarioLog> testScenarioLogList;

    @OneToOne(mappedBy = "testScenario")
    private Solution solution;

    @Enumerated(EnumType.STRING)
    private ValidTypeEnum type;

    private String content;


    //== Builder 메서드 ==//
    @Builder
    public TestScenario(Stage stage, ValidTypeEnum type, Solution solution) {
        this.type = type;
        this.solution = solution;

        // 연관관계 로직
        this.stage = stage;
        stage.appendTestScenario(this);

        this.content = stage.getTitle();
    }

    //== 비즈니스 로직 ==//
    public void appendTestCommon(TestCommon testCommon) {
        this.testCommonList.add(testCommon);
    }

    public void removeTestCommon(TestCommon testCommon) {
        this.testCommonList.remove(testCommon);
    }

    public void appendTestScenarioLog(TestScenarioLog testScenarioLog) {
        this.testScenarioLogList.add(testScenarioLog);
    }

    public void removeTestScenarioLog(TestScenarioLog testScenarioLog) {
        this.testScenarioLogList.remove(testScenarioLog);
    }

    public void edit(ValidTypeEnum type, String content) {
        this.type = type;
        this.content = content;
    }


    //== setter 메서드 ==//
    public void setSolution(Solution solution) {
        this.solution = solution;
    }

}
