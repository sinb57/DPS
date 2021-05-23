package com.project.dps.domain.scenario.stage.check.request;

import com.project.dps.domain.scenario.stage.check.Subject;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "request")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Request {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private String content; // 요구사항


    //== Builder 메서드 ==//
    @Builder
    public Request(Subject subject, String content) {
        this.content = content;

        // 연관관계 로직
        this.subject = subject;
        subject.appendRequest(this);
    }


    //== 비즈니스 로직 ==//
    public void edit(String content) {
        this.content = content;
    }
}
