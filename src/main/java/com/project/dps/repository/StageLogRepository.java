package com.project.dps.repository;

import com.project.dps.domain.log.StageLog;
import com.project.dps.domain.scenario.stage.Stage;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StageLogRepository extends JpaRepository<StageLog, Long> {

    @Query(value = "Select * from Stage_Log where (member_id = :memberId and stage_id = :stageId) order by create_time limit 1", nativeQuery = true)
    Optional<StageLog> getOneByMemberIdAndStageId(@Param("memberId") Long memberId, @Param("stageId") Long stageId);
}
