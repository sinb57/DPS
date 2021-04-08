package com.project.dps.domain.log;

import com.project.dps.domain.Member;
import com.project.dps.domain.Scenario;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ScenarioPassLog {

    @Id @GeneratedValue
    @Column(name = "scenario_pass_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "scenario_id")
    private Scenario scenario;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime localDateTime;
}
