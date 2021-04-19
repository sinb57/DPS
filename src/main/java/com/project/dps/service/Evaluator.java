package com.project.dps.service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Evaluator {
    private static final String BASE_PATH = "./";
    private static final long WAIT_MAX_TIME = 20;
    private static final int EVALUATION_COUNT_MAX = 200;
    private static int evaluationCount = 0;

    public static boolean Evaluate(List<String> URLs, String POC_ID) {
        String POC = FetchPOC(POC_ID);

        String POCFilePath;
        try {
            POCFilePath = CreatePOCFile(POC);
        } catch (IOException e) {
            return false;
        }

        boolean result = EvaluatePOC(URLs, POCFilePath);

        RemovePOCFile(POCFilePath);

        return result;
    }

    private static String FetchPOC(String POC_ID) {
        String POC;

        // TODO: Fetch POC

        // TEST
        POC = "var url = casper.cli.get('url0'); \n"
                + "casper.test.begin('Test', function suite(test) { \n"
                + "  casper.start(url, function() { \n"
                + "      casper.fill('#form1', {input1: 'value1', input2: 'value2'}, true); \n"
                + "  }).then(function () { \n"
                + "      test.assertExist('#element1'); \n"
                + "      test.assert(casper.fetchText('#element1') == 'hello, world!'); \n"
                + "      \n"
                + "      test.assertExist('#element2');\n"
                + "  }).run(function () { \n"
                + "      test.done(); \n"
                + "  }); \n "
                + "});";

        return POC;
    }

    private static synchronized String GetPOCFileName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String datetime = LocalDateTime.now().format(formatter);

        int hashCode = (datetime + evaluationCount).hashCode();
        String POCFileName = Math.abs(hashCode) + ".js";

        evaluationCount = ++evaluationCount % EVALUATION_COUNT_MAX;

        return POCFileName;
    }
    private static String CreatePOCFile(String POC) throws IOException {
        String POCFileName = GetPOCFileName();
        String POCFilePath = BASE_PATH + POCFileName;

        File file = new File(POCFilePath);

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(POC);
        writer.close();

        return POCFilePath;
    }
    private static void RemovePOCFile(String POCFilePath) {
        File file = new File(POCFilePath);

        if (file.exists())
            file.delete();
    }

    private static boolean EvaluatePOC(List<String> URLs, String POCFilePath) {
        Runtime runtime = Runtime.getRuntime();

        List<String> paramURLs = new ArrayList<>();
        for (int i = 0; i < URLs.size(); i++)
            paramURLs.add("--url" + i + "=" + URLs.get(i));

        List<String> params = new ArrayList<>();
        params.add("casperjs");
        params.add("test");
        params.addAll(paramURLs);
        params.add(POCFilePath);

        try {
            Process process = runtime.exec(params.toArray(new String[0]));

            if (process.waitFor(WAIT_MAX_TIME, TimeUnit.SECONDS))
                return process.exitValue() == 0;
            return false;
        } catch (IOException | InterruptedException ignored) {
            return false;
        }
    }
}
