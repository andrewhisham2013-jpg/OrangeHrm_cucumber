package tests.userAssignment.Login;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.example.pages.DashboardPage;
import tests.base.BaseTest;
import org.testng.Assert;

public class LoginStepDef extends BaseTest {

    @Given("the admin user requests authentication using configuration properties")
    public void adminUserRequestsAuthentication() {
        loginPage
                .enterUsername(jsonFileManager.getNestedValue("admin", "username"))
                .enterPassword(jsonFileManager.getNestedValue("admin", "password"))
                .clickLoginButton();
    }

    @Then("the user should be redirected to the main dashboard module safely")
    public void userShouldBeRedirectedToDashboard() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        Assert.assertTrue(dashboardPage.isDashboardMenuDisplayed(), "Dashboard menu layout failed to load!");
    }
}