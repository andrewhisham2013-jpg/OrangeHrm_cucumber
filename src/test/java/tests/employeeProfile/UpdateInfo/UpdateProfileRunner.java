package tests.employeeProfile.UpdateInfo;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/tests/employeeProfile/UpdateInfo/UpdateProfileDetails.feature",
        glue = {"tests"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/UpdateProfileDetails-Report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class UpdateProfileRunner extends AbstractTestNGCucumberTests {
}