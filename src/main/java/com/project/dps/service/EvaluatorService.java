package com.project.dps.service;

import com.project.dps.domain.log.TestCaseLog;
import com.project.dps.domain.log.TestScenarioLog;
import com.project.dps.domain.scenario.stage.poc.TestCommon;
import com.project.dps.domain.scenario.stage.poc.TestCase;
import com.project.dps.domain.scenario.stage.poc.TestScenario;
import com.project.dps.domain.scenario.stage.Stage;
import com.project.dps.domain.log.StageLog;
import com.project.dps.domain.scenario.stage.poc.*;
import com.project.dps.repository.TestCaseLogRepository;
import com.project.dps.repository.TestScenarioLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EvaluatorService {
    private final String BASE_PATH = "./casperjs/tmp/";
    private final long WAIT_MAX_TIME = 20;
    private final int EVALUATION_COUNT_MAX = 200;
    private int evaluationCount = 0;

    private final TestScenarioLogRepository testScenarioLogRepository;
    private final TestCaseLogRepository testCaseLogRepository;

    @Transactional
    public void evaluate(StageLog stageLog, String targetUrl, String targetExtension) {

        Stage stage = stageLog.getStage();

        for (TestScenario testScenario : stage.getTestScenarioList()) {
            TestScenarioLog testScenarioLog = TestScenarioLog.builder()
                    .stageLog(stageLog).testScenario(testScenario)
                    .result(ValidResultEnum.FAIL).build();

            testScenarioLogRepository.save(testScenarioLog);

            for (TestCommon testCommon : testScenario.getTestCommonList()) {
                for (TestCase testCase : testCommon.getTestCaseList()) {
                    String pocContent = testCommon.getContent() + "\n\n" + testCase.getContent();
                    ValidResultEnum result = evaluate(targetUrl, targetExtension, pocContent);

                    TestCaseLog testCaseLog = TestCaseLog.builder()
                            .testScenarioLog(testScenarioLog).testCase(testCase)
                            .result(result).build();

                    testCaseLogRepository.save(testCaseLog);
                }
            }

            assignTestScenarioLogResult(testScenarioLog);

            if (testScenarioLog.getResult() == ValidResultEnum.PASS) {
                testScenarioLogRepository.save(testScenarioLog);
            }
        }

        assignStageLogResult(stageLog);

    }

    private void assignStageLogResult(StageLog stageLog) {
        List<TestScenarioLog> testScenarioLogList = stageLog.getTestScenarioLogList();
        int passCount = 0;
        for (TestScenarioLog testScenarioLog : testScenarioLogList) {
            if (testScenarioLog.getResult() == ValidResultEnum.PASS)
                passCount++;
        }
        if (passCount == testScenarioLogList.size()) {
            stageLog.setResult(ValidResultEnum.PASS);
        }
    }


    private void assignTestScenarioLogResult(TestScenarioLog testScenarioLog) {
        List<TestCaseLog> testCaseLogList = testScenarioLog.getTestCaseLogList();
        int passCount = 0;
        for (TestCaseLog testCaseLog : testCaseLogList) {
            if (testCaseLog.getResult() == ValidResultEnum.PASS)
                passCount++;
        }
        if (passCount == testCaseLogList.size()) {
            testScenarioLog.setResult(ValidResultEnum.PASS);
        }
    }


    private ValidResultEnum evaluate(String targetUrl, String targetExtension, String pocContent) {

        String pocFilePath;

        try {
            pocFilePath = createPocFile(pocContent);
        } catch (IOException e) {
            return ValidResultEnum.FAIL;
        }

        ValidResultEnum result = evaluatePoc(targetUrl, targetExtension, pocFilePath);

        removePocFile(pocFilePath);

        return result;
    }

    private synchronized String getPocFileName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String datetime = LocalDateTime.now().format(formatter);

        int hashCode = (datetime + evaluationCount).hashCode();
        String pocFileName = Math.abs(hashCode) + ".js";

        evaluationCount = ++evaluationCount % EVALUATION_COUNT_MAX;

        return pocFileName;
    }
    private String createPocFile(String pocContent) throws IOException {
        String pocFileName = getPocFileName();
        String pocFilePath = BASE_PATH + pocFileName;

        File file = new File(pocFilePath);

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(pocContent);
        writer.close();

        return pocFilePath;
    }
    private void removePocFile(String pocFilePath) {
        File file = new File(pocFilePath);

        if (file.exists())
            file.delete();
    }

    private ValidResultEnum evaluatePoc(String targetUrl, String targetExtension, String pocFilePath) {
        Runtime runtime = Runtime.getRuntime();

        List<String> params = new ArrayList<>();
        params.add("casperjs");
        params.add("test");
        params.add("--url=" + targetUrl);
        params.add("--extension=" + targetExtension);
        params.add(pocFilePath);

        try {
            Process process = runtime.exec(params.toArray(new String[0]));

            if (process.waitFor(WAIT_MAX_TIME, TimeUnit.SECONDS))
                return (process.exitValue() == 0) ? ValidResultEnum.PASS : ValidResultEnum.FAIL;
            return ValidResultEnum.FAIL;
        } catch (IOException | InterruptedException ignored) {
            return ValidResultEnum.FAIL;
        }
    }
}
