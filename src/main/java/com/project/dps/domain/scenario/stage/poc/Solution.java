package com.project.dps.domain.scenario.stage.poc;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "poc_solution")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Solution {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solution_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "test_category_id")
    private TestScenario testScenario;

    private String title; // solution 제목
    private String reason; // 발생 원인
    private String manual; // 패치 방법
    private String source; // 참고문헌, 출처


    //== Builder 메서드 ==//
    @Builder
    public Solution(TestScenario testScenario, String title, String reason, String manual, String source) {
        this.title = title;
        this.reason = reason;
        this.manual = manual;
        this.source = source;

        // 연관관계 로직
        this.testScenario = testScenario;
        testScenario.setSolution(this);
    }

    //== 비즈니스 로직 ==//
    public void edit(String title, String reason, String manual, String source) {
        this.title = title;
        this.reason = reason;
        this.manual = manual;
        this.source = source;
    }
}
