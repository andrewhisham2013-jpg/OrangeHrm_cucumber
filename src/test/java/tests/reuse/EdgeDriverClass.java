package tests.reuse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverClass extends WebDriverFactory {
    private static WebDriver driver=null;

    public static WebDriver getEdgeDriver() {
        if (driver==null){
            driver = new EdgeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }
}
