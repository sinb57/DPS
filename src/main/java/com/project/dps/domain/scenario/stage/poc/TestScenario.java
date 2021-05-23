package com.project.dps.domain.scenario.stage.poc;

import com.project.dps.domain.log.TestCaseLog;
import com.project.dps.domain.log.TestScenarioLog;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poc_scenario_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "poc_category_id")
    private TestCategory testCategory;

    @OneToMany(mappedBy = "testScenario")
    private List<TestCase> testCaseList = new ArrayList<>();

    @OneToMany(mappedBy = "testScenario")
    private List<TestScenarioLog> testScenarioLogList = new ArrayList<>();

    @OneToOne(mappedBy = "testScenario")
    private Solution solution;

    private String content; // 검사 항목 내용


    //== Builder 메서드 ==//
    @Builder
    public TestScenario(TestCategory testCategory, Solution solution, String content) {
        this.solution = solution;
        this.content = content;

        // 연관관계 로직
        this.testCategory = testCategory;
        testCategory.appendTestScenario(this);
    }

    //== 비즈니스 로직 ==//
    public void appendTestCase(TestCase testCase) {
        this.testCaseList.add(testCase);
    }

    public void removeTestCase(TestCase testCase) {
        this.testCaseList.remove(testCase);
    }

    public void appendTestScenarioLog(TestScenarioLog testScenarioLog) {
        this.testScenarioLogList.add(testScenarioLog);
    }

    public void removeTestScenarioLog(TestScenarioLog testScenarioLog) {
        this.testScenarioLogList.remove(testScenarioLog);
    }

    //== setter 메서드 ==//
    public void setSolution(Solution solution) {
        this.solution = solution;
    }

}
