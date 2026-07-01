package tests.employeeReports.Login;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/tests/employeeReports/Login/AdminLogin.feature",
        glue = {"tests"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/AdminLogin-Report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class AdminLoginRunner extends AbstractTestNGCucumberTests {
}