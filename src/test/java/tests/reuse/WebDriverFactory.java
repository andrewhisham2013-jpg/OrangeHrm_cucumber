package tests.reuse;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {


    public static WebDriver getDriver(String browser) {
        WebDriver localDriver;

        switch(browser.toLowerCase()) {
            case "chrome":
                localDriver = ChromeDriver.setChromeDriver();
                break;
            case "firefox":
                localDriver = FireFoxDriverClass.getFireFoxDriver();
                break;
            case "edge":
                localDriver = EdgeDriverClass.getEdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        return localDriver;
    }
}