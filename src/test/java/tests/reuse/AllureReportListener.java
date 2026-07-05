package tests.reuse;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.*;

import java.io.*;
import java.lang.reflect.Method;
import java.util.Properties;

import static java.lang.invoke.MethodHandles.lookup;

public class AllureReportListener implements ITestListener, ISuiteListener, IInvokedMethodListener {

    private static final Logger log = LogManager.getLogger(lookup().lookupClass());
    private static final String METADATA_PATH = "src/main/resources/loginTest-metadata.json";

    @Override
    public void onStart(ISuite suite) {
        File dir = new File("allure-results");
        if (!dir.exists() && !dir.mkdirs()) {
            log.warn("Failed to create allure-results directory, it might already exist.");
        }
        createEnvironmentFile();
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        if (method.isTestMethod()) {
            loadMetadata(result);
        }
    }

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {}

    @Override
    public void onTestStart(ITestResult result) {
        loadMetadata(result);
        attachTestNGInfo(result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        loadMetadata(result);
        Allure.parameter("Execution Time (ms)", result.getEndMillis() - result.getStartMillis());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        loadMetadata(result);
        Allure.parameter("Execution Time (ms)", result.getEndMillis() - result.getStartMillis());

        if (result.getThrowable() != null) {
            StringWriter sw = new StringWriter();
            result.getThrowable().printStackTrace(new PrintWriter(sw));
            Allure.addAttachment("Stack Trace", sw.toString());
            Allure.addAttachment("Exception Message", result.getThrowable().getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Allure.label("status", "skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    private void loadMetadata(ITestResult result) {
        try {
            File file = new File(METADATA_PATH);
            if (!file.exists()) return;

            String key = result.getTestClass().getName() + "." + result.getMethod().getMethodName();
            JsonFileManager jsonManager = new JsonFileManager(METADATA_PATH);

            String[] fields = {"epic", "feature", "story", "owner", "description", "jira"};

            String epic = jsonManager.getNestedValue(key, fields[0]);
            String feature = jsonManager.getNestedValue(key, fields[1]);
            String story = jsonManager.getNestedValue(key, fields[2]);
            String owner = jsonManager.getNestedValue(key, fields[3]);
            String description = jsonManager.getNestedValue(key, fields[4]);
            String jira = jsonManager.getNestedValue(key, fields[5]);

            if (epic != null && !epic.isEmpty()) Allure.epic(epic);
            if (feature != null && !feature.isEmpty()) Allure.feature(feature);
            if (story != null && !story.isEmpty()) Allure.story(story);
            if (owner != null && !owner.isEmpty()) Allure.label("owner", owner);
            if (description != null && !description.isEmpty()) Allure.description(description);
            if (jira != null && !jira.isEmpty()) Allure.link(jira, jira);

        } catch (Exception e) {
            log.error("Error reading Allure metadata configuration matching this test method context.", e);
            throw new RuntimeException("Aborting suite: Failed to load required metadata configuration.", e);
        }
    }

    private void attachTestNGInfo(ITestResult result) {
        try {
            Method method = result.getMethod().getConstructorOrMethod().getMethod();
            org.testng.annotations.Test test = method.getAnnotation(org.testng.annotations.Test.class);

            if (test == null) return;

            Allure.parameter("Class Name", result.getTestClass().getName());
            Allure.parameter("Method Name", method.getName());
            Allure.parameter("Priority", test.priority());
            Allure.parameter("Enabled", test.enabled());
            Allure.parameter("Invocation Count", test.invocationCount());
            Allure.parameter("Timeout", test.timeOut());

            if (test.groups().length > 0) {
                Allure.parameter("Groups", String.join(", ", test.groups()));
            }

            if (result.getParameters().length > 0) {
                Object[] params = result.getParameters();
                for (int i = 0; i < params.length; i++) {
                    Allure.parameter("Parameter-" + i, String.valueOf(params[i]));
                }
            }
        } catch (Exception e) {
            log.error("Failed to append structural TestNG execution metadata to Allure context.", e);
        }
    }

    private void createEnvironmentFile() {
        Properties properties = new Properties();
        JsonFileManager jsonManager = new JsonFileManager("src/main/resources/config.json");

        String url = jsonManager.getValueByKey("url");
        String browser = jsonManager.getValueByKey("browser");

        if (url == null || browser == null) {
            log.error("Critical configuration keys are missing in config.json.");
            throw new RuntimeException("Aborting suite: Essential values 'url' or 'browser' could not be loaded from config.json.");
        }

        properties.setProperty("OS Name", System.getProperty("os.name"));
        properties.setProperty("OS Version", System.getProperty("os.version"));
        properties.setProperty("OS Architecture", System.getProperty("os.arch"));
        properties.setProperty("User", System.getProperty("user.name"));
        properties.setProperty("Java Version", System.getProperty("java.version"));
        properties.setProperty("Java Vendor", System.getProperty("java.vendor"));
        properties.setProperty("Framework", "Selenium + TestNG");
        properties.setProperty("Project", "OrangeHRM Automation");
        properties.setProperty("Environment", "QA");
        properties.setProperty("Execution Type", "Local");
        properties.setProperty("Application URL", url);
        properties.setProperty("Execution Browser", browser);

        try (FileOutputStream fos = new FileOutputStream("allure-results/environment.properties")) {
            properties.store(fos, "Execution Environment");
        } catch (IOException e) {
            log.error("Could not write environment properties map tracking workspace info.", e);
            throw new RuntimeException("Aborting suite: Failed to output environment properties artifact.", e);
        }
    }
}