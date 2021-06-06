package com.project.dps.domain.scenario.stage.poc;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "poc_solution")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Solution {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solution_id")
    private Long id;

    @OneToMany(mappedBy = "solution")
    private List<TestScenario> testScenarioList = new ArrayList<>();

    private String title; // solution 제목

    @Column(length = 2000)
    private String reason; // 발생 원인

    @Column(length = 2000)
    private String manual; // 패치 방법

    @Column(length = 2000)
    private String source; // 참고문헌, 출처


    //== Builder 메서드 ==//
    @Builder
    public Solution(String title, String reason, String manual, String source) {
        this.title = title;
        this.reason = reason;
        this.manual = manual;
        this.source = source;

    }

    //== 비즈니스 로직 ==//
    public void appendTestScenario(TestScenario testScenario) {
        testScenarioList.add(testScenario);
    }

    public void removeTestScenario(TestScenario testScenario) {
        testScenarioList.remove(testScenario);
    }

    public void edit(String title, String reason, String manual, String source) {
        this.title = title;
        this.reason = reason;
        this.manual = manual;
        this.source = source;
    }
}
