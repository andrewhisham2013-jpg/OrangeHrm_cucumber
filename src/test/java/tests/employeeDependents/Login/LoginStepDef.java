package tests.employeeDependents.Login;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import tests.base.BaseTest;

public class LoginStepDef extends BaseTest {


    @Given("the admin enters system credentials and accesses the detailed info panel for employee {string}")
    public void adminAccessesEmployeeDetailsPanel(String employeeName) {
        myInfo = loginPage
                .enterUsername(jsonFileManager.getNestedValue("admin", "username"))
                .enterPassword(jsonFileManager.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .searchAndOpenEmployeeDetails(employeeName);
    }

    @Then("the system should confirm the employee info panel loaded successfully")
    public void systemConfirmsEmployeePanelLoaded() {
        Assert.assertNotNull(myInfo, "Failed to navigate to the Employee Info panel.");
    }
}