package org.example.admin;

import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.time.Duration;

public class AdminPage extends BasePage {

    private final By adminPageButton = By.xpath("//span[text()='Admin']/parent::a");
    private final By searchUsernameField = By.xpath("//div[div[label[text()='Username']]]//input");
    private final By searchButton = By.xpath("//button[@type='submit']");

    private By getEditButtonLocator(String username) {
        String sanitizedUsername = username.replace("'", "\\'");
        return By.xpath("//div[@class='oxd-table-card' and .//*[text()='" + sanitizedUsername + "']]//button[i[contains(@class,'pencil')]]");
    }

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public AdminPage pressOnAdminPage() {
        findElement(adminPageButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchUsernameField));
        return this;
    }

    public AdminPage searchUser(String username) {
        findElement(searchUsernameField).clear();
        findElement(searchUsernameField).sendKeys(username);
        findElement(searchButton).click();
        return this;
    }

    public EditUserPage clickEditUser(String username) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(getEditButtonLocator(username))).click();

        return new EditUserPage(driver);
    }

    public AdminPage isAdminButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        boolean isDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(adminPageButton)).isDisplayed();

        Assert.assertTrue(isDisplayed, "Login verification failed: The Admin button is not visible for the newly updated user.");

        return this;
    }
}