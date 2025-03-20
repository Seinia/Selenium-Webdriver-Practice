package util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver) {
        if (driver == null) {
            log.debug("Driver is null, cannot take a screenshot.");
            return;
        }

        File screenCapture = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            String pathName = "./target/screenshots/" + getCurrentTimeAsString() + ".png";
            FileUtils.copyFile(screenCapture, new File(pathName));
            log.info("Test failed. Screenshot was saved in {}", pathName);
        } catch (IOException e) {
            log.debug("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

    private static String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}