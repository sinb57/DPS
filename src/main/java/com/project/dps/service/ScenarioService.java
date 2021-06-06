package com.project.dps.service;


import com.project.dps.domain.log.TestCategoryLog;
import com.project.dps.domain.member.Member;
import com.project.dps.domain.scenario.Scenario;
import com.project.dps.domain.scenario.stage.Stage;
import com.project.dps.domain.scenario.stage.poc.TestCategory;
import com.project.dps.domain.scenario.stage.poc.TestScenario;
import com.project.dps.dto.scenario.ScenarioDto;
import com.project.dps.repository.ScenarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScenarioService {

    private final ScenarioRepository scenarioRepository;

    public List<ScenarioDto> getScenarioList() {
        List<Scenario> scenarioList = scenarioRepository.findAll();
        return scenarioList.stream()
                .map(scenario -> ScenarioDto.toDto(scenario))
                .collect(Collectors.toList());
    }


    public Long getIdBySubTitle(String subTitle) {
        return getScenarioIfExist(subTitle).getId();
    }

    public ScenarioDto findById(Long id) {
        return ScenarioDto.toDto(getScenarioIfExist(id));
    }

    public ScenarioDto findBySubTitle(String subTitle) {
        return ScenarioDto.toDto(getScenarioIfExist(subTitle));
    }

    public ScenarioDto findSummaryBySubTitle(String subTitle) {
        return ScenarioDto.toSimpleDto(getScenarioIfExist(subTitle));
    }


    Scenario getScenarioIfExist(Long id) {
        Optional<Scenario> scenario = scenarioRepository.findById(id);
        // EXCEPTION
        return scenario.orElseThrow(() -> new IllegalStateException("Not existed scenario subtitle"));
    }

    Scenario getScenarioIfExist(String subTitle) {
        Optional<Scenario> scenario = scenarioRepository.findBySubTitle(subTitle);
        // EXCEPTION
        return scenario.orElseThrow(() -> new IllegalStateException("Not existed scenario subtitle"));
    }
}
