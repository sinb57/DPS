package com.project.dps.domain;

import com.project.dps.domain.log.poc.PocLog;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Column(unique = true)
    private String email;

    private String name;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<PocLog> logList = new ArrayList<>();


    // 생성자 메서드
    @Builder
    public Member(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }


    public void modifyNameAndPassword(String name, String password) {
        this.name = name;
        this.password = password;
    }
}