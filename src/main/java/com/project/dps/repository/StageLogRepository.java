package com.project.dps.repository;

import com.project.dps.domain.log.StageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StageLogRepository extends JpaRepository<StageLog, Long> {




}
