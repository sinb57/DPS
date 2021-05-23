package com.project.dps.domain.scenario.stage.check.parameter;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "parameter")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Parameter {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parameter_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Group group;

    private String name;
    private String value;


    //== Builder 메서드 ==//
    @Builder
    public Parameter(Group group, String name, String value) {
        this.name = name;
        this.value = value;

        // 연관관계 로직
        this.group = group;
        group.appendParameter(this);
    }

    //== 비즈니스 로직 ==//
    public void changeContent(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public void edit(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
