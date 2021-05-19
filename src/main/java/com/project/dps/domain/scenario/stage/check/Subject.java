package com.project.dps.domain.scenario.stage.check;

import com.project.dps.domain.scenario.stage.poc.TestScenario;
import com.project.dps.domain.scenario.stage.Stage;
import com.project.dps.domain.scenario.stage.check.parameter.Group;
import com.project.dps.domain.scenario.stage.check.request.Request;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subject")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subject {

    @Id @GeneratedValue
    @Column(name = "subject_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @OneToOne(mappedBy = "subject")
    private Group group;

    @OneToMany(mappedBy = "subject")
    private List<TestScenario> testScenarioList = new ArrayList<>();

    @OneToMany(mappedBy = "subject")
    private List<Request> requestList = new ArrayList<>();

    private String title;   // 제목
    private String result;  // 수행 결과


    //== Builder 메서드 ==//
    @Builder
    public Subject(Stage stage, String title, String result, Group group,
                   List<TestScenario> testScenarioList, List<Request> requestList) {
        this.title = title;
        this.result = result;
        this.group = group;
        this.testScenarioList = testScenarioList;
        this.requestList = requestList;

        // 연관관계 로직
        this.stage = stage;
        stage.appendSubject(this);
    }

    //== 비즈니스 로직 ==//
    public void appendTestScenario(TestScenario testScenario) {
        this.testScenarioList.add(testScenario);
    }

    public void removeTestScenario(TestScenario testScenario) {
        this.testScenarioList.remove(testScenario);
    }

    public void appendRequest(Request request) {
        this.requestList.add(request);
    }

    public void removeRequest(Request request) {
        this.requestList.remove(request);
    }


    //== Setter 메서드 ==//
    public void setGroup(Group group) {
        this.group = group;
    }

}
