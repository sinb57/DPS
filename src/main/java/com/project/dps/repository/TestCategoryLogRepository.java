package com.project.dps.repository;

import com.project.dps.domain.log.TestCategoryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCategoryLogRepository extends JpaRepository<TestCategoryLog, Long> {


}
