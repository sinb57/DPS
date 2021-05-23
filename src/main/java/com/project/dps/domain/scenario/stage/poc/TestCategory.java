package com.project.dps.domain.scenario.stage.poc;

import com.project.dps.domain.scenario.stage.Stage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "poc_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestCategory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "poc_category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @OneToMany(mappedBy = "testCategory")
    private List<TestScenario> testScenarioList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ValidTypeEnum type;


    //== Builder 메서드 ==//
    @Builder
    public TestCategory(Stage stage, ValidTypeEnum type) {
        this.type = type;

        // 연관관계 로직
        this.stage = stage;
    }

    //== 비즈니스 로직 ==//
    public void appendTestScenario(TestScenario testScenario) {
        this.testScenarioList.add(testScenario);
    }

    public void removeTestScenario(TestScenario testScenario) {
        this.testScenarioList.remove(testScenario);
    }


}
