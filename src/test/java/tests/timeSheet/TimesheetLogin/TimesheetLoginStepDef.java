package tests.timeSheet.TimesheetLogin;

import org.example.pages.DashboardPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import tests.base.BaseTest;
import tests.hooks.HooksHandler;

public class TimesheetLoginStepDef extends BaseTest {

    @Given("the employee logs into the system using valid data")
    public void employeeLogsIn() {
        loginPage
                .enterUsername(HooksHandler.csv.getRowData(0).get("username"))
                .enterPassword(HooksHandler.csv.getRowData(0).get("password"))
                .clickLoginButton();
    }

    @Then("the employee navigates to the time tracking section")
    public void employeeNavigatesToTimeTracking() {
        new DashboardPage(driver).pressOnTimeButton();
    }
}