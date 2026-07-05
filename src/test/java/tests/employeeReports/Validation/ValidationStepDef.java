package tests.employeeReports.Validation;

import org.example.pim.PimPage;
import org.example.pim.PimReportPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import tests.base.BaseTest;
import org.testng.Assert;

public class ValidationStepDef extends BaseTest {



    @Given("the admin logs in and opens the primary PIM report configuration window")
    public void adminLogsInAndNavigatesToReports() {
        loginPage
                .enterUsername(jsonFileManager.getNestedValue("admin", "username"))
                .enterPassword(jsonFileManager.getNestedValue("admin", "password"))
                .clickLoginButton();

        reportPage = new PimPage(driver).navigateToPimReports();
    }

    @When("the admin submits custom structural reporting constraints named {string}")
    public void adminSubmitsCustomReportingConstraints(String reportName) {
        reportPage
                .clickAddReport()
                .configureReportDetails(reportName)
                .enterEmployeeCriterion("Andrew Hisham")
                .selectDisplayFields("Personal", "Employee First Name")
                .selectDisplayFields("Personal", "Nationality")
                .selectDisplayFields("Personal", "Marital Status")
                .selectDisplayFields("Emergency Contacts", "Name")
                .selectDisplayFields("Emergency Contacts", "Relationship")
                .selectDisplayFields("Emergency Contacts", "Home Telephone")
                .clickSaveReport();
    }

    @Then("the admin confirms that the generated reporting layout display header reflects {string}")
    public void adminConfirmsReportingLayoutHeader(String expectedTitle) {
        Assert.assertEquals(reportPage.getReportHeaderTitleText(), expectedTitle, "The report header title does not match the provided report configuration name.");
    }
}