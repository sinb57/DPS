package com.project.dps.domain;

import com.project.dps.domain.log.poc.PocLog;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<PocLog> logList = new ArrayList<>();
}