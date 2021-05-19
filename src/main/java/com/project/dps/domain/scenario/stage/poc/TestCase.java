package com.project.dps.domain.scenario.stage.poc;

import com.project.dps.domain.log.TestCaseLog;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "poc_case")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestCase {

    @Id @GeneratedValue
    @Column(name = "poc_case_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "poc_common_id")
    private TestCommon testCommon;

    @OneToMany(mappedBy = "testCase")
    private List<TestCaseLog> testCaseLogList = new ArrayList<>();

    private String content; // POC 코드


    //== Builder 메서드 ==//
    @Builder
    public TestCase(TestCommon testCommon, String content, List<TestCaseLog> testCaseLogList) {
        this.content = content;
        this.testCaseLogList = testCaseLogList;

        // 연관관계 로직
        this.testCommon = testCommon;
        testCommon.appendTestCaseList(this);
    }


    //== 비즈니스 로직 ==//
    public void appendPocLog(TestCaseLog testCaseLog) {
        this.testCaseLogList.add(testCaseLog);
    }

    public void removePocLog(TestCaseLog testCaseLog) {
        this.testCaseLogList.remove(testCaseLog);
    }

    public void edit(String content) {
        this.content = content;
    }
}
