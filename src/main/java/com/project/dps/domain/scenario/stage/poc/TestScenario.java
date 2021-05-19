package com.project.dps.domain.scenario.stage.poc;

import com.project.dps.domain.scenario.stage.check.Subject;
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

    @Id @GeneratedValue
    @Column(name = "poc_scenario_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "testScenario")
    private List<TestCommon> testCommonList = new ArrayList<>();

    @OneToOne(mappedBy = "testScenario")
    private Solution solution;

    @Enumerated(EnumType.STRING)
    private ValidTypeEnum type;

    private String content;


    //== Builder 메서드 ==//
    @Builder
    public TestScenario(Subject subject, ValidTypeEnum type, String content, Solution solution) {
        this.type = type;
        this.content = content;
        this.solution = solution;

        // 연관관계 로직
        this.subject = subject;
        subject.appendTestScenario(this);
    }

    //== 비즈니스 로직 ==//
    public void appendTestCommon(TestCommon testCommon) {
        this.testCommonList.add(testCommon);
    }

    public void removeTestCommon(TestCommon testCommon) {
        this.testCommonList.remove(testCommon);
    }

    public void edit(ValidTypeEnum type, String content) {
        this.type = type;
        this.content = content;
    }


    //== setter 메서드 ==//
    public void setSolution(Solution solution) {
        this.solution = solution;
    }

}
