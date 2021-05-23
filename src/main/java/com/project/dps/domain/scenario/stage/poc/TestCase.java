package com.project.dps.domain.scenario.stage.poc;

import com.project.dps.domain.log.TestCaseLog;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "poc_case")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestCase {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poc_case_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "poc_common_id")
    private TestCommon testCommon;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "poc_scenario_id")
    private TestScenario testScenario;

    @OneToMany(mappedBy = "testCase")
    private List<TestCaseLog> testCaseLogList = new ArrayList<>();

    private String content; // POC 코드


    //== Builder 메서드 ==//
    @Builder
    public TestCase(TestCommon testCommon, TestScenario testScenario, String content) {
        this.content = content;

        // 연관관계 로직
        this.testCommon = testCommon;
        testCommon.appendTestCaseList(this);
        this.testScenario = testScenario;
        testScenario.appendTestCase(this);
    }


    //== 비즈니스 로직 ==//
    public void appendPocLog(TestCaseLog testCaseLog) {
        this.testCaseLogList.add(testCaseLog);
    }

    public void removePocLog(TestCaseLog testCaseLog) {
        this.testCaseLogList.remove(testCaseLog);
    }

    public void edit(String content) {
        this.content = content;
    }
}
