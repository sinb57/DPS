package com.project.dps.domain.poc;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("secure")
@Getter
public class SecurePoc extends Poc {

    private String type;
    private String content;
}
