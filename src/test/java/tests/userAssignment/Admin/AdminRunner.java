package tests.userAssignment.Admin;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/tests/userAssignment/Admin/Admin.feature",
        glue = {"tests"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/UserAssignmentAdmin-Report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class AdminRunner extends AbstractTestNGCucumberTests {
}