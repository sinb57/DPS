package com.project.dps.repository;

import com.project.dps.domain.poc.SecurePoc;
import com.project.dps.domain.poc.FunctionalPoc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class PocRepository {

    private final EntityManager em;

    public void save(FunctionalPoc poc) {
        em.persist(poc);
    }

    public void save(SecurePoc poc) {
        em.persist(poc);
    }



}
