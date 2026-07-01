package tests.userAssignment.Dashboard;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/tests/userAssignment/Dashboard/Dashboard.feature",
        glue = {"tests"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/UserAssignmentDashboard-Report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class DashboardRunner extends AbstractTestNGCucumberTests {
}