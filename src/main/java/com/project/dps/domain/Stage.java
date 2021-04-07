package com.project.dps.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Stage {

    @Id @GeneratedValue
    @Column(name = "stage_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Scenario scenario;

    private Long no; // 스테이지 번호
    private String content; // 스테이지 내용
}
