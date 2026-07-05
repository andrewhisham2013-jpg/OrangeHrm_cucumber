package tests.employeeProfile.Login;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.example.pages.DashboardPage;
import tests.hooks.HooksHandler;
import tests.base.BaseTest;
import java.util.Map;

public class LoginEmployeeStepDef extends BaseTest {

    @Given("the user logs in as an ESS employee with configuration parameters")
    public void logInAsEmployee() {
        Map<String, String> data = HooksHandler.csv.getRowData(0);

        loginPage
                .enterUsername(data.get("username"))
                .enterPassword(data.get("password"))
                .clickLoginButton();
    }

    @Then("the user transitions to the PIM My Info Personal Details screen successfully")
    public void navigateToMyInfo() {
        new DashboardPage(driver)
                .clickMyInfo();
    }
}