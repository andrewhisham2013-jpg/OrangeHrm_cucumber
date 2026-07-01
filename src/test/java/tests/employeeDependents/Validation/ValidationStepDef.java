package tests.employeeDependents.Validation;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.DashboardPage;
import org.testng.Assert;
import tests.base.BaseTest;
import tests.hooks.HooksHandler;

public class ValidationStepDef extends BaseTest {

    @Given("the user logs in as Admin HR to manage records")
    public void logInAsAdmin() {
        loginPage
                .enterUsername(HooksHandler.json.getNestedValue("admin", "username"))
                .enterPassword(HooksHandler.json.getNestedValue("admin", "password"))
                .clickLoginButton();
    }

    @Given("the admin navigates to the PIM Employee List and selects the target employee")
    public void adminSelectsEmployee() {
        HooksHandler.verificationPage = new DashboardPage(driver)
                .pressOnPimButton()
                .searchAndOpenEmployeeDetails(HooksHandler.json.getNestedValue("newEmployee", "firstName") + " " + HooksHandler.json.getNestedValue("newEmployee", "lastName"));
    }

    @When("the admin adds a new emergency contact and edits existing contacts")
    public void addAndEditEmergencyContacts() {
        HooksHandler.verificationPage
                .navigateToEmergencyContacts()
                .addEmergencyContact(
                        HooksHandler.json.getNestedValue("userStory3", "emergencyName"),
                        HooksHandler.json.getNestedValue("userStory3", "emergencyRelationship"),
                        HooksHandler.json.getNestedValue("userStory3", "emergencyMobile")
                )
                .editExistingEmergencyContact("0123456789");
    }

    @When("the admin navigates to dependents to add a new dependent record")
    public void addDependentRecord() {
        HooksHandler.verificationPage
                .navigateToDependents()
                .addDependent(
                        HooksHandler.json.getNestedValue("userStory3", "dependentName"),
                        HooksHandler.json.getNestedValue("userStory3", "dependentRelationship"),
                        HooksHandler.json.getNestedValue("userStory3", "dependentDob")
                );
    }

    @Then("the admin verifies that both emergency contacts and dependents are correctly saved")
    public void verifyDataIsSaved() {
        HooksHandler.verificationPage = HooksHandler.verificationPage
                .logout()
                .enterUsername(HooksHandler.json.getNestedValue("admin", "username"))
                .enterPassword(HooksHandler.json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .searchAndOpenEmployeeDetails(HooksHandler.json.getNestedValue("newEmployee", "firstName") + " " + HooksHandler.json.getNestedValue("newEmployee", "lastName"))
                .navigateToEmergencyContacts();

        Assert.assertTrue(HooksHandler.verificationPage.isEmergencyContactPresent(
                        HooksHandler.json.getNestedValue("userStory3", "emergencyName"),
                        HooksHandler.json.getNestedValue("userStory3", "emergencyRelationship"),
                        "0123456789"),
                "Saved emergency contact record could not be found under table entries!");

        Assert.assertTrue(HooksHandler.verificationPage.navigateToDependents().isDependentPresent(
                        HooksHandler.json.getNestedValue("userStory3", "dependentName"),
                        HooksHandler.json.getNestedValue("userStory3", "dependentRelationship")),
                "Saved dependent record could not be found under table entries!");
    }
}