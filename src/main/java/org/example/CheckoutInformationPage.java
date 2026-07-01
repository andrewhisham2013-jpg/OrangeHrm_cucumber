package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutInformationPage extends BasePage{

    private By pageTitle = By.xpath("//span[@class='title']");
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By cancelButton = By.id("cancel");
    private By cartButton = By.xpath("//*[@id='shopping_cart_container']/a");

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageTitle() {
        return findElement(pageTitle);
    }
    public WebElement getFirstName() {
        return findElement(firstName);
    }
    public WebElement getLastName() {
        return findElement(lastName);
    }
    public WebElement getPostalCode() {
        return findElement(postalCode);
    }
    public WebElement getContinueButton() {
        return findElement(continueButton);
    }
    public WebElement getCancelButton() {
        return findElement(cancelButton);
    }
    public WebElement getCartButton() {
        return findElement(cartButton);
    }

    public void enterFirstName(String firstname) {
        findElement(firstName).clear();
        findElement(firstName).sendKeys(firstname);
    }
    public void enterLastName(String lastname) {
        findElement(lastName).clear();
        findElement(lastName).sendKeys(lastname);
    }
    public void enterPostalCode(String postcode) {
        findElement(postalCode).clear();
        findElement(postalCode).sendKeys(postcode);
    }

    public CheckoutOverviewPage ClickOnContinueButton() {
        findElement(continueButton).click();
        return new CheckoutOverviewPage(driver);
    }
}
