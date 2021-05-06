package com.project.dps.domain.poc;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PocSolution {

    @Id @GeneratedValue
    @Column(name = "solution_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "poc_test_category_id")
    private PocTestCategory pocTestCategory;

    private String title; // solution 제목

    private String content; // solution 내용


    // 연관관계 메서드
    private void setPocTestCategory(PocTestCategory pocTestCategory) {
        this.pocTestCategory = pocTestCategory;
        pocTestCategory.setPocSolution(this);
    }

    // 생성자 메서드
    public PocSolution(PocTestCategory pocTestCategory, String title, String content) {
        this.setPocTestCategory(pocTestCategory);
        this.title = title;
        this.content = content;
    }
}
