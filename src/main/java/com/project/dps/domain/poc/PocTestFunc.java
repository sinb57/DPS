package com.project.dps.domain.poc;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PocTestFunc {

    @Id @GeneratedValue
    @Column(name = "poc_test_func_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "poc_test_category_id")
    private PocTestCategory pocTestCategory;

    @OneToMany(mappedBy = "pocTestFunc")
    private List<PocTestCase> pocTestCaseList = new ArrayList<>();

    private String content; // POC 코드

    private LocalDateTime createTime; // 생성 날짜


    // 연관관계 메서드
    private void setPocTestCategory(PocTestCategory pocTestCategory) {
        this.pocTestCategory = pocTestCategory;
        pocTestCategory.getPocTestFuncList().add(this);
    }


    // 생성자 메서드
    public PocTestFunc(PocTestCategory pocTestCategory, PocTypeEnum category, String type,
                       String content, List<PocTestCase> pocTestCaseList) {
        this.setPocTestCategory(pocTestCategory);
        this.content = content;
        this.pocTestCaseList = pocTestCaseList;
        this.createTime = LocalDateTime.now();

    }


}
