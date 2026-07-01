package tests.reuse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriver {

    public static WebDriver setChromeDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--start-maximized");

        return new org.openqa.selenium.chrome.ChromeDriver(chromeOptions);
    }
}