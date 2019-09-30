package com.epam.ta.core.service.helpers;


import com.epam.ta.core.driver.Driver;
import org.openqa.selenium.By;

public class PageHelper {

    private PageHelper() {
        throw new IllegalStateException("[Utility class. Instance must not be created]");
    }

    public static boolean isElementAbsent(By elementToBePresentLocator) {
        return Driver.getDriverInstance().getDriver().findElements(elementToBePresentLocator).isEmpty();
    }

}