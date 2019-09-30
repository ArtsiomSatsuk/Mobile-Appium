package com.epam.ta.fixture;

import com.epam.ta.core.driver.Driver;
import com.epam.ta.core.listeners.Listener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(Listener.class)
public class CommonConditions {

    @BeforeMethod
    public void openApp() {
        Driver.getDriverInstance().launchMobileApp();
    }

    @AfterMethod
    public void closeApp() {
        Driver.getDriverInstance().closeMobileApp();
    }

}
