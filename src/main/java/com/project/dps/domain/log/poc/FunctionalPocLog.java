package com.project.dps.domain.log.poc;

import com.project.dps.domain.Member;
import com.project.dps.domain.poc.Poc;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("function")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FunctionalPocLog extends PocLog {

    @Enumerated(EnumType.STRING)
    private PocResult result; // PASS, FAIL
    private LocalDateTime createTime; // 생성 날짜


    // 생성 메서드
    public FunctionalPocLog (Member member, Poc poc, PocResult result) {
        super(member, poc);
        this.result = result;
        this.createTime = LocalDateTime.now();
    }
}
