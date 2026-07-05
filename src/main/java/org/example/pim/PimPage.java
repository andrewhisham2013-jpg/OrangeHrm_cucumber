package org.example.pim;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.BasePage;
import org.example.myinfo.MyInfoPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static java.lang.invoke.MethodHandles.lookup;

public class PimPage extends BasePage {
    private static final Logger log = LogManager.getLogger(lookup().lookupClass());

    private final By addEmployeeButton = By.xpath("//a[text()='Add Employee']");
    private final By pimHeaderTitle = By.xpath("//h6[text()='PIM']");
    private final By employeeNameSearchInput = By.xpath("//label[text()='Employee Name']/following::input[1]");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By reportsTab = By.xpath("//a[text()='Reports']");
    private final By formLoader = By.className("oxd-form-loader");
    private final By hintOption = By.xpath("//div[@role='option']");
    private final By firstRowEditButton = By.xpath("(//div[@class='oxd-table-body']//div[@class='oxd-table-card'])[1]//button[i[contains(@class,'pencil')]]");

    public PimPage(WebDriver driver) {
        super(driver);
    }

    public PimReportPage navigateToPimReports() {
        findElement(reportsTab).click();
        return new PimReportPage(driver);
    }

    public AddEmployeePage clickAddEmployeeButton() {
        findElement(addEmployeeButton).click();
        return new AddEmployeePage(driver);
    }

    public String getHeaderTitleText() {
        return findElement(pimHeaderTitle).getText();
    }

    public PimPage searchEmployee(String employeeName) {
        log.info("Searching for employee record: {}", employeeName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(formLoader));

        WebElement nameInput = wait.until(ExpectedConditions.elementToBeClickable(employeeNameSearchInput));
        nameInput.click();
        nameInput.sendKeys(employeeName);

        wait.until(ExpectedConditions.elementToBeClickable(hintOption)).click();
        log.info("Selected autocomplete hint option.");

        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        log.info("Clicked Search button.");
        return this;
    }

    public MyInfoPage openEmployeeDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(formLoader));

        WebElement recordRow = wait.until(ExpectedConditions.presenceOfElementLocated(firstRowEditButton));

        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", recordRow);

        wait.until(ExpectedConditions.elementToBeClickable(recordRow)).click();
        log.info("Opened details for employee row element.");

        return new MyInfoPage(driver);
    }

    public MyInfoPage searchAndOpenEmployeeDetails(String employeeName) {
        searchEmployee(employeeName);
        return openEmployeeDetails();
    }
}