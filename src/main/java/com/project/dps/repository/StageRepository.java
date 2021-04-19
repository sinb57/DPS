package com.project.dps.repository;

import com.project.dps.domain.Scenario;
import com.project.dps.domain.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StageRepository {

    private final EntityManager em;

    public void save(Stage stage) {
        em.persist(stage);
    }

    public Stage findOne(Long id) {
        return em.find(Stage.class, id);
    }

    public List<Stage> findAll() {
        return em.createQuery("select s from Stage s", Stage.class)
                .getResultList();
    }

    public List<Stage> findByScenarioIdAndNo(Long scenarioId, Long no) {
        return em.createQuery("select s from Stage s " +
                " where scenario_id = :id and no = :no", Stage.class)
                .setParameter("id", scenarioId)
                .setParameter("no", no)
                .getResultList();
    }

    public List<Stage> findByContent(String content) {
        return em.createQuery("select s from Stage s where s.content like :content", Stage.class)
                .setParameter("content", "%" + content + "%")
                .getResultList();
    }
}
