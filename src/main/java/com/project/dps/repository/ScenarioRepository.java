package com.project.dps.repository;

import com.project.dps.domain.Scenario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScenarioRepository {

    private final EntityManager em;

    public void save(Scenario scenario) {
        em.persist(scenario);
    }

    public Scenario findOne(Long id) {
        return em.find(Scenario.class, id);
    }

    public List<Scenario> findAll() {
        return em.createQuery("select s from Scenario s", Scenario.class)
                .getResultList();
    }

    public List<Scenario> findByTitle(String title) {
        return em.createQuery("select s from Scenario s where s.title like :title", Scenario.class)
                .setParameter("title", "%" + title + "%")
                .getResultList();
    }
}
