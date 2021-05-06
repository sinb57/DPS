package com.project.dps.domain.log;

import com.project.dps.domain.poc.PocResultEnum;
import com.project.dps.domain.poc.PocTestCase;
import com.project.dps.domain.poc.PocTypeEnum;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PocTestCaseLog {

    @Id @GeneratedValue
    @Column(name = "poc_test_case_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stage_log_id")
    private StageLog stageLog;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "poc_test_case_id")
    private PocTestCase pocTestCase;

    @Enumerated(EnumType.STRING)
    private PocResultEnum result; // PASS, FAIL

    private PocTypeEnum type; // FUNCTIONAL, SECURE

    private String category;



    // 연관관계 메서드
    private void setStageLog(StageLog stageLog) {
        this.stageLog = stageLog;
        stageLog.getPocTestCaseLogList().add(this);
    }

    private void setPocTestCase(PocTestCase pocTestCase) {
        this.pocTestCase = pocTestCase;
        pocTestCase.getPocTestCaseLogList().add(this);
    }


    // 생성자 메서드
    public PocTestCaseLog(StageLog stageLog, PocTestCase pocTestCase, PocResultEnum result, PocTypeEnum type, String category) {
        this.setStageLog(stageLog);
        this.setPocTestCase(pocTestCase);
        this.result = result;
        this.type = type;
        this.category = category;
    }
}
