package com.project.dps.domain.log;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("secure")
@Getter
public class SecurePocLog extends PocLog{

    @Enumerated(EnumType.STRING)
    private PocResult result;

    private LocalDateTime localDateTime;

}
