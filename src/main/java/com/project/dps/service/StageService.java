package com.project.dps.service;

import com.project.dps.mapstruct.dto.StageDto;
import com.project.dps.domain.Stage;
import com.project.dps.mapstruct.mapper.StageMapper;
import com.project.dps.repository.StageRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

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
    public StageDto findById(Long stageId) {
        return mapper.toDto(getStageIfExist(stageId));
    }

    public StageDto findByScenarioIdAndStageNo(Long scenarioId, Long stageNo) {
        return mapper.toDto(getStageIfExist(scenarioId, stageNo));
    }

    Stage getStageIfExist(Long stageId) {
        Optional<Stage> stage = stageRepository.findById(stageId);
        // EXCEPTION
        return stage.orElseThrow(() -> new IllegalStateException("Not existed stage"));
    }

    Stage getStageIfExist(Long scenarioId, Long stageNo) {
        Optional<Stage> stage = stageRepository.findByScenarioIdAndStageNo(scenarioId, stageNo);
        // EXCEPTION
        return stage.orElseThrow(() -> new IllegalStateException("Not existed stage"));
    }
}