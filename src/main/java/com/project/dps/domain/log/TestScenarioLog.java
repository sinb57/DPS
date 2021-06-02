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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poc_scenario_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "poc_category_log_id")
    private TestCategoryLog testCategoryLog;

    @OneToMany(mappedBy = "testScenarioLog")
    private List<TestCaseLog> testCaseLogList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "poc_scenario_id")
    private TestScenario testScenario;

    @Enumerated(EnumType.STRING)
    private ValidResultEnum result = ValidResultEnum.FAIL;

    private int testCaseCount;

    private int testCasePassCount;


    //== Builder 메서드 ==//
    @Builder
    public TestScenarioLog(TestCategoryLog testCategoryLog, TestScenario testScenario, ValidResultEnum result) {
        this.result = result;

        // 연관관계 로직
        this.testCategoryLog = testCategoryLog;
        testCategoryLog.appendTestScenarioLog(this);
        this.testScenario = testScenario;
        testScenario.appendTestScenarioLog(this);
    }

    //== 비즈니스 로직 ==//
    public void appendTestCaseLog(TestCaseLog testCaseLog) {
        this.testCaseLogList.add(testCaseLog);
        this.testCaseCount++;
        if (testCaseLog.getResult() == ValidResultEnum.PASS) {
            this.testCasePassCount++;
        }
        this.checkResult();
    }

    public void removeTestCaseLog(TestCaseLog testCaseLog) {
        this.testCaseLogList.remove(testCaseLog);
        this.testCaseCount--;
        if (testCaseLog.getResult() == ValidResultEnum.PASS) {
            this.testCasePassCount--;
        }
        this.checkResult();
    }

    private void checkResult() {
        if (testCaseCount == testCasePassCount) {
            if (result == ValidResultEnum.FAIL) {
                this.result = ValidResultEnum.PASS;
                testCategoryLog.increaseTestScenarioPassCount();
            }
        }
        else {
            if (result == ValidResultEnum.PASS) {
                this.result = ValidResultEnum.FAIL;
                testCategoryLog.decreaseTestScenarioPassCount();
            }
        }
    }
}
