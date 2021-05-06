package com.project.dps.domain.poc;

import com.project.dps.domain.Stage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PocTestCategory {

    @Id
    @GeneratedValue
    @Column(name = "poc_test_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @OneToMany(mappedBy = "pocTestCategory")
    private List<PocTestFunc> pocTestFuncList = new ArrayList<>();

    @OneToOne(mappedBy = "pocTestCategory")
    private PocSolution pocSolution;

    private PocTypeEnum type;

    private String category;


    // 연관관계 메서드
    private void setStage(Stage stage) {
        this.stage = stage;
        stage.getPocTestCategoryList().add(this);
    }

    // 생성자 메서드
    public PocTestCategory(Stage stage, PocTypeEnum type, String category,
                           PocSolution pocSolution, List<PocTestFunc> pocTestFuncList) {
        this.setStage(stage);
        this.type = type;
        this.category =  category;
        this.pocSolution = pocSolution;
        this.pocTestFuncList = pocTestFuncList;
    }

    // setter 메서드
    public void setPocSolution (PocSolution pocSolution) {
        this.pocSolution = pocSolution;
    }
}
