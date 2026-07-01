package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutCompletePage extends BasePage{

    private By pageTitle = By.xpath("//span[@data-test='title']");
    private By thankfulMessage = By.xpath("//*[@data-test='complete-header']");
    private By noteMessage = By.xpath("//*[@data-test='complete-text']");
    private By cartButton = By.xpath("//*[@id='shopping_cart_container']/a");
    private By backHomeButton = By.id("back-to-products");
    private By menuButton = By.id("react-burger-menu-btn");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageTitle() {
        return findElement(pageTitle);
    }
    public WebElement getThankfulMessage() {
        return findElement(thankfulMessage);
    }
    public WebElement getNoteMessage() {
        return findElement(noteMessage);
    }
    public WebElement getBackHomeButton() {
        return findElement(backHomeButton);
    }
    public WebElement getCartButton() {
        return findElement(cartButton);
    }
    public WebElement getMenuButton() {
        return findElement(menuButton);
    }
}
