package com.project.dps.domain.log.poc;

import com.project.dps.domain.Member;
import com.project.dps.domain.poc.Poc;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class PocLog {

    @Id @GeneratedValue
    @Column(name = "poc_log_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "poc_id")
    private Poc poc;


    // 연관관계 메서드
    private void setMember(Member member) {
        this.member = member;
        member.getLogList().add(this);
    }

    private void setPoc(Poc poc) {
        this.poc = poc;
        poc.getLogList().add(this);
    }

    
    // 생성자 메서드
    public PocLog (Member member, Poc poc) {
        this.setPoc(poc);
        this.setMember(member);
    }
}
