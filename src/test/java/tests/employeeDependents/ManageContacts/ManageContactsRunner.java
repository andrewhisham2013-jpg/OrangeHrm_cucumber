package tests.employeeDependents.ManageContacts;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/tests/employeeDependents/ManageContacts/ManageContacts.feature",
        glue = {"tests"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/EmployeeDependentsManageContacts-Report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class ManageContactsRunner extends AbstractTestNGCucumberTests {
}