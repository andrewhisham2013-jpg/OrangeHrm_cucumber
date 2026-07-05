package org.example.pim;

import org.example.admin.AdminPage;
import org.example.pages.BasePage;
import org.example.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddEmployeePage extends BasePage {

    private final By firstNameField = By.name("firstName");
    private final By middleNameField = By.name("middleName");
    private final By lastNameField = By.name("lastName");
    private final By employeeIdField = By.xpath("//div[div[label[text()='Employee Id']]]//input");
    private final By loginDetailsToggle = By.cssSelector(".oxd-switch-input");
    private final By usernameField = By.xpath("//div[div[label[text()='Username']]]//input");
    private final By passwordField = By.xpath("//div[div[label[text()='Password']]]//input[@type='password']");
    private final By confirmPasswordField = By.xpath("//div[div[label[text()='Confirm Password']]]//input[@type='password']");
    private final By saveButton = By.xpath("//button[@type='submit']");
    private final By personalDetailsHeader = By.xpath("//h6[text()='Personal Details']");
    private final By formLoader = By.className("oxd-form-loader");
    private final By userDropdown = By.className("oxd-userdropdown-name");
    private final By logoutLink = By.xpath("//a[text()='Logout']");

    public AddEmployeePage(WebDriver driver) {
        super(driver);
    }

    public AddEmployeePage enterFirstName(String firstName) {
        findElement(firstNameField).sendKeys(firstName);
        return this;
    }

    public AddEmployeePage enterMiddleName(String middleName) {
        findElement(middleNameField).sendKeys(middleName);
        return this;
    }

    public AddEmployeePage enterLastName(String lastName) {
        findElement(lastNameField).sendKeys(lastName);
        return this;
    }

    public AddEmployeePage enterEmployeeId(String employeeId) {
        findElement(employeeIdField).clear();
        findElement(employeeIdField).sendKeys(employeeId);
        return this;
    }

    public AddEmployeePage toggleCreateLoginDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(formLoader));
        wait.until(ExpectedConditions.elementToBeClickable(loginDetailsToggle)).click();
        return this;
    }

    public AddEmployeePage enterUsername(String username) {
        findElement(usernameField).sendKeys(username);
        return this;
    }

    public AddEmployeePage enterPassword(String password) {
        findElement(passwordField).sendKeys(password);
        return this;
    }

    public AddEmployeePage enterConfirmPassword(String confirmPassword) {
        findElement(confirmPasswordField).sendKeys(confirmPassword);
        return this;
    }

    public AdminPage clickSaveButton() {
        findElement(saveButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(personalDetailsHeader));
        return new AdminPage(driver);
    }

    public LoginPage logout() {
        findElement(userDropdown).click();
        findElement(logoutLink).click();
        return new LoginPage(this.driver);
    }
}
