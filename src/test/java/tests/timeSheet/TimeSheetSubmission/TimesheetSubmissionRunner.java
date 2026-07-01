package tests.timeSheet.TimeSheetSubmission;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/tests/timeSheet/TimeSheetSubmission/TimesheetSubmission.feature",
        glue = {"tests"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/TimesheetSubmission-Report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class TimesheetSubmissionRunner extends AbstractTestNGCucumberTests {
}