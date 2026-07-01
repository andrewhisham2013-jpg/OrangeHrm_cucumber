package tests.driver;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {

    public static WebDriver driver;

    public static WebDriver getDriver(String browser){
        switch ((browser.toLowerCase())){
            case "chrome":
                driver = ChromeDriverClass.getChromeDriver();
                break;
            case "firefox":
                driver = FireFoxDriveClass.getFireFoxDriver();
                break;
            case "edge":
                driver = EdgeDriverClass.getEdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid Browser Name" + browser);
        }
        return driver;
    }
}
