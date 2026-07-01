package org.example.admin;

import org.example.pages.BasePage;
import org.example.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class EditUserPage extends BasePage {

    private final By userRoleDropdown = By.cssSelector(".oxd-select-text");
    private final By saveButton = By.cssSelector("button[type='submit']");
    private final By userDropdown = By.className("oxd-userdropdown-tab");
    private final By logoutLink = By.xpath("//a[text()='Logout']");
    private final By toastSuccessMessage = By.id("oxd-toast-container");

    private By getRoleOptionLocator(String roleName) {
        return By.xpath("//div[@role='listbox']//*[text()='" + roleName + "']");
    }

    public EditUserPage(WebDriver driver) {
        super(driver);
    }

    public EditUserPage selectUserRole(String roleName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(userRoleDropdown)).click();

        By roleOption = getRoleOptionLocator(roleName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(roleOption));
        findElement(roleOption).click();

        return this;
    }

    public EditUserPage clickSave() {
        findElement(saveButton).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(toastSuccessMessage));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(toastSuccessMessage));
        } catch (Exception e) {
            wait.until(ExpectedConditions.elementToBeClickable(userDropdown));
        }

        return this;
    }

    public LoginPage clickLogout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(userDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();

        return new LoginPage(driver);
    }
}
