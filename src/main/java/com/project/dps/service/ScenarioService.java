package com.project.dps.service;


import com.project.dps.domain.scenario.Scenario;
import com.project.dps.dto.scenario.ScenarioDto;
import com.project.dps.repository.ScenarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScenarioService {

    private final ScenarioRepository scenarioRepository;

    @Transactional
    public void create() {

    }

    @Transactional
    public void update() {

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




//    public Page<ScenarioDto> findScenarios(Pageable pageable) {
//        int pageNo = (pageable.getPageNumber() == 0 ? 0 : (pageable.getPageNumber() - 1));
//        int elementCount = 10;
//
//        pageable = PageRequest.of(pageNo, elementCount, Sort.Direction.DESC, "id");
//        return scenarioRepository.findAll(pageable)
//                .map(scenario -> mapper.toDto(scenario));
//    }


    private Scenario getScenarioIfExist(Long id) {
        Optional<Scenario> scenario = scenarioRepository.findById(id);
        // EXCEPTION
        return scenario.orElseThrow(() -> new IllegalStateException("Not existed scenario subtitle"));
    }

    private Scenario getScenarioIfExist(String subTitle) {
        Optional<Scenario> scenario = scenarioRepository.findBySubTitle(subTitle);
        // EXCEPTION
        return scenario.orElseThrow(() -> new IllegalStateException("Not existed scenario subtitle"));
    }
}
