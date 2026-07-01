package tests.timeSheet.TimesheetLogin;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/tests/timeSheet/TimesheetLogin/TimesheetLogin.feature",
        glue = {"tests"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/TimesheetLogin-Report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class TimesheetLoginRunner extends AbstractTestNGCucumberTests {
}