package com.project.dps.domain.log;

import com.project.dps.domain.scenario.stage.poc.ValidResultEnum;
import com.project.dps.domain.scenario.stage.poc.TestCase;
import com.project.dps.domain.scenario.stage.poc.ValidTypeEnum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "poc_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PocLog {

    @Id @GeneratedValue
    @Column(name = "poc_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stage_log_id")
    private StageLog stageLog;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "poc_case_id")
    private TestCase testCase;

    @Enumerated(EnumType.STRING)
    private ValidResultEnum result; // PASS, FAIL

    @Enumerated(EnumType.STRING)
    private ValidTypeEnum type; // FUNCTIONAL, SECURE

    private String category;


    //== 연관관계 메서드 ==//
    private void setStageLog(StageLog stageLog) {
        this.stageLog = stageLog;
        this.stageLog.appendPocLog(this);
    }

    private void setTestCase(TestCase testCase) {
        this.testCase = testCase;
        this.testCase.appendPocLog(this);

    }


    //== Builder 메서드 ==//
    @Builder
    public PocLog(StageLog stageLog, TestCase testCase, String category) {
        this.category = category;

        // 연관관계 로직
        this.stageLog = stageLog;
        stageLog.appendPocLog(this);
        this.testCase = testCase;
        testCase.appendPocLog(this);

        this.type = testCase.getTestCommon().getTestScenario().getType();
    }

    //== Setter 메서드 ==//
    public void setResult(ValidResultEnum result) {
        this.result = result;
    }
}
