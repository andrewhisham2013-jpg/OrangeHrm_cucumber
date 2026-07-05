package tests.reuse;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class Screenshot {
    public static File takeScreenshot(WebDriver driver, String path) throws IOException {
        TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
        File sourceScreenshot = takeScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(path);
        FileHandler.copy(sourceScreenshot, destinationFile);
        return destinationFile;
    }
}