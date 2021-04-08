package com.project.dps.domain.poc;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("secure")
public class SecurePoc extends Poc {

    private String type;
    private String content;
}
