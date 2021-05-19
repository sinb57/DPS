package com.project.dps.repository;

import com.project.dps.domain.log.PocLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PocLogRepository extends JpaRepository<PocLog, Long> {


}
