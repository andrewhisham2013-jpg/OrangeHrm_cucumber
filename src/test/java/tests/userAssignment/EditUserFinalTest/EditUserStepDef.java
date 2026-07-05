package tests.userAssignment.EditUserFinalTest;

import org.example.pages.DashboardPage;
import org.example.pim.AddEmployeePage;
import org.example.admin.AdminPage;
import org.example.admin.EditUserPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import tests.base.BaseTest;
import tests.hooks.HooksHandler;

public class EditUserStepDef extends BaseTest {

    private String staticUsername;

    @Given("the user logs in using credentials from json")
    public void userLogsInUsingCredentialsFromJson() {
        staticUsername = HooksHandler.json.getNestedValue("newEmployee", "username") + System.currentTimeMillis();

        loginPage
                .enterUsername(HooksHandler.json.getNestedValue("admin", "username"))
                .enterPassword(HooksHandler.json.getNestedValue("admin", "password"))
                .clickLoginButton();
    }

    @Given("the user navigates to the PIM module and selects Add Employee")
    public void userNavigatesToPimAndSelectsAddEmployee() {
        new DashboardPage(driver)
                .pressOnPimButton()
                .clickAddEmployeeButton();
    }

    @When("the user inputs the new employee name details and unique username configuration")
    public void userInputsEmployeeDetails() {
        new AddEmployeePage(driver)
                .enterFirstName(HooksHandler.json.getNestedValue("newEmployee", "firstName"))
                .enterLastName(HooksHandler.json.getNestedValue("newEmployee", "lastName"))
                .enterEmployeeId(HooksHandler.json.getNestedValue("newEmployee", "employeeId"))
                .toggleCreateLoginDetails()
                .enterUsername(staticUsername)
                .enterPassword(HooksHandler.json.getNestedValue("newEmployee", "password"))
                .enterConfirmPassword(HooksHandler.json.getNestedValue("newEmployee", "confirmPassword"));
    }

    @When("the user saves the profile and modifies their access permissions in Admin page")
    public void userSavesProfileAndModifiesPermissions() {
        new AddEmployeePage(driver).clickSaveButton();

        new DashboardPage(driver)
                .pressOnAdminPage()
                .searchUser(staticUsername)
                .clickEditUser(staticUsername)
                .selectUserRole("Admin");
    }

    @When("the user logs out and re-authenticates with the upgraded profile")
    public void userLogsOutAndReAuthenticates() {
        new EditUserPage(driver)
                .clickSave()
                .clickLogout()
                .enterUsername(staticUsername)
                .enterPassword(HooksHandler.json.getNestedValue("newEmployee", "password"))
                .clickLoginButton();
    }

    @Then("the upgraded user should be able to access administrative controls safely")
    public void upgradedUserVerifiesAdminAccess() {
        new AdminPage(driver)
                .pressOnAdminPage()
                .isAdminButtonDisplayed();
    }
}