package tests.employeeReports.ContactReport;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import tests.base.BaseTest;
import tests.hooks.HooksHandler;

public class ContactReportStepDef extends BaseTest {

    @Given("the admin logs in and navigates to the PIM Reports configuration section")
    public void adminNavigatesToPimReports() {
        pimReportPage = loginPage
                .enterUsername(HooksHandler.json.getNestedValue("admin", "username"))
                .enterPassword(HooksHandler.json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .navigateToPimReports();
    }

    @When("the admin configures a custom report named {string} with specified criterion and display fields")
    public void adminConfiguresCustomReport(String reportName) {
        String employeeFullName = HooksHandler.json.getNestedValue("newEmployee", "firstName") + " " + HooksHandler.json.getNestedValue("newEmployee", "lastName");

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