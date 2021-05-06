package com.project.dps.service;

import com.project.dps.domain.Member;
import com.project.dps.domain.Stage;
import com.project.dps.domain.log.PocTestCaseLog;
import com.project.dps.domain.log.StageLog;
import com.project.dps.domain.poc.*;
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
public class EvaluatorService {
    private final String BASE_PATH = "./";
    private final long WAIT_MAX_TIME = 20;
    private final int EVALUATION_COUNT_MAX = 200;
    private int evaluationCount = 0;

    public StageLog evaluate(Stage stage, Member member, String targetUrl) {

        StageLog stageLog = new StageLog(stage, member, PocResultEnum.FAIL, null);

        List<PocTestCategory> pocTestCategoryList = stage.getPocTestCategoryList();

        for (PocTestCategory pocTestCategory : pocTestCategoryList) {
            for (PocTestFunc pocTestFunc : pocTestCategory.getPocTestFuncList()) {
                for (PocTestCase pocTestCase : pocTestFunc.getPocTestCaseList()) {
                    String pocContent = pocTestFunc.getContent() + "\n\n" + pocTestCase.getContent();
                    PocResultEnum result = evaluate(targetUrl, pocContent);
                    PocTestCaseLog pocDetailLog = new PocTestCaseLog(
                            stageLog, pocTestCase, result, pocTestCategory.getType(), pocTestCategory.getCategory());
                }
            }
        }

        return stageLog;

    }


    private PocResultEnum evaluate(String targetUrl, String pocContent) {

        String pocFilePath;

        try {
            pocFilePath = createPocFile(pocContent);
        } catch (IOException e) {
            return PocResultEnum.FAIL;
        }
        List<String> urlList = new ArrayList<>();
        urlList.add(targetUrl);

        PocResultEnum result = evaluatePoc(urlList, pocFilePath);

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
    private String createPocFile(String poc) throws IOException {
        String pocFileName = getPocFileName();
        String pocFilePath = BASE_PATH + pocFileName;

        File file = new File(pocFilePath);

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(poc);
        writer.close();

        return pocFilePath;
    }
    private void removePocFile(String pocFilePath) {
        File file = new File(pocFilePath);

        if (file.exists())
            file.delete();
    }

    private PocResultEnum evaluatePoc(List<String> urlList, String pocFilePath) {
        Runtime runtime = Runtime.getRuntime();

        List<String> paramurl = new ArrayList<>();
        for (int i = 0; i < urlList.size(); i++)
            paramurl.add("--url" + i + "=" + urlList.get(i));

        List<String> params = new ArrayList<>();
        params.add("casperjs");
        params.add("test");
        params.addAll(paramurl);
        params.add(pocFilePath);

        try {
            Process process = runtime.exec(params.toArray(new String[0]));

            if (process.waitFor(WAIT_MAX_TIME, TimeUnit.SECONDS))
                return (process.exitValue() == 0) ? PocResultEnum.PASS : PocResultEnum.FAIL;
            return PocResultEnum.FAIL;
        } catch (IOException | InterruptedException ignored) {
            return PocResultEnum.FAIL;
        }
    }
}
