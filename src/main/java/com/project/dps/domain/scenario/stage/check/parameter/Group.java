package com.project.dps.domain.scenario.stage.check.parameter;

import com.project.dps.domain.scenario.stage.check.Subject;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parameter_group")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Group {

    @Id @GeneratedValue
    @Column(name = "group_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Enumerated(EnumType.STRING)
    private MethodTypeEnum type;

    @OneToMany(mappedBy = "group")
    @Builder.Default
    private List<Parameter> parameterList = new ArrayList<>();


    //== Builder 메서드 ==//
    @Builder
    public Group(Subject subject, MethodTypeEnum type, List<Parameter> parameterList) {
        this.type = type;

        // 연관관계 로직
        this.subject = subject;
        subject.setGroup(this);
    }


    //== 비즈니스 로직 ==//
    public void appendParameter(Parameter parameter) {
        this.parameterList.add(parameter);
    }

    public void removeParameter(Parameter parameter) {
        this.parameterList.remove(parameter);
    }

    public void edit(MethodTypeEnum type) {
        this.type = type;
    }
}
