package tests.hooks;

import org.example.myinfo.MyInfoPage;
import tests.base.BaseTest;
import org.example.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import tests.reuse.CSVFileManager;
import tests.reuse.JsonFileManager;
import tests.reuse.Screenshot;
import tests.reuse.WebDriverFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

@SuppressWarnings("unused")
public class HooksHandler extends BaseTest {
    private static final Logger log = LogManager.getLogger(HooksHandler.class);
    public static JsonFileManager json;
    public static CSVFileManager csv;
    public static MyInfoPage verificationPage;

    @Before
    public void setUp() {
        json = new JsonFileManager("src/main/resources/config.json");
        log.debug("Json File Manager Initialized");

        csv = new CSVFileManager("src/main/resources/user_stories.csv");
        log.debug("CSV File Manager Initialized");

        String targetBrowser = System.getenv("BROWSER");
        if (targetBrowser == null || targetBrowser.isEmpty()) {
            targetBrowser = json.getValueByKey("browser");
        }

        driver = WebDriverFactory.getDriver(targetBrowser);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        softAssert = new SoftAssert();
        log.debug("Assertion Manager Initialized");

        verificationPage = null;

        String targetUrl = System.getenv("APP_URL");
        if (targetUrl == null || targetUrl.isEmpty()) {
            targetUrl = json.getValueByKey("url");
        }

        driver.get(targetUrl);
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
    }

    @After
    public void teardown() {
        try {
            File logFile = new File("logs/application.log");
            if (logFile.exists()) {
                Allure.addAttachment("Framework Execution Log", new FileInputStream(logFile));
            }
        } catch (Exception e) {
            log.error("Failed to attach log file to Allure report", e);
        }

        if (driver != null) {
            driver.quit();
        }
    }

    @AfterStep
    public void checkFail(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            File directory = new File("screenshots");
            if (!directory.exists()) {
                boolean created = directory.mkdirs();
                if (!created) {
                    log.warn("Could not create screenshots directory.");
                }
            }

            String sanitizedScenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9.-]", "_");
            File failureScreenshot = Screenshot.takeScreenshot(driver, "screenshots/" + sanitizedScenarioName + ".png");

            if (failureScreenshot.exists()) {
                Allure.addAttachment("Failure Screenshot", new FileInputStream(failureScreenshot));
            }
        }
    }
}