package tests.userAssignment.Dashboard;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import tests.base.BaseTest;

public class DashboardStepDef extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(DashboardStepDef.class);

    @Given("the admin logs in and switches directly to the PIM module view")
    public void adminLogsInAndSwitchesToPim() {
        log.info("Starting test: Navigate to PIM module using method chaining");

        pimPage = loginPage
                .enterUsername(jsonFileManager.getNestedValue("admin", "username"))
                .enterPassword(jsonFileManager.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton();
    }

    @Then("the system should display the header title matching validation criteria")
    public void systemDisplaysHeaderTitleMatchingCriteria() {
        Assert.assertEquals(pimPage.getHeaderTitleText(), "PIM",
                "The page header title does not indicate you are inside the PIM module!");

        log.info("Finished test: Navigate to PIM module");
    }
}