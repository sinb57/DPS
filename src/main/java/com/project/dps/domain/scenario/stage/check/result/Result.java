package com.project.dps.domain.scenario.stage.check.result;

import com.project.dps.domain.scenario.stage.check.Subject;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "result")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Result {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private String content; // 수행 결과


    //== Builder 메서드 ==//
    @Builder
    public Result(Subject subject, String content) {
        this.content = content;

        // 연관관계 로직
        this.subject = subject;
        subject.appendResult(this);
    }


    //== 비즈니스 로직 ==//
    public void edit(String content) {
        this.content = content;
    }
}
