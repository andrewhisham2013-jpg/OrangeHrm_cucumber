package tests.employeeReports.ContactReport;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/tests/employeeReports/ContactReport/ContactReport.feature",
        glue = {"tests"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/ContactReport-Report.html",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
        }
)
public class ContactReportRunner extends AbstractTestNGCucumberTests {
}