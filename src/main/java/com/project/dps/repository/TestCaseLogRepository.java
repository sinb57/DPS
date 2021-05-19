package com.project.dps.repository;

import com.project.dps.domain.log.TestCaseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseLogRepository extends JpaRepository<TestCaseLog, Long> {


}
