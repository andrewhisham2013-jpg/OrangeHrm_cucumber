package tests.userAssignment.AddEmployee;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.pim.AddEmployeePage;
import tests.base.BaseTest;
import tests.hooks.HooksHandler;

public class AddEmployeeStepDef extends BaseTest {

    @Given("the admin navigates to the PIM creation screen")
    public void userNavigatesToPimAndSelectsAddEmployee() {
        loginPage
                .enterUsername(HooksHandler.json.getNestedValue("admin", "username"))
                .enterPassword(HooksHandler.json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .clickAddEmployeeButton();
    }

    @When("the admin populates the creation wizard fields with employee details")
    public void userInputsEmployeeDetails() {
        new AddEmployeePage(driver)
                .enterFirstName(HooksHandler.json.getNestedValue("newEmployee", "firstName"))
                .enterLastName(HooksHandler.json.getNestedValue("newEmployee", "lastName"))
                .toggleCreateLoginDetails()
                .enterUsername(HooksHandler.json.getNestedValue("newEmployee", "username"))
                .enterEmployeeId(HooksHandler.json.getNestedValue("newEmployee", "employeeId"))
                .enterPassword(HooksHandler.json.getNestedValue("newEmployee", "password"))
                .enterConfirmPassword(HooksHandler.json.getNestedValue("newEmployee", "confirmPassword"));
    }

    @Then("the user saves the profile safely")
    public void userSavesTheProfileSafely() {
        new AddEmployeePage(driver)
                .clickSaveButton();
    }
}