package org.example.myinfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.BasePage;
import org.example.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import static java.lang.invoke.MethodHandles.lookup;

public class MyInfoPage extends BasePage {
    private static final Logger log = LogManager.getLogger(lookup().lookupClass());

    private final By formLoader = By.className("oxd-form-loader");
    private final By contactDetailsTab = By.xpath("//a[text()='Contact Details']");
    private final By emergencyContactsTab = By.xpath("//a[text()='Emergency Contacts']");
    private final By nationalityDropdown = By.xpath("//label[text()='Nationality']/following::div[contains(@class,'oxd-select-text')][1]");
    private final By maritalStatusDropdown = By.xpath("//label[text()='Marital Status']/following::div[contains(@class,'oxd-select-text')][1]");
    private final By savePersonalDetailsBtn = By.xpath("//h6[text()='Personal Details']/following::button[@type='submit'][1]");
    private final By street1Input = By.xpath("//label[text()='Street 1']/following::input[1]");
    private final By saveContactDetailsBtn = By.xpath("//form//button[@type='submit']");
    private final By addEmergencyBtn = By.xpath("//button[contains(.,'Add')]");
    private final By emergencyNameInput = By.xpath("//label[text()='Name']/following::input[1]");
    private final By emergencyRelationshipInput = By.xpath("//label[text()='Relationship']/following::input[1]");
    private final By emergencyHomePhoneInput = By.xpath("//label[text()='Home Telephone']/following::input[1]");
    private final By saveEmergencyBtn = By.xpath("//button[@type='submit'][contains(.,'Save')]");
    private final By dependentsTab = By.xpath("//a[text()='Dependents']");
    private final By dependentNameInput = By.xpath("//label[text()='Name']/following::input[1]");
    private final By dependentRelationshipDropdown = By.xpath("//label[text()='Relationship']/following::div[contains(@class,'oxd-select-text')][1]");
    private final By dependentDobInput = By.xpath("//label[text()='Date of Birth']/following::input[1]");
    private final By userDropdown = By.className("oxd-userdropdown-name");
    private final By logoutLink = By.xpath("//a[text()='Logout']");
    private final By editContactBtn = By.xpath("(//div[@role='row'])[2]//button[contains(@class, 'oxd-icon-button')][2]");
    private final By toastContainer = By.className("oxd-toast");
    private final By selectedNationalityText = By.xpath("//label[text()='Nationality']/following::div[contains(@class,'oxd-select-text-input')][1]");
    private final By selectedMaritalStatusText = By.xpath("//label[text()='Marital Status']/following::div[contains(@class,'oxd-select-text-input')][1]");

    private By getDropdownOptionLocator(String optionText) {
        return By.xpath("//div[@role='listbox']//*[text()='" + optionText + "']");
    }

    private By getEmergencyContactRowLocator(String name, String relationship, String phone, String rawNumber) {
        return By.xpath("//div[@role='row'][descendant::*[contains(text(),'" + name + "')] and descendant::*[contains(text(),'" + relationship + "')] and (descendant::*[contains(text(),'" + phone + "')] or descendant::*[contains(text(),'" + rawNumber + "')])]");
    }

    private By getDependentRowLocator(String name, String relationship) {
        return By.xpath("//div[@role='row'][descendant::*[contains(text(),'" + name + "')] and descendant::*[contains(text(),'" + relationship + "')]]");
    }

    public MyInfoPage(WebDriver driver){
        super(driver);
    }

    public LoginPage logout() {
        findElement(userDropdown).click();
        findElement(logoutLink).click();
        return new LoginPage(this.driver);
    }

