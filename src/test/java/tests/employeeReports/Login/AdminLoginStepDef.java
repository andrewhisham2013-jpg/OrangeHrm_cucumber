package tests.employeeReports.Login;

import org.example.pages.DashboardPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import tests.base.BaseTest;
import org.testng.Assert;

public class AdminLoginStepDef extends BaseTest {


    @Given("the admin enters valid credentials from configuration files")
    public void adminEntersCredentials() {
        dashboard = loginPage
                .enterUsername(jsonFileManager.getNestedValue("admin", "username"))
                .enterPassword(jsonFileManager.getNestedValue("admin", "password"))
                .clickLoginButton();
    }

    @Then("the admin verifies that the dashboard page loads successfully")
    public void verifyDashboardLoading() {
        Assert.assertNotNull(dashboard, "Failed to authenticate and load the Dashboard Page.");
    }
}