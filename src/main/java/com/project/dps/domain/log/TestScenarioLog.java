package com.project.dps.domain.log;

import com.project.dps.domain.scenario.stage.poc.TestScenario;
import com.project.dps.domain.scenario.stage.poc.ValidResultEnum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "poc_scenario_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestScenarioLog {

    @Id @GeneratedValue
    @Column(name = "poc_scenario_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stage_log_id")
    private StageLog stageLog;

    @OneToMany(mappedBy = "testScenarioLog")
    private List<TestCaseLog> testCaseLogList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "poc_scenario_id")
    private TestScenario testScenario;

    @Enumerated(EnumType.STRING)
    private ValidResultEnum result; // PASS, FAIL



    //== Builder 메서드 ==//
    @Builder
    public TestScenarioLog(StageLog stageLog, TestScenario testScenario, ValidResultEnum result) {
        this.result = result;

        // 연관관계 로직
        this.stageLog = stageLog;
        stageLog.appendTestScenarioLog(this);
        this.testScenario = testScenario;
        testScenario.appendTestScenarioLog(this);
    }

    //== 비즈니스 로직 ==//
    public void appendTestCaseLog(TestCaseLog testCaseLog) {
        this.testCaseLogList.add(testCaseLog);
    }

    public void removeTestCaseLog(TestCaseLog testCaseLog) {
        this.testCaseLogList.remove(testCaseLog);
    }


    //== Setter 메서드 ==//
    public void setResult(ValidResultEnum result) {
        this.result = result;
    }
}
