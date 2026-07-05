package tests.employeeDependents.Validation;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/tests/employeeDependents/Validation/validation.feature",
        glue = {"tests"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/EmergencyAndDependents-Report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class ValidationRunner extends AbstractTestNGCucumberTests {
}