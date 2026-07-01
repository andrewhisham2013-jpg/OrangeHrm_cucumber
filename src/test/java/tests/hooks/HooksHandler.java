package tests.hooks;

import tests.base.BaseTest;
import tests.driver.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.CartPage;
import org.example.LoginPage;
import org.testng.asserts.SoftAssert;
import tests.reuse.ConfigManager;
import tests.reuse.Screenshots;
import tests.reuse.SwagLabsObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class HooksHandler extends BaseTest {
    private Logger log = LogManager.getLogger(HooksHandler.class);

    @Before
    public void setUp() {
//        jsonFileManagerInvalidLogin = new JsonFileManager("src/main/resources/LoginData/InvalidCredential.json");
        log.debug("Json file manager initialization");
        configManager = new ConfigManager("src/main/resources/Config.properties");
        log.debug("Configuration file manager initialization");
//        excelFileManager = new ExcelFileManager("src/main/resources/data.xlsx","Sheet1");
        log.debug("Excel file manager initialization");
//        csvFileManager = new CSVFileManager("src/main/resources/CSVData.csv");
        log.debug("CSV file manager initialization");

        driver = WebDriverFactory.getDriver(configManager.getValue("browser"));
        SwagLabsObjects.loginPage = new LoginPage(driver);
        SwagLabsObjects.cartPage = new CartPage(driver);
        softAssert = new SoftAssert();
    }

    @After
    public void tearDown() {
        try {
            Allure.addAttachment("log file", new FileInputStream("logs/application.log"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (driver != null) {
            driver.quit();
            log.info("Driver Closed");
        }
    }

    @AfterStep
    public void checkFail(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            // Using scenario name since TestNG result.getName() isn't available here
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            File scrShot = Screenshots.takesshots(driver, "Screenshots/" + screenshotName + ".png");

            Allure.addAttachment("Screenshot on Failure", new FileInputStream(scrShot));
        }
    }
}