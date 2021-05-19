package com.project.dps.repository;

import com.project.dps.domain.scenario.Scenario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScenarioRepository extends JpaRepository<Scenario, Long> {

    Page<Scenario> findAll(Pageable pageable);

    Optional<Scenario> findBySubTitle(String subTitle);

    List<Scenario> findByTitleLike(String title);

    Page<Scenario> findByTitleLike(String title, Pageable pageable);

}