    private void selectCustomDropdownOption(By dropdownLocator, String optionText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(formLoader));
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownLocator));
        new Actions(driver).moveToElement(dropdown).perform();
        wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
        By optionLocator = getDropdownOptionLocator(optionText);
        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
        log.info("Successfully selected dropdown option: {}", optionText);
    }

    public MyInfoPage selectNationality(String nationality) {
        selectCustomDropdownOption(nationalityDropdown, nationality);
        return this;
    }

    public MyInfoPage selectMaritalStatus(String maritalStatus) {
        selectCustomDropdownOption(maritalStatusDropdown, maritalStatus);
        return this;
    }

    public MyInfoPage clickSavePersonalDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement saveBtn = wait.until(ExpectedConditions.presenceOfElementLocated(savePersonalDetailsBtn));
        new Actions(driver).moveToElement(saveBtn).perform();
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
        log.info("Clicked personal details save button.");
        return this;
    }

    public MyInfoPage verifyPersonalDetailsSaved() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastContainer));
        org.testng.Assert.assertTrue(successToast.getText().contains("Success"), "Personal details save toast was not displayed!");
        log.info("Verified: Personal details saved successfully!");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(formLoader));
        return this;
    }

    public MyInfoPage updatePersonalDetails(String nationality, String maritalStatus) {
        log.info("Starting personal details update. Nationality: {}, Marital Status: {}", nationality, maritalStatus);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(formLoader));
        selectNationality(nationality);
        selectMaritalStatus(maritalStatus);
        clickSavePersonalDetails();
        verifyPersonalDetailsSaved();
        return this;
    }

    public MyInfoPage navigateToContactDetails() {
        log.info("Navigating to Contact Details tab.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(contactDetailsTab));
        new Actions(driver).moveToElement(element).perform();
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return this;
    }

    public MyInfoPage enterStreetAddress(String street) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement streetInput = wait.until(ExpectedConditions.elementToBeClickable(street1Input));
        streetInput.click();
        streetInput.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
        streetInput.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);
        streetInput.sendKeys(street);
        streetInput.sendKeys(org.openqa.selenium.Keys.TAB);

        wait.until(ExpectedConditions.attributeToBe(streetInput, "value", street));
        log.info("Verified street text value matches data source.");
        return this;
    }

    public MyInfoPage clickSaveContactDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement saveBtn = wait.until(ExpectedConditions.presenceOfElementLocated(saveContactDetailsBtn));
        new Actions(driver).moveToElement(saveBtn).perform();
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
        log.info("Clicked contact details save button.");
        return this;
    }

    public MyInfoPage verifyContactDetailsSaved() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastContainer));
        Assert.assertTrue(successToast.getText().contains("Success"), "Contact details save toast was not displayed!");
        log.info("Verified: Contact details saved successfully!");
        return this;
    }

    public MyInfoPage updateContactDetails(String street) {
        log.info("Starting contact details update. Street: {}", street);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(formLoader));
        enterStreetAddress(street);
        clickSaveContactDetails();
        verifyContactDetailsSaved();
        return this;
    }

    public MyInfoPage navigateToEmergencyContacts() {
        log.info("Navigating to Emergency Contacts tab.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(emergencyContactsTab));
        new Actions(driver).moveToElement(element).perform();
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return this;
    }

    public MyInfoPage clickAddEmergencyContact() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(addEmergencyBtn)).click();
        return this;
    }

    public MyInfoPage enterEmergencyContactDetails(String name, String relationship, String phone) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(emergencyNameInput));
        nameField.click();
        nameField.sendKeys(name);
        nameField.sendKeys(org.openqa.selenium.Keys.TAB);
        wait.until(ExpectedConditions.attributeToBe(nameField, "value", name));

        WebElement relationField = wait.until(ExpectedConditions.elementToBeClickable(emergencyRelationshipInput));
        relationField.click();
        relationField.sendKeys(relationship);
        relationField.sendKeys(org.openqa.selenium.Keys.TAB);
        wait.until(ExpectedConditions.attributeToBe(relationField, "value", relationship));

        WebElement phoneField = wait.until(ExpectedConditions.elementToBeClickable(emergencyHomePhoneInput));
        phoneField.click();
        phoneField.sendKeys(phone);
        phoneField.sendKeys(org.openqa.selenium.Keys.TAB);
        wait.until(ExpectedConditions.attributeToBe(phoneField, "value", phone));

        log.info("Verified all data inputs (including required phone) match data source.");
        return this;
    }

    public MyInfoPage clickSaveEmergencyContact() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveEmergencyBtn));
        saveBtn.click();
        log.info("Clicked emergency contact save button.");
        return this;
    }

    public MyInfoPage verifyEmergencyContactSaved() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastContainer));
        org.testng.Assert.assertTrue(successToast.getText().contains("Success"), "Emergency contact save toast was not displayed!");
        log.info("Verified: Emergency contact saved successfully!");
        return this;
    }

    public MyInfoPage addEmergencyContact(String name, String relationship, String phone) {
        log.info("Starting emergency contact addition. Name: {}, Relationship: {}, Phone: {}", name, relationship, phone);
        clickAddEmergencyContact();
        enterEmergencyContactDetails(name, relationship, phone);
        clickSaveEmergencyContact();
        verifyEmergencyContactSaved();
        return this;
    }

    public MyInfoPage clickEditExistingEmergencyContactIcon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(formLoader));
        wait.until(ExpectedConditions.elementToBeClickable(editContactBtn)).click();
        log.info("Clicked existing contact record action modify edit icon.");
        return this;
    }

    public MyInfoPage updateEmergencyContactPhone(String newPhone) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement phoneField = wait.until(ExpectedConditions.elementToBeClickable(emergencyHomePhoneInput));
        phoneField.click();
        phoneField.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
        phoneField.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);
        phoneField.sendKeys(newPhone);
        phoneField.sendKeys(org.openqa.selenium.Keys.TAB);
        wait.until(ExpectedConditions.attributeToBe(phoneField, "value", newPhone));
        return this;
    }

    public MyInfoPage verifyEmergencyContactUpdated() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastContainer));
        org.testng.Assert.assertTrue(successToast.getText().contains("Success"), "Emergency contact update save toast was not displayed!");
        log.info("Verified: Emergency contact data updated successfully!");
        return this;
    }

    public MyInfoPage editExistingEmergencyContact(String newPhone) {
        log.info("Starting emergency contact edit update. New Phone: {}", newPhone);
        clickEditExistingEmergencyContactIcon();
        updateEmergencyContactPhone(newPhone);
        clickSaveEmergencyContact();
        verifyEmergencyContactUpdated();
        return this;
    }

    public String getSelectedNationality() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(formLoader));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(selectedNationalityText));
        wait.until(d -> !element.getText().trim().isEmpty() && !element.getText().trim().equals("-- Select --"));
        return element.getText().trim();
    }

    public String getSelectedMaritalStatus() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(formLoader));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(selectedMaritalStatusText));
        wait.until(d -> !element.getText().trim().isEmpty() && !element.getText().trim().equals("-- Select --"));
        return element.getText().trim();
    }

    public String getStreetAddressValue() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(formLoader));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(street1Input));
        wait.until(driver -> !element.getAttribute("value").trim().isEmpty());
        return element.getAttribute("value");
    }

    public boolean isEmergencyContactPresent(String name, String relationship, String phone) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(formLoader));
        String rawNumber = phone.replaceFirst("^0+", "");
        By rowLocator = getEmergencyContactRowLocator(name, relationship, phone, rawNumber);
        wait.until(ExpectedConditions.presenceOfElementLocated(rowLocator));
        return !driver.findElements(rowLocator).isEmpty();
    }

    public MyInfoPage navigateToDependents() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(dependentsTab));
        new Actions(driver).moveToElement(element).perform();
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return this;
    }

    public MyInfoPage clickAddDependent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(addEmergencyBtn)).click();
        return this;
    }

    public MyInfoPage enterDependentDetails(String name, String relationship, String dob) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(dependentNameInput));
        nameField.sendKeys(name);

        selectCustomDropdownOption(dependentRelationshipDropdown, relationship);

        WebElement dobField = wait.until(ExpectedConditions.elementToBeClickable(dependentDobInput));
        dobField.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
        dobField.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);
        dobField.sendKeys(dob);
        return this;
    }

    public MyInfoPage clickSaveDependent() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(saveEmergencyBtn)).click();
        return this;
    }

    public MyInfoPage verifyDependentSaved() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastContainer));
        org.testng.Assert.assertTrue(successToast.getText().contains("Success"));
        return this;
    }

    public MyInfoPage addDependent(String name, String relationship, String dob) {
        clickAddDependent();
        enterDependentDetails(name, relationship, dob);
        clickSaveDependent();
        verifyDependentSaved();
        return this;
    }

    public boolean isDependentPresent(String name, String relationship) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(formLoader));
        By rowLocator = getDependentRowLocator(name, relationship);
        wait.until(ExpectedConditions.visibilityOfElementLocated(rowLocator));
        return !driver.findElements(rowLocator).isEmpty();
    }
}
