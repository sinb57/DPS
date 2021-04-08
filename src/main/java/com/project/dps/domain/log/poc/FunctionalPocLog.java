package com.project.dps.domain.log.poc;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("function")
@Getter
public class FunctionalPocLog extends PocLog {

    private LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING)
    private PocResult result; // PASS, FAIL

}
