package com.project.dps.domain.log.poc;

import com.project.dps.domain.Member;
import com.project.dps.domain.poc.Poc;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("secure")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SecurePocLog extends PocLog {

    @Enumerated(EnumType.STRING)
    private PocResult result;
    private LocalDateTime createTime; // 생성 날짜

    // 생성 메서드
    public SecurePocLog (Member member, Poc poc, PocResult result) {
        super(member, poc);
        this.result = result;
        this.createTime = LocalDateTime.now();
    }
}
