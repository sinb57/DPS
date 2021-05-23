package com.project.dps.domain.member;

import com.project.dps.domain.log.ScenarioLog;
import com.project.dps.domain.log.StageLog;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;

    private String password;

    @OneToMany(mappedBy = "member")
    private List<StageLog> stageLogList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ScenarioLog> scenarioLogs = new ArrayList<>();


    //== Builder 메서드 ==//
    @Builder
    public Member(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }


    //== 비즈니스 로직 ==//
    public void appendStageLog(StageLog stageLog) {
        this.stageLogList.add(stageLog);
    }

    public void appendScenarioLog(ScenarioLog scenarioLog) {
        this.scenarioLogs.add(scenarioLog);
    }

    public void edit(String name, String password) {
        this.name = name;
        this.password = password;
    }
}