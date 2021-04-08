package com.project.dps.domain.log;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("secure")
public class SecurePocLog extends PocLog{

    private PocResult result;
    private LocalDateTime localDateTime;

}
