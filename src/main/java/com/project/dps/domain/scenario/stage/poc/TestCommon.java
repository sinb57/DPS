package com.project.dps.domain.scenario.stage.poc;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "poc_common")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestCommon {

    @Id @GeneratedValue
    @Column(name = "poc_common_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "poc_scenario_id")
    private TestScenario testScenario;

    @OneToMany(mappedBy = "testCommon")
    @Builder.Default
    private List<TestCase> testCaseList = new ArrayList<>();

    private String content; // POC 공통 분모


    //== Builder 메서드 ==//
    @Builder
    public TestCommon(TestScenario testScenario, List<TestCase> testCaseList, String content) {
        this.testCaseList = testCaseList;
        this.content = content;

        // 연관관계 로직
        this.testScenario = testScenario;
        testScenario.appendTestCommon(this);
    }


    //== 비즈니스 로직 ==//
    public void appendTestCaseList(TestCase testCase) {
        this.testCaseList.add(testCase);
    }

    public void removeTestCaseList(TestCase testCase) {
        this.testCaseList.remove(testCase);
    }

    public void edit(String content) {
         this.content = content;
    }
}
