package tests.employeeDependents.ManageDependents;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import tests.base.BaseTest;

public class ManageDependentsStepDef extends BaseTest {


    @Given("the admin authenticates and opens the dependents management layout for employee {string}")
    public void adminOpensDependentsLayout(String employeeName) {
        verificationPage = loginPage
                .enterUsername(jsonFileManager.getNestedValue("admin", "username"))
                .enterPassword(jsonFileManager.getNestedValue("admin", "password"))
                .clickLoginButton()
                .pressOnPimButton()
                .searchAndOpenEmployeeDetails(employeeName)
                .navigateToDependents();
    }

    @When("the admin populates the new dependent entries from the system configuration")
    public void adminAddsNewDependentRecord() {
        verificationPage.addDependent(
                jsonFileManager.getNestedValue("userStory3", "dependentName"),
                jsonFileManager.getNestedValue("userStory3", "dependentRelationship"),
                jsonFileManager.getNestedValue("userStory3", "dependentDob")
        );
    }

    @Then("the system validates the presence of the freshly added dependent record in the results table")
    public void systemValidatesDependentPresence() {
        Assert.assertTrue(
                verificationPage.isDependentPresent(
                        jsonFileManager.getNestedValue("userStory3", "dependentName"),
                        jsonFileManager.getNestedValue("userStory3", "dependentRelationship")
                ),
                "Saved dependent record could not be found under table entries!"
        );
    }
}