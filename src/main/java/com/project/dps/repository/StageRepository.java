package com.project.dps.repository;

import com.project.dps.domain.Scenario;
import com.project.dps.domain.Stage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {

//    Page<Stage> findByScenario_Id(@Param(value = "scenarioId") Long scenarioId, Pageable pageable);

    @Query("select count(s) from Stage s where (scenario_id = :scenarioId)")
    int countByScenarioIdAndStageNo(@Param("scenarioId") Long scenarioId);

//    Page<Stage> findByTitleLike(String title, Pageable pageable);
//
//    @Query("select count(stage) from Stage s where (scenario_id = :scenarioId)")
//    int countByScenarioIdAndStageNo(@Param("scenarioId") Long scenarioId);
//
    @Query("select s from Stage s where scenario_id = :scenarioId and no = :stageNo")
    Optional<Stage> findByScenarioIdAndStageNo(@Param("scenarioId") Long scenarioId, @Param("stageNo") Long stageNo);

//
//    @Query("select s from Stage s where (scenario_id = :scenarioId")
//    Page<Stage> findByScenarioId(@Param("scenarioId") Long scenarioId);

}
