package tests.base;

import org.example.myinfo.MyInfoPage;
import org.example.pages.LoginPage;
import org.example.pages.DashboardPage;
import org.example.admin.AdminPage;
import org.example.pim.PimPage;

import org.example.pim.PimReportPage;
import org.example.time.TimePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import tests.reuse.JsonFileManager;

public class BaseTest {
    public static WebDriverWait wait;
    public static SoftAssert softAssert;
    public static JsonFileManager jsonFileManager;
    protected static WebDriver driver;
    public static PimReportPage reportPage;
    public static MyInfoPage myInfoPage;
    public static PimReportPage pimReportPage;
    public static DashboardPage dashboard;


    public static LoginPage loginPage;
    public static PimPage pimPage;
    public static MyInfoPage myInfo;
    public static MyInfoPage verificationPage;
    public static TimePage timePage;


    public WebDriver getDriver(){
        return driver;
    }
}