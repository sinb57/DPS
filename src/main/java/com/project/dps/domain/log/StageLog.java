package com.project.dps.domain.log;

import com.project.dps.domain.member.Member;
import com.project.dps.domain.scenario.stage.poc.ValidResultEnum;
import com.project.dps.domain.scenario.stage.Stage;
import com.project.dps.domain.scenario.stage.poc.ValidTypeEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stage_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StageLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stage_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "stageLog")
    private List<TestCategoryLog> testCategoryLogList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ValidResultEnum result = ValidResultEnum.FAIL;

    private LocalDateTime createTime;


    //== Builder 메서드 ==//
    @Builder
    public StageLog(Stage stage, Member member, ValidResultEnum result) {
        this.result = result;
        this.createTime = LocalDateTime.now();

        // 연관관계
        this.stage = stage;
        stage.appendStageLog(this);
        this.member = member;
        member.appendStageLog(this);
    }

    //== 비즈니스 로직 ==//
    public void appendTestCategoryLog(TestCategoryLog testCategoryLog) {
        this.testCategoryLogList.add(testCategoryLog);
    }

    public void removeTestCategoryLog(TestCategoryLog testCategoryLog) {
        this.testCategoryLogList.remove(testCategoryLog);
    }


    // setter 메서드
    public void makeItPass (ValidResultEnum result) {
        this.result = result;
    }
}

