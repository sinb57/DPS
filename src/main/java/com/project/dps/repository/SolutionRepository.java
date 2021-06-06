package com.project.dps.repository;

import com.project.dps.domain.scenario.Scenario;
import com.project.dps.domain.scenario.stage.poc.Solution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, Long> {

    Optional<Solution> findByTitle(String title);

}
