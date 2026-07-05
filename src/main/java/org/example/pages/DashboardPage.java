package org.example.pages;

import org.example.admin.AdminPage;
import org.example.myinfo.MyInfoPage;
import org.example.pim.PimPage;
import org.example.time.TimePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {
    private final By pimButton = By.xpath("//span[text()='PIM']");
    private final By userDropdown = By.className("oxd-userdropdown-name");
    private final By logoutLink = By.xpath("//a[text()='Logout']");
    private final By myInfoButton = By.xpath("//span[text()='My Info']");
    private final By adminMenuLink = By.xpath("//span[text()='Admin']/parent::a");
    private final By timeButton = By.xpath("//span[text()='Time']");

    public DashboardPage(WebDriver driver){
        super(driver);
    }

    public boolean isDashboardMenuDisplayed() {
        return findElement(pimButton).isDisplayed();
    }

    public AdminPage pressOnAdminPage() {
        findElement(adminMenuLink).click();
        return new AdminPage(this.driver);
    }

    public PimPage pressOnPimButton(){
        findElement(pimButton).click();
        return new PimPage(driver);
    }

    public MyInfoPage clickMyInfo() {
        findElement(myInfoButton).click();
        return new MyInfoPage(driver);
    }

    public TimePage pressOnTimeButton() {
        findElement(timeButton).click();
        return new TimePage(driver);
    }

    public LoginPage logout() {
        findElement(userDropdown).click();
        findElement(logoutLink).click();
        return new LoginPage(driver);
    }
}