package com.project.dps.domain.poc;

import com.project.dps.domain.Stage;
import com.project.dps.domain.log.poc.PocLog;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Getter
public abstract class Poc {

    @Id @GeneratedValue
    @Column(name = "poc_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @OneToMany(mappedBy = "poc")
    private List<PocLog> logList = new ArrayList<>();


    // 연관관계 메서드
    public void setStage(Stage stage) {
        this.stage = stage;
        stage.getPocList().add(this);
    }
}
