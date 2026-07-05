package tests.employeeProfile.Login;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/tests/employeeProfile/Login/LoginEmployee.feature",
        glue = {"tests"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/LoginEmployee-Report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class LoginEmployeeRunner extends AbstractTestNGCucumberTests {
}