package com.project.dps.domain.scenario.stage.check;

import com.project.dps.domain.scenario.stage.check.result.Result;
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
    private List<Request> requestList = new ArrayList<>();

    @OneToMany(mappedBy = "subject")
    private List<Result> resultList = new ArrayList<>();

    private String title;   // 제목


    //== Builder 메서드 ==//
    @Builder
    public Subject(Stage stage, String title, Group group,
                   List<Request> requestList, List<Result> resultList) {
        this.title = title;
        this.group = group;
        this.requestList = requestList;
        this.resultList = resultList;

        // 연관관계 로직
        this.stage = stage;
        stage.appendSubject(this);
    }

    //== 비즈니스 로직 ==//
    public void appendRequest(Request request) {
        this.requestList.add(request);
    }

    public void removeRequest(Request request) {
        this.requestList.remove(request);
    }

    public void appendResult(Result result) {
        this.resultList.add(result);
    }

    public void removeResult(Result result) {
        this.resultList.remove(result);
    }


    //== Setter 메서드 ==//
    public void setGroup(Group group) {
        this.group = group;
    }

}
