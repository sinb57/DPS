package com.project.dps.domain.poc;

import com.project.dps.domain.log.PocTestCaseLog;
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
public class PocTestCase {

    @Id @GeneratedValue
    @Column(name = "poc_test_case_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "poc_test_func_id")
    private PocTestFunc pocTestFunc;

    @OneToMany(mappedBy = "pocTestCase")
    private List<PocTestCaseLog> pocTestCaseLogList = new ArrayList<>();

    private String content; // POC 코드

    private LocalDateTime createTime; // 생성 날짜


    // 연관관계 메서드
    private void setPocTestFunc(PocTestFunc pocTestFunc) {
        this.pocTestFunc = pocTestFunc;
        pocTestFunc.getPocTestCaseList().add(this);
    }


    // 생성자 메서드
    public PocTestCase(PocTestFunc pocTestFunc, String content,
                       List<PocTestCaseLog> pocTestCaseLogList) {
        this.setPocTestFunc(pocTestFunc);
        this.content = content;
        this.pocTestCaseLogList = pocTestCaseLogList;
        this.createTime = LocalDateTime.now();

    }

}
