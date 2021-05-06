package com.project.dps.service;

import com.project.dps.mapstruct.dto.ScenarioDto;
import com.project.dps.domain.Scenario;
import com.project.dps.mapstruct.mapper.ScenarioMapper;
import com.project.dps.repository.ScenarioRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScenarioService {

    private final ScenarioRepository scenarioRepository;
    private final ScenarioMapper mapper = Mappers.getMapper(ScenarioMapper.class);

    /*
    @Transactional
    public void create()
     */

    /*
    @Transactional
    public void update()
     */

    public ScenarioDto findBySubTitle(String subTitle) {
        return mapper.toDto(getScenarioIfExist(subTitle));
    }

    public ScenarioDto findSummaryBySubTitle(String subTitle) {
        return mapper.toSimpleDto(getScenarioIfExist(subTitle));
    }




//    public Page<ScenarioDto> findScenarios(Pageable pageable) {
//        int pageNo = (pageable.getPageNumber() == 0 ? 0 : (pageable.getPageNumber() - 1));
//        int elementCount = 10;
//
//        pageable = PageRequest.of(pageNo, elementCount, Sort.Direction.DESC, "id");
//        return scenarioRepository.findAll(pageable)
//                .map(scenario -> mapper.toDto(scenario));
//    }


    private Scenario getScenarioIfExist(String subTitle) {
        Optional<Scenario> scenario = scenarioRepository.findBySubTitle(subTitle);
        // EXCEPTION
        return scenario.orElseThrow(() -> new IllegalStateException("Not existed scenario subtitle"));
    }
}
