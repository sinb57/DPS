package com.project.dps.service;

import com.project.dps.domain.log.StageLog;
import com.project.dps.domain.scenario.Scenario;
import com.project.dps.domain.scenario.stage.poc.ValidResultEnum;
import com.project.dps.dto.log.StageLogDto;
import com.project.dps.dto.scenario.stage.StageDto;
import com.project.dps.domain.scenario.stage.Stage;
import com.project.dps.repository.StageLogRepository;
import com.project.dps.repository.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StageService {

    private final ScenarioService scenarioService;
    private final StageRepository stageRepository;
    private final StageLogRepository stageLogRepository;

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

    public StageLogDto findByStageId(Long stageLogId) {
        return StageLogDto.toDto(getStageLogIfExist(stageLogId));
    }

    StageLog getStageLogIfExist(Long stageLogId) {
        Optional<StageLog> stageLog = stageLogRepository.findById(stageLogId);
        // EXCEPTION
        return stageLog.orElseThrow(() -> new IllegalStateException("Not existed stageLog"));
    }

    public List<StageLogDto> getStagePassLogList(Long memberId, Long scenarioId) {
        List<StageLog> stagePassLogList = new ArrayList<>();

        Scenario scenario = scenarioService.getScenarioIfExist(scenarioId);

        for (Stage stage : scenario.getStageList()) {
            Optional<StageLog> stageLog = stageLogRepository.getOneByMemberIdAndStageId(memberId, stage.getId());

            if (stageLog.isPresent())
                if (stageLog.get().getResult() == ValidResultEnum.PASS)
                    stagePassLogList.add(stageLog.get());
        }

        return stagePassLogList.stream()
                .map(stageLog -> StageLogDto.toDto(stageLog))
                .collect(Collectors.toList());
    }
}