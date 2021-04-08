package com.project.dps.domain;

import com.project.dps.domain.log.poc.PocLog;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<PocLog> logList = new ArrayList<>();


    // 생성자 메서드
    public Member(String name, String password) {
        this.name = name;
        this.password = password;
    }
}