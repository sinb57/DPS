package com.project.dps.domain.log;

import com.project.dps.domain.member.Member;
import com.project.dps.domain.scenario.stage.poc.ValidResultEnum;
import com.project.dps.domain.scenario.stage.Stage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stage_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StageLog {

    @Id @GeneratedValue
    @Column(name = "stage_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "stageLog")
    @Builder.Default
    private List<PocLog> pocLogList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ValidResultEnum result;

    private LocalDateTime createTime;


    //== 연관관계 메서드 ==//
    private void setStage (Stage stage) {
        this.stage = stage;
        stage.getStageLogList().add(this);
    }

    private void setMember (Member member) {
        this.member = member;
        member.getStageLogList().add(this);
    }


    //== Builder 메서드 ==//
    @Builder
    public StageLog(Stage stage, Member member) {
        this.createTime = LocalDateTime.now();

        // 연관관계
        this.stage = stage;
        stage.appendStageLog(this);
        this.member = member;
        member.appendStageLog(this);
    }


    //== 비즈니스 로직 ==//
    public void appendPocLog(PocLog pocLog) {
        this.pocLogList.add(pocLog);
    }

    public void removePocLog(PocLog pocLog) {
        this.pocLogList.remove(pocLog);
    }


    // setter 메서드
    public void setResult (ValidResultEnum result) {
        this.result = result;
    }
}

