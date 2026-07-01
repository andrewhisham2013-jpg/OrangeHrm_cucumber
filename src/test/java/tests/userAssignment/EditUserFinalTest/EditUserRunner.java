package tests.userAssignment.EditUserFinalTest;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/tests/userAssignment/EditUserFinalTest/EditUser.feature",
        glue = {"tests"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/UserAssignment-Report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class EditUserRunner extends AbstractTestNGCucumberTests {
}