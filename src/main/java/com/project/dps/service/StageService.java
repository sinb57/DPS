package com.project.dps.service;

import com.project.dps.domain.log.StageLog;
import com.project.dps.domain.log.TestCategoryLog;
import com.project.dps.domain.log.TestScenarioLog;
import com.project.dps.domain.member.Member;
import com.project.dps.domain.scenario.Scenario;
import com.project.dps.domain.scenario.stage.poc.ValidResultEnum;
import com.project.dps.dto.log.StageLogDto;
import com.project.dps.dto.scenario.stage.StageDto;
import com.project.dps.domain.scenario.stage.Stage;
import com.project.dps.repository.StageLogRepository;
import com.project.dps.repository.StageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StageService {

    private final MemberService memberService;
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


    public HashMap<String, HashMap<String, Integer>> getStatisticsBySecureCheck(String email, String subtitle) {
        Member member = memberService.getMemberIfExist(email);

        HashMap<String, HashMap<String, Integer>> statisticsByStage = getStatisticsByStage(member.getId(), subtitle);
        HashMap<String, HashMap<String, Integer>> statisticsBySecureCheck = new HashMap<>();

        for (Map.Entry<String, HashMap<String, Integer>> entry : statisticsByStage.entrySet()) {
            String stageTitle = entry.getKey();

            for (Map.Entry<String, Integer> secondEntry : entry.getValue().entrySet()) {
                String secureCheckContent = secondEntry.getKey();
                int testScenarioCount = secondEntry.getValue();

                if (statisticsBySecureCheck.containsKey(secureCheckContent)) {
                    HashMap<String, Integer> stageMap = statisticsBySecureCheck.get(secureCheckContent);
                    stageMap.put(stageTitle, testScenarioCount);
                }
                else {
                    HashMap<String, Integer> stageMap = new HashMap<>();
                    stageMap.put(stageTitle, testScenarioCount);
                    statisticsBySecureCheck.put(secureCheckContent, stageMap);
                }
            }
        }

        return statisticsBySecureCheck;
    }

    public HashMap<String, Integer> getSecureCheckCount(HashMap<String, HashMap<String, Integer>> statisticsBySecureCheck) {
        HashMap<String, Integer> secureCheckCountMap = new HashMap<>();

        for (Map.Entry<String, HashMap<String, Integer>> entry : statisticsBySecureCheck.entrySet()) {
            String testScenarioContent = entry.getKey();
            HashMap<String, Integer> stageMap = entry.getValue();

            for (Map.Entry<String, Integer> secondEntry : stageMap.entrySet()) {
                int stageCount = secondEntry.getValue();

                if (secureCheckCountMap.containsKey(testScenarioContent)) {
                    int count = secureCheckCountMap.get(testScenarioContent);
                    secureCheckCountMap.put(testScenarioContent, count + stageCount);
                }
                else {
                    secureCheckCountMap.put(testScenarioContent, stageCount);
                }
            }
        }
        return secureCheckCountMap;
    }

    public HashMap<String, HashMap<String, Integer>> getStatisticsByStage(Long memberId, String subTitle) {
        HashMap<String, HashMap<String, Integer>> statisticsByStage = new HashMap<>();

        Scenario scenario = scenarioService.getScenarioIfExist(subTitle);
        List<StageLog> stageLogList = stageLogRepository.findByMemberIdAndScenarioId(memberId, scenario.getId());

        for (StageLog stageLog : stageLogList) {
            TestCategoryLog testCategoryLog = stageLog.getTestCategoryLogList().get(1);

            HashMap<String, Integer> secureCheckMap = new HashMap<>();

            for (TestScenarioLog testScenarioLog : testCategoryLog.getTestScenarioLogList()){
                if (testScenarioLog.getResult() == ValidResultEnum.PASS)
                    continue;

                String testScenarioContent = testScenarioLog.getTestScenario().getContent();

                if (secureCheckMap.containsKey(testScenarioContent)) {
                    int count = secureCheckMap.get(testScenarioContent);
                    secureCheckMap.put(testScenarioContent, ++count);
                }
                else {
                    secureCheckMap.put(testScenarioContent, 1);
                }
            }

            String stageTitle = stageLog.getStage().getTitle();

            if (statisticsByStage.containsKey(stageTitle)) {
                HashMap<String, Integer> origin = statisticsByStage.get(stageTitle);
                for (Map.Entry<String, Integer> entry : secureCheckMap.entrySet()) {
                    String key = entry.getKey();
                    if (origin.containsKey(key)) {
                        int count = origin.get(key);
                        origin.put(key, count + entry.getValue());
                    }
                    else {
                        origin.put(key, entry.getValue());
                    }
                }
            }
            else {
                statisticsByStage.put(stageLog.getStage().getTitle(), secureCheckMap);
            }
        }

        return statisticsByStage;
    }

}