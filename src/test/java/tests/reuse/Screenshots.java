package tests.reuse;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class Screenshots {
    public static File takesshots(WebDriver driver , String screenshotspath) throws IOException
    {
        TakesScreenshot screenshot = ((TakesScreenshot)driver);
        File scrShot = screenshot.getScreenshotAs(OutputType.FILE);
        File dist = new File(screenshotspath);
        FileHandler.copy(scrShot,dist);
        return dist;
    }
}
