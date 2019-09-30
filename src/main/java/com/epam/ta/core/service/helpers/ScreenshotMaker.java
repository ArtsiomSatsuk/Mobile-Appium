package com.epam.ta.core.service.helpers;

import com.epam.ta.core.driver.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenshotMaker {
    private static final String PROJECT_PATH = "user.dir";
    private static final String SCREENSHOTS_NAME = "screenshot_";
    private static final String SCREEN_CAPTURE_FAILED_MSG = "Failed to make screenshot - %s%s";
    private static final String SCREEN_CAPTURE_SUCCEEDED_MSG = "Screenshot has been created - %s%s Path to screenshot - %s";
    private static final String ABSOLUTE_PATH_TO_SCREENSHOT = "%s/screenshots/%s.png";

    private static Logger logger = LogManager.getLogger(ScreenshotMaker.class);

    private ScreenshotMaker() {
    }

//    public static byte[] captureScreen(String details) {
//        String screenshotName = SCREENSHOTS_NAME + Counter.getInstance().increment();
//        String destinationPath = String.format(ABSOLUTE_PATH_TO_SCREENSHOT, System.getProperty(PROJECT_PATH), screenshotName);
//        File source;
//        try {
//            source = ((TakesScreenshot) Driver.getDriverInstance().getDriver()).getScreenshotAs(OutputType.FILE);
//            FileUtils.copyFile(source, new File(destinationPath));
//            logger.info(String.format(SCREEN_CAPTURE_SUCCEEDED_MSG, screenshotName, details, destinationPath));
//            return Files.toByteArray(source);
//        } catch (IOException e) {
//            logger.error(String.format(SCREEN_CAPTURE_FAILED_MSG, screenshotName, details));
//            return null;
//        }
//    }

    public static byte[] captureScreen(String details) {
             return ((TakesScreenshot) Driver.getDriverInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
    }

}