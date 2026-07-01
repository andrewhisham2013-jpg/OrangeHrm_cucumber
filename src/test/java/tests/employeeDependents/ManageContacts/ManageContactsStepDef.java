package tests.employeeDependents.ManageContacts;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import tests.base.BaseTest;

public class ManageContactsStepDef extends BaseTest {



    @Given("the admin authenticates and navigates to the emergency contacts interface for employee {string}")
    public void adminNavigatesToEmergencyContacts(String employeeName) {
        myInfo = loginPage
                .enterUsername(jsonFileManager.getNestedValue("admin", "username"))
                .enterPassword(jsonFileManager.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .searchAndOpenEmployeeDetails(employeeName)
                .navigateToEmergencyContacts();
    }

    @When("the admin creates a new emergency contact record with configured details")
    public void adminAddsEmergencyContact() {
        myInfo
                .addEmergencyContact(
                        jsonFileManager.getNestedValue("userStory3", "emergencyName"),
                        jsonFileManager.getNestedValue("userStory3", "emergencyRelationship"),
                        jsonFileManager.getNestedValue("userStory3", "emergencyMobile")
                );
    }

    @Then("the admin updates the existing contact mobile record safely")
    public void adminEditsExistingContact() {
        myInfo
                .editExistingEmergencyContact("0123456789");
    }
}