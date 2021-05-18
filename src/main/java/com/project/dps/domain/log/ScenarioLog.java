package com.project.dps.domain.log;

import com.project.dps.domain.member.Member;
import com.project.dps.domain.scenario.Scenario;
import com.project.dps.domain.scenario.stage.poc.ValidResultEnum;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScenarioLog {

    @Id @GeneratedValue
    @Column(name = "scenario_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "scenario_id")
    private Scenario scenario;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private ValidResultEnum result;

    private LocalDateTime localDateTime;


    //== Builder 메서드 ==//
    @Builder
    public ScenarioLog(Scenario scenario, Member member, ValidResultEnum result) {
        this.result = result;
        this.localDateTime = LocalDateTime.now();

        // 연관관계 로직
        this.scenario = scenario;
        scenario.appendScenarioLog(this);
        this.member = member;
        member.appendScenarioLog(this);

    }
}
