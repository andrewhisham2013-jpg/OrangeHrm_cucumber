package tests.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverClass extends WebDriverFactory{
    private static WebDriver driver = null;

    public static WebDriver getChromeDriver(){
        if(driver == null){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            driver = new org.openqa.selenium.chrome.ChromeDriver(options);
            driver.manage().window().maximize();
        }
        return driver;

    }
}
