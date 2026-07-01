package tests.employeeReports.ContactReport;

import org.example.pim.PimReportPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import tests.base.BaseTest;

public class ContactReportStepDef extends BaseTest {


    @Given("the admin logs in and navigates to the PIM Reports configuration section")
    public void adminNavigatesToPimReports() {
        pimReportPage = loginPage
                .enterUsername(jsonFileManager.getNestedValue("admin", "username"))
                .enterPassword(jsonFileManager.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .navigateToPimReports();
    }

    @When("the admin configures a custom report named {string} with specified criterion and display fields")
    public void adminConfiguresCustomReport(String reportName) {
        String employeeFullName = jsonFileManager.getNestedValue("newEmployee", "firstName") + " " + jsonFileManager.getNestedValue("newEmployee", "lastName");

        pimReportPage
                .clickAddReport()
                .configureReportDetails(reportName)
                .enterEmployeeCriterion(employeeFullName)
                .selectDisplayFields("Personal", "Employee First Name")
                .selectDisplayFields("Personal", "Nationality")
                .selectDisplayFields("Personal", "Marital Status")
                .selectDisplayFields("Emergency Contacts", "Name")
                .selectDisplayFields("Emergency Contacts", "Relationship")
                .selectDisplayFields("Emergency Contacts", "Home Telephone")
                .clickSaveReport();
    }
}