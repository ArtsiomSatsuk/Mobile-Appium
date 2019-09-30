package com.epam.ta.core.service.waiters;

import com.epam.ta.core.driver.Driver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

public class Waiter {

    private static final long WAIT_FOR_ELEMENT_SECONDS = 10;
    private static final int POLLING_TIME_DURATION_MILLIS = 100;

    private Wait<AndroidDriver> wait = new FluentWait<>((AndroidDriver) Driver.getDriverInstance().getDriver()).withTimeout(WAIT_FOR_ELEMENT_SECONDS, TimeUnit.SECONDS)
            .pollingEvery(POLLING_TIME_DURATION_MILLIS, TimeUnit.SECONDS)
            .ignoring(NoSuchElementException.class)
            .ignoring(StaleElementReferenceException.class);

    public WebElement waitUntilElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitUntilElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitUntilElementToBePresent(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return Driver.getDriverInstance().getDriver().findElement(locator);
    }

}