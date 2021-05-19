package com.project.dps.repository;

import com.project.dps.domain.log.TestScenarioLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestScenarioLogRepository extends JpaRepository<TestScenarioLog, Long> {


}
