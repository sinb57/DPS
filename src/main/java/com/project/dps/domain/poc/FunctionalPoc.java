package com.project.dps.domain.poc;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("function")
@Getter
public class FunctionalPoc extends Poc {

    private String type;
    private String content;
}
