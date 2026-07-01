package tests.timeSheet.TimeSheetSubmission;

import org.example.time.TimePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import tests.base.BaseTest;
import tests.hooks.HooksHandler;

public class TimesheetSubmissionStepDef extends BaseTest {

    @Given("the employee logs in and opens the timesheet editor layout")
    public void employeeLogsInAndOpensLayout() {
        loginPage
                .enterUsername(HooksHandler.csv.getRowData(0).get("username"))
                .enterPassword(HooksHandler.csv.getRowData(0).get("password"))
                .clickLoginButton()
                .pressOnTimeButton();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("oxd-form-loader")));

        new TimePage(driver).clickEditTimesheet();
    }

    @When("the employee updates the project row log entries and saves the records")
    public void employeeUpdatesProjectAndSaves() {
        new TimePage(driver)
                .enterTimesheetRowData(
                        HooksHandler.json.getNestedValue("userStory4", "projectName"),
                        HooksHandler.json.getNestedValue("userStory4", "activityName"),
                        "20", "4", "6", "9"
                )
                .clickSaveTimesheet();
    }
}