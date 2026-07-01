package tests.base;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import tests.reuse.CSVFileManager;
import tests.reuse.ConfigManager;
import tests.reuse.ExcelFileManager;
import tests.reuse.JsonFileManager;

public class BaseTest
{
    public static ConfigManager configManager;
    public static JsonFileManager jsonFileManagerValidLogin;
    public static JsonFileManager jsonFileManagerInvalidLogin;
    public static JsonFileManager jsonFileManagerCheckoutInformation;
    public static ExcelFileManager excelFileManager;
    public static CSVFileManager csvFileManager;
    protected static WebDriver driver;
    public static SoftAssert softAssert;



}
