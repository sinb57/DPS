package com.project.dps.service;

import com.project.dps.dto.scenario.stage.StageDto;
import com.project.dps.domain.scenario.stage.Stage;
import com.project.dps.repository.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StageService {

    private final StageRepository stageRepository;

    /*
    @Transactional
    public void create()
     */

    /*
    @Transactional
    public void update()
     */

    public Long getIdByScenarioIdAndStageNo(Long scenarioId, Long stageNo) {
        return getStageIfExist(scenarioId, stageNo).getId();
    }

    public StageDto findById(Long stageId) {
        return StageDto.toDto(getStageIfExist(stageId));
    }

    public StageDto findByScenarioIdAndStageNo(Long scenarioId, Long stageNo) {
        return StageDto.toDto(getStageIfExist(scenarioId, stageNo));
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