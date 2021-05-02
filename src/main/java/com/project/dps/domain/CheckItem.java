package com.project.dps.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckItem {

    @Id @GeneratedValue
    @Column(name = "checkItem_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    private String content;

    // 연관관계 메서드
    private void setStage(Stage stage) {
        this.stage = stage;
        stage.getCheckItemList().add(this);
    }

    // 생성자 메서드
    @Builder
    public CheckItem(Stage stage, String content) {
        this.setStage(stage);
        this.content = content;
    }
}
