package tests.employeeDependents.ManageDependents;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/tests/employeeDependents/ManageDependents/ManageDependents.feature",
        glue = {"tests"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/EmployeeDependentsManageDependents-Report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class ManageDependentsRunner extends AbstractTestNGCucumberTests {
}