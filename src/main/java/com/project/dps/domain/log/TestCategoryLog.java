package com.project.dps.domain.log;

import com.project.dps.domain.scenario.stage.poc.ValidResultEnum;
import com.project.dps.domain.scenario.stage.poc.ValidTypeEnum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "poc_category_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestCategoryLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poc_category_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stage_log_id")
    private StageLog stageLog;

    @OneToMany(mappedBy = "testCategoryLog")
    private List<TestScenarioLog> testScenarioLogList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ValidResultEnum result = ValidResultEnum.FAIL;

    @Enumerated(EnumType.STRING)
    private ValidTypeEnum type;



    //== Builder 메서드 ==//
    @Builder
    public TestCategoryLog(StageLog stageLog, ValidTypeEnum type, ValidResultEnum result) {
        this.type = type;
        this.result = result;

        // 연관관계 로직
        this.stageLog = stageLog;
        stageLog.appendTestCategoryLog(this);
    }



    //== 비즈니스 로직 ==//
    public void appendTestScenarioLog(TestScenarioLog testScenarioLog) {
        this.testScenarioLogList.add(testScenarioLog);
    }

    public void removeTestScenarioLog(TestScenarioLog testScenarioLog) {
        this.testScenarioLogList.remove(testScenarioLog);
    }



    //== Setter 메서드 ==//
    public void makeItPass (ValidResultEnum result) {
        this.result = result;
    }
}
