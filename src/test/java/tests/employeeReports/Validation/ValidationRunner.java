package tests.employeeReports.Validation;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/tests/employeeReports/Validation/Validation.feature",
        glue = {"tests"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/ValidationReport-Report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class ValidationRunner extends AbstractTestNGCucumberTests {
}