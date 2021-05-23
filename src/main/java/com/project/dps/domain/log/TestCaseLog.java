package com.project.dps.domain.log;

import com.project.dps.domain.scenario.stage.poc.ValidResultEnum;
import com.project.dps.domain.scenario.stage.poc.TestCase;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "poc_case_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestCaseLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poc_case_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "poc_scenario_log_id")
    private TestScenarioLog testScenarioLog;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "poc_case_id")
    private TestCase testCase;

    @Enumerated(EnumType.STRING)
    private ValidResultEnum result = ValidResultEnum.FAIL;


    //== Builder 메서드 ==//
    @Builder
    public TestCaseLog(TestScenarioLog testScenarioLog, TestCase testCase, ValidResultEnum result) {
        this.result = result;

        // 연관관계 로직
        this.testScenarioLog = testScenarioLog;
        testScenarioLog.appendTestCaseLog(this);
        this.testCase = testCase;
        testCase.appendPocLog(this);
    }

    //== Setter 메서드 ==//
    public void makeItPass (ValidResultEnum result) {
        this.result = result;
    }
}
