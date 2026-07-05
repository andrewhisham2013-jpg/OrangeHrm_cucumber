package tests.employeeProfile.UpdateInfo;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.example.pages.DashboardPage;
import org.example.myinfo.MyInfoPage;
import tests.base.BaseTest;
import tests.hooks.HooksHandler;

public class UpdateProfileStepDef extends BaseTest {

    @Given("the employee profile wizard updates fields including contact details, emergency contacts, marital status, and nationality")
    public void updatePersonalFields() {
        loginPage
                .enterUsername(HooksHandler.csv.getRowData(0).get("username"))
                .enterPassword(HooksHandler.csv.getRowData(0).get("password"))
                .clickLoginButton();

        new DashboardPage(driver)
                .clickMyInfo();

        HooksHandler.verificationPage = new MyInfoPage(driver)
                .updatePersonalDetails(HooksHandler.csv.getRowData(0).get("nationality"), HooksHandler.csv.getRowData(0).get("maritalStatus"))
                .navigateToContactDetails()
                .updateContactDetails(HooksHandler.csv.getRowData(0).get("street"))
                .navigateToEmergencyContacts()
                .addEmergencyContact(HooksHandler.csv.getRowData(0).get("emergencyName"), HooksHandler.csv.getRowData(0).get("emergencyRelationship"), HooksHandler.csv.getRowData(0).get("emergencyPhone"));
    }

    @Then("the user cleanly logs out of the current active employee session")
    public void employeeLogsOut() {
        HooksHandler.verificationPage
                .logout();
    }
}