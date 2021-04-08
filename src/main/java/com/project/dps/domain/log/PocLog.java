package com.project.dps.domain.log;

import com.project.dps.domain.Member;
import com.project.dps.domain.poc.Poc;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Getter
public abstract class PocLog {

    @Id @GeneratedValue
    @Column(name = "poc_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "poc_id")
    private Poc poc;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

}
