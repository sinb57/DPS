package com.project.dps.domain.log;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("secure")
public class FunctionalPocLog {

    @Enumerated(EnumType.STRING)
    private PocResult result;

    private LocalDateTime localDateTime;

}
