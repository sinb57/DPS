package com.project.dps.domain.poc;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("function")
public class FunctionalPoc extends Poc {

    private String type;
    private String content;
}
