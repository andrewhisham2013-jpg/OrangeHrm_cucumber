package tests.userAssignment.Login;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

    @CucumberOptions(
            features = "src/test/java/tests/userAssignment/Login/Login.feature",
            glue = {"tests"},
            plugin = {
                    "pretty",
                    "html:target/cucumber-reports/UserAssignmentLogin-Report.html",
                    "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
            }
    )
    public class LoginRunner extends AbstractTestNGCucumberTests {
    }
