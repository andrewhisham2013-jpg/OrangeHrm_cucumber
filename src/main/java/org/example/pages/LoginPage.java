package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By invalidCredentialsErrorMessage = By.xpath("//p[contains(@class, 'oxd-alert-content-text')]");
    private final By fieldRequiredValidationMessage = By.xpath("//span[contains(@class, 'oxd-input-group__message')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterUsername(String username) {
        findElement(usernameField).sendKeys(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        findElement(passwordField).sendKeys(password);
        return this;
    }

    public DashboardPage clickLoginButton() {
        findElement(loginButton).click();
        return new DashboardPage(driver);
    }
}