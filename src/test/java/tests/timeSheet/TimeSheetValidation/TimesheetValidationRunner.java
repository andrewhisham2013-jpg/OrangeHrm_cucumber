package tests.timeSheet.TimeSheetValidation;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/tests/timeSheet/TimeSheetValidation/TimesheetValidation.feature",
        glue = {"tests"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/TimesheetValidation-Report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class TimesheetValidationRunner extends AbstractTestNGCucumberTests {
}