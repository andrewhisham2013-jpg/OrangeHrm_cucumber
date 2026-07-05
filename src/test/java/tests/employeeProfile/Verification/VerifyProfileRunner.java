package tests.employeeProfile.Verification;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/tests/employeeProfile/Verification/VerifyEmployeeData.feature",
        glue = {"tests"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/PersonalInfoUpdate-Report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class VerifyProfileRunner extends AbstractTestNGCucumberTests {
}