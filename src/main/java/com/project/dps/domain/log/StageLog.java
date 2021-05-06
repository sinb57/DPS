package com.project.dps.domain.log;

import com.project.dps.domain.Member;
import com.project.dps.domain.Stage;
import com.project.dps.domain.poc.PocResultEnum;
import com.project.dps.domain.poc.PocTestCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StageLog {

    @Id @GeneratedValue
    @Column(name = "stage_pass_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "stageLog")
    private List<PocTestCaseLog> pocTestCaseLogList = new ArrayList<>();

    private PocResultEnum result;

    private LocalDateTime createTime;

    // 연관관계 메서드
    private void setStage (Stage stage) {
        this.stage = stage;
        stage.getStageLogList().add(this);
    }

    private void setMember (Member member) {
        this.member = member;
        member.getStageLogList().add(this);
    }

    public StageLog(Stage stage, Member member, PocResultEnum result,
                    List<PocTestCaseLog> pocTestCaseLogList) {
        this.setStage(stage);
        this.setMember(member);
        this.result = result;
        createTime = LocalDateTime.now();
        this.pocTestCaseLogList = pocTestCaseLogList;
    }
}

