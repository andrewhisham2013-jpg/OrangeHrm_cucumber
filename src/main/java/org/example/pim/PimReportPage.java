package org.example.pim;

import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PimReportPage extends BasePage {

    private final By addButton = By.xpath("//button[contains(.,'Add')]");
    private final By reportNameInput = By.xpath("//label[text()='Report Name']/following::input[1]");
    private final By selectionCriteriaDropdown = By.xpath("//label[text()='Selection Criteria']/following::div[contains(@class,'oxd-select-text')][1]");
    private final By addCriteriaButton = By.xpath("//label[text()='Selection Criteria']/following::button[contains(@class,'oxd-icon-button')][1]");
    private final By dynamicEmployeeNameInput = By.xpath("//div[contains(@class, 'oxd-autocomplete-text-input')]/input");
    private final By autocompleteOption = By.xpath("//div[@role='option' or contains(@class, 'oxd-autocomplete-option')]");
    private final By displayFieldGroupDropdown = By.xpath("//label[text()='Select Display Field Group']/following::div[contains(@class,'oxd-select-text')][1]");
    private final By displayFieldDropdown = By.xpath("//label[text()='Select Display Field']/following::div[contains(@class,'oxd-select-text')][1]");
    private final By addDisplayFieldButton = By.xpath("//label[text()='Select Display Field']/following::button[contains(@class,'oxd-icon-button')][1]");
    private final By saveButton = By.xpath("//button[@type='submit'][text()=' Save ']");
    private final By reportHeaderTitle = By.xpath("//div[@class='oxd-layout-context']//*[contains(@class, 'title')]");
    private final By formLoader = By.className("oxd-form-loader");
    private final By toastMessage = By.className("oxd-toast");
    private final By employeeNameOption = By.xpath("//div[@role='listbox']//*[text()='Employee Name']");

    private By getListBoxOptionLocator(String optionText) {
        String sanitizedText = optionText.replace("'", "\\'");
        return By.xpath("//div[@role='listbox']//*[text()='" + sanitizedText + "']");
    }

    public PimReportPage(WebDriver driver) {
        super(driver);
    }

    public PimReportPage clickAddReport() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        return this;
    }

    public PimReportPage enterReportName(String reportName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(reportNameInput));
        nameField.sendKeys(reportName);
        return this;
    }

    public PimReportPage selectSelectionCriteria() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement criteriaDropdown = wait.until(ExpectedConditions.elementToBeClickable(selectionCriteriaDropdown));
        criteriaDropdown.click();
        wait.until(ExpectedConditions.elementToBeClickable(employeeNameOption)).click();
        return this;
    }

    public PimReportPage clickAddCriteria() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(addCriteriaButton)).click();
        return this;
    }

    public PimReportPage configureReportDetails(String reportName) {
        enterReportName(reportName);
        selectSelectionCriteria();
        clickAddCriteria();
        return this;
    }

    public PimReportPage typeEmployeeCriterionName(String employeeName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(formLoader));
        WebElement empInput = wait.until(ExpectedConditions.elementToBeClickable(dynamicEmployeeNameInput));
        empInput.click();
        empInput.sendKeys(employeeName);
        return this;
    }

    public PimReportPage selectEmployeeAutocompleteOption(String employeeName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement employeeOption = wait.until(d -> {
            java.util.List<WebElement> options = d.findElements(autocompleteOption);
            for (WebElement option : options) {
                if (option.getText().trim().equalsIgnoreCase(employeeName)) {
                    return option;
                }
            }
            return null;
        });
        employeeOption.click();
        return this;
    }

    public PimReportPage enterEmployeeCriterion(String employeeName) {
        typeEmployeeCriterionName(employeeName);
        selectEmployeeAutocompleteOption(employeeName);
        return this;
    }

    public PimReportPage selectDisplayFieldGroup(String fieldGroup) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(displayFieldGroupDropdown)).click();
        By groupOption = getListBoxOptionLocator(fieldGroup);
        wait.until(ExpectedConditions.elementToBeClickable(groupOption)).click();
        return this;
    }

    public PimReportPage selectDisplayField(String displayField) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(displayFieldDropdown)).click();
        By fieldOption = getListBoxOptionLocator(displayField);
        wait.until(ExpectedConditions.elementToBeClickable(fieldOption)).click();
        return this;
    }

    public PimReportPage clickAddDisplayField() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(addDisplayFieldButton)).click();
        return this;
    }

    public PimReportPage selectDisplayFields(String fieldGroup, String displayField) {
        selectDisplayFieldGroup(fieldGroup);
        selectDisplayField(displayField);
        clickAddDisplayField();
        return this;
    }

    public PimReportPage clickSaveButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        return this;
    }

    public PimReportPage verifyReportSavedToast() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(toastMessage));
        return this;
    }

    public void clickSaveReport() {
        clickSaveButton();
        verifyReportSavedToast();
    }

    public String getReportHeaderTitleText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(formLoader));
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(reportHeaderTitle));
        wait.until(d -> !titleElement.getText().trim().isEmpty());
        return titleElement.getText().trim();
    }
}