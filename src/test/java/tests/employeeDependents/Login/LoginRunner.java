package tests.employeeDependents.Login;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/tests/employeeDependents/Login/Login.feature",
        glue = {"tests"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/EmployeeDependentsLogin-Report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class LoginRunner extends AbstractTestNGCucumberTests {
}