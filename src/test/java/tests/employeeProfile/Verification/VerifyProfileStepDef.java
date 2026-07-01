package tests.employeeProfile.Verification;

import org.example.pages.LoginPage;
import org.example.pages.DashboardPage;
import org.example.myinfo.MyInfoPage;
import org.example.pim.PimPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import tests.base.BaseTest;
import tests.hooks.HooksHandler;
import org.testng.Assert;

public class VerifyProfileStepDef extends BaseTest {



    @Given("the user logs in as an ESS employee")
    public void logInAsEmployee() {
        loginPage
                .enterUsername(HooksHandler.csv.getRowData(0).get("username"))
                .enterPassword(HooksHandler.csv.getRowData(0).get("password"))
                .clickLoginButton();
    }

    @Given("the user navigates to PIM My Info Personal Details")
    public void navigateToMyInfo() {
        new DashboardPage(driver)
                .clickMyInfo();
    }

    @When("the user updates fields including contact details, emergency contacts, marital status, and nationality")
    public void updatePersonalFields() {
        myInfoPage = new MyInfoPage(driver)
                .updatePersonalDetails(HooksHandler.csv.getRowData(0).get("nationality"), HooksHandler.csv.getRowData(0).get("maritalStatus"))
                .navigateToContactDetails()
                .updateContactDetails(HooksHandler.csv.getRowData(0).get("street"))
                .navigateToEmergencyContacts()
                .addEmergencyContact(HooksHandler.csv.getRowData(0).get("emergencyName"), HooksHandler.csv.getRowData(0).get("emergencyRelationship"), HooksHandler.csv.getRowData(0).get("emergencyPhone"));
    }

    @When("the user logs out of the employee account")
    public void employeeLogsOut() {
        myInfoPage
                .logout();
    }

    @When("the user logs back in as Admin HR")
    public void logInAsAdmin() {
        new LoginPage(driver)
                .enterUsername(HooksHandler.json.getNestedValue("admin", "username"))
                .enterPassword(HooksHandler.json.getNestedValue("admin", "password"))
                .clickLoginButton();
    }

    @When("the admin navigates to PIM Employee List and selects the updated employee")
    public void adminSelectsEmployee() {
        new DashboardPage(driver)
                .pressOnPimButton();

        new PimPage(driver)
                .searchAndOpenEmployeeDetails("Andrew Hisham");

        myInfoPage = new MyInfoPage(driver);
    }

    @Then("the admin verifies that the employee changes are correctly saved")
    public void verifyChangesAsAdmin() {
        Assert.assertEquals(myInfoPage.getSelectedNationality(), HooksHandler.csv.getRowData(0).get("nationality"));
        Assert.assertEquals(myInfoPage.getSelectedMaritalStatus(), HooksHandler.csv.getRowData(0).get("maritalStatus"));

        myInfoPage.navigateToContactDetails();
        Assert.assertEquals(myInfoPage.getStreetAddressValue(), HooksHandler.csv.getRowData(0).get("street"));

        myInfoPage.navigateToEmergencyContacts();
        boolean isContactPresent = myInfoPage.isEmergencyContactPresent(
                HooksHandler.csv.getRowData(0).get("emergencyName"),
                HooksHandler.csv.getRowData(0).get("emergencyRelationship"),
                HooksHandler.csv.getRowData(0).get("emergencyPhone")
        );
        Assert.assertTrue(isContactPresent, "Verification failed: Emergency contact details mismatch!");
    }
}