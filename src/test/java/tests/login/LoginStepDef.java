package tests.login;

import tests.base.BaseTest;
import tests.driver.WebDriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import static tests.reuse.SwagLabsObjects.loginPage;
import static tests.reuse.SwagLabsObjects.products;

public class LoginStepDef extends BaseTest {

    @Given("site is opened")
    public void siteIsOpen(){
        WebDriverFactory.getDriver(configManager.getValue("browser")).get(configManager.getValue("url"));
    }

    @When("enter {string} and {string}")
    public void enterAndEnter(String user,String pass){
        loginPage.enterUserName(configManager.getValue("username"));
        loginPage.enterPassword(configManager.getValue("password"));
    }

    @When("click on login button")
    public void clickOnLoginButton(){
        products = loginPage.clickOnLoginButton();
    }

    @Then("home title is appeared")
    public void verifyTheLoginSuccessfully(){
        Assert.assertTrue(products.getTitle().isDisplayed());

    }
}
