package tests.userAssignment.AddEmployee;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/tests/userAssignment/AddEmployee/AddEmployee.feature",
        glue = {"tests"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/UserAssignmentAddEmployee-Report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class AddEmployeeRunner extends AbstractTestNGCucumberTests {
}