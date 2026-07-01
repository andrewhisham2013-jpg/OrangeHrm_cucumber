package tests.userAssignment.Admin;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.example.pages.DashboardPage;
import org.example.pim.AddEmployeePage;
import tests.base.BaseTest;

public class AdminStepDef extends BaseTest {

    @Given("the administrator logs into the system with core credentials")
    public void administratorLogsIntoSystem() {
        loginPage
                .enterUsername(jsonFileManager.getNestedValue("admin", "username"))
                .enterPassword(jsonFileManager.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .clickAddEmployeeButton();
    }

    @When("the administrator creates an employee with full names and unique login data")
    public void administratorCreatesEmployeeWithFullNames() {
        new AddEmployeePage(driver)
                .enterFirstName(jsonFileManager.getNestedValue("newEmployee", "firstName"))
                .enterMiddleName("osama")
                .enterLastName(jsonFileManager.getNestedValue("newEmployee", "lastName"))
                .toggleCreateLoginDetails()
                .enterUsername(jsonFileManager.getNestedValue("newEmployee", "username"))
                .enterPassword(jsonFileManager.getNestedValue("newEmployee", "password"))
                .enterEmployeeId(jsonFileManager.getNestedValue("newEmployee", "employeeId"))
                .enterConfirmPassword(jsonFileManager.getNestedValue("newEmployee", "confirmPassword"));
    }

    @Then("the administrator saves the profile and immediately opens it for editing inside Admin module")
    public void administratorSavesAndModifiesProfile() {
        new AddEmployeePage(driver).clickSaveButton();

        new DashboardPage(driver)
                .pressOnAdminPage()
                .searchUser(jsonFileManager.getNestedValue("newEmployee", "username"))
                .clickEditUser(jsonFileManager.getNestedValue("newEmployee", "username"));
    }
}