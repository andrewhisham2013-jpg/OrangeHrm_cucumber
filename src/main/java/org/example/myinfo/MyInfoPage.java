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
        String sanitizedText = optionText.replace("'", "\\'");
        return By.xpath("//div[@role='listbox']//*[text()='" + sanitizedText + "']");
    }

    private By getEmergencyContactRowLocator(String name, String relationship, String phone, String rawNumber) {
        String sName = name.replace("'", "\\'");
        String sRelationship = relationship.replace("'", "\\'");
        String sPhone = phone.replace("'", "\\'");
        String sRawNumber = rawNumber.replace("'", "\\'");
        return By.xpath("//div[@role='row'][descendant::*[contains(text(),'" + sName + "')] and descendant::*[contains(text(),'" + sRelationship + "')] and (descendant::*[contains(text(),'" + sPhone + "')] or descendant::*[contains(text(),'" + sRawNumber + "')])]");
    }

    private By getDependentRowLocator(String name, String relationship) {
        String sName = name.replace("'", "\\'");
        String sRelationship = relationship.replace("'", "\\'");
        return By.xpath("//div[@role='row'][descendant::*[contains(text(),'" + sName + "')] and descendant::*[contains(text(),'" + sRelationship + "')]]");
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
        return this;
    }

    public MyInfoPage verifyPersonalDetailsSaved() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastContainer));
        org.testng.Assert.assertTrue(successToast.getText().contains("Success"), "Personal details save toast was not displayed!");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(formLoader));
        return this;
    }

    public MyInfoPage updatePersonalDetails(String nationality, String maritalStatus) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(formLoader));
        selectNationality(nationality);
        selectMaritalStatus(maritalStatus);
        clickSavePersonalDetails();
        verifyPersonalDetailsSaved();
        return this;
    }

    public MyInfoPage navigateToContactDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(contactDetailsTab));
        new Actions(driver).moveToElement(element).perform();
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return this;
    }

    public MyInfoPage enterStreetAddress(String street) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement streetInputEl = wait.until(ExpectedConditions.elementToBeClickable(street1Input));
        streetInputEl.click();
        streetInputEl.sendKeys(org.openqa.selenium.Keys.CONTROL + "a");
        streetInputEl.sendKeys(org.openqa.selenium.Keys.BACK_SPACE);
        streetInputEl.sendKeys(street);
        streetInputEl.sendKeys(org.openqa.selenium.Keys.TAB);

        wait.until(ExpectedConditions.attributeToBe(streetInputEl, "value", street));
        return this;
    }

    public MyInfoPage clickSaveContactDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement saveBtn = wait.until(ExpectedConditions.presenceOfElementLocated(saveContactDetailsBtn));
        new Actions(driver).moveToElement(saveBtn).perform();
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
        return this;
    }

    public MyInfoPage verifyContactDetailsSaved() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastContainer));
        Assert.assertTrue(successToast.getText().contains("Success"), "Contact details save toast was not displayed!");
        return this;
    }

    public MyInfoPage updateContactDetails(String street) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(formLoader));
        enterStreetAddress(street);
        clickSaveContactDetails();
        verifyContactDetailsSaved();
        return this;
    }

    public MyInfoPage navigateToEmergencyContacts() {
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
        return this;
    }

    public MyInfoPage clickSaveEmergencyContact() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(saveEmergencyBtn));
        saveBtn.click();
        return this;
    }

    public MyInfoPage verifyEmergencyContactSaved() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement successToast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastContainer));
        org.testng.Assert.assertTrue(successToast.getText().contains("Success"), "Emergency contact save toast was not displayed!");
        return this;
    }

    public MyInfoPage addEmergencyContact(String name, String relationship, String phone) {
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
        return this;
    }

    public MyInfoPage editExistingEmergencyContact(String newPhone) {
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
        wait.until(d -> !element.getAttribute("value").trim().isEmpty());
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