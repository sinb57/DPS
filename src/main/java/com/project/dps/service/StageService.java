package com.project.dps.service;

import com.project.dps.controller.dto.ScenarioDto;
import com.project.dps.controller.dto.StageDto;
import com.project.dps.domain.Scenario;
import com.project.dps.domain.Stage;
import com.project.dps.mapstruct.StageMapper;
import com.project.dps.repository.ScenarioRepository;
import com.project.dps.repository.StageRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StageService {

    private final StageRepository stageRepository;
    private final StageMapper mapper = Mappers.getMapper(StageMapper.class);


    /*
    @Transactional
    public void create()
     */

    /*
    @Transactional
    public void update()
     */

    public Optional<StageDto> findBySubTitle(Long scenarioId, Long stageNo) {
        Optional<Stage> stage = stageRepository.findByScenarioIdAndStageNo(scenarioId, stageNo);
        StageDto stageDto = mapper.toDto(stage.get());
        return Optional.of(stageDto);
    }

//    public List<StageDto> findStages(Long scenarioId) {
//        return stageRepository.findBy
//                .map(scenario -> mapper.toDto(Stage));
//    }

}