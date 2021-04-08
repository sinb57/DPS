package com.project.dps.domain.log;

import com.project.dps.domain.Member;
import com.project.dps.domain.Stage;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class StagePassLog {

    @Id @GeneratedValue
    @Column(name = "stage_pass_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime localDateTime;
}

