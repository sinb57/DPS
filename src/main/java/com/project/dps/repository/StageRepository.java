package com.project.dps.repository;

import com.project.dps.domain.Scenario;
import com.project.dps.domain.Stage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StageRepository extends JpaRepository<Scenario, Long> {

//    Page<Stage> findByScenario_Id(@Param(value = "scenarioId") Long scenarioId, Pageable pageable);

    List<Stage> findByTitleLike(String title);

    Page<Stage> findByTitleLike(String title, Pageable pageable);


}
