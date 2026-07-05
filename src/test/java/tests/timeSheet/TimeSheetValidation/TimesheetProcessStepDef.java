package tests.timeSheet.TimeSheetValidation;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.DashboardPage;
import org.testng.Assert;
import tests.base.BaseTest;


import static tests.hooks.HooksHandler.json;

public class TimesheetProcessStepDef extends BaseTest {




    @Given("the employee logs in and navigates to the timesheet editor module")
    public void employeeLogsInAndNavigates() {
        loginPage
                .enterUsername(json.getNestedValue("newEmployee", "username"))
                .enterPassword(json.getNestedValue("newEmployee", "password"))
                .clickLoginButton()
                .pressOnTimeButton()
                .clickEditTimesheet();
    }

    @When("the employee logs out and the admin authenticates into the dashboard")
    public void employeeLogsOutAndAdminLogsIn() {
        new DashboardPage(driver).logout();

        timePage = loginPage
                .enterUsername(json.getNestedValue("admin", "username"))
                .enterPassword(json.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnTimeButton()
                .filterByEmployee(json.getNestedValue("newEmployee", "firstName") + " " + json.getNestedValue("newEmployee", "lastName"));
    }

    @Then("the admin verifies that the recorded hours per day and total accumulation are accurate")
    public void adminVerifiesHoursMatrix() {
        Assert.assertEquals(timePage.getStaticHoursByDayIndex(0), "20:00", "Monday hours mismatch!");
        Assert.assertEquals(timePage.getStaticHoursByDayIndex(1), "04:00", "Tuesday hours mismatch!");
        Assert.assertEquals(timePage.getStaticHoursByDayIndex(2), "06:00", "Wednesday hours mismatch!");
        Assert.assertEquals(timePage.getStaticHoursByDayIndex(3), "09:00", "Thursday hours mismatch!");
        Assert.assertEquals(timePage.getTotalHoursString(), "39:00", "Total accumulated hours column mismatch!");

        timePage.submitTimesheet();
    }
}