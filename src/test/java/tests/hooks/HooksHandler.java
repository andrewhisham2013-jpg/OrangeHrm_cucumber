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


public class HooksHandler extends BaseTest {
    public Logger log = LogManager.getLogger(HooksHandler.class);
    public static JsonFileManager json;
    public static CSVFileManager csv;
    public static MyInfoPage verificationPage;

    @Before
    public void setUp() {
        driver = WebDriverFactory.getDriver("chrome");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        softAssert = new SoftAssert();
        log.debug("Assertion Manager Initialized");

        json = new JsonFileManager("src/main/resources/config.json");
        log.debug("Json File Manager Initialized");

        csv = new CSVFileManager("src/main/resources/user_stories.csv");
        log.debug("CSV File Manager Initialized");

        verificationPage = null;

        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
    }

    @After
    public void teardown() {
        try {
            File logFile = new File("logs/application.log");
            if (logFile.exists()) {
                Allure.addAttachment("log File", new FileInputStream(logFile));
            }
        } catch (Exception e) {
            e.printStackTrace();
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
                directory.mkdirs();
            }

            File scrshoot = Screenshot.takeScreenshot(driver, "screenshots/" + scenario.getName().replaceAll("[^a-zA-Z0-9.-]", "_") + ".png");
            if (scrshoot != null && scrshoot.exists()) {
                Allure.addAttachment("ScreenShot", new FileInputStream(scrshoot));
            }
        }
    }
}