package com.epam.ta.product.pages;

import com.epam.ta.core.driver.Driver;
import com.epam.ta.core.service.waiters.Waiter;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage {
    protected static Logger logger = LogManager.getLogger(AbstractPage.class);

    protected Waiter waiter = new Waiter();

    protected AbstractPage() {
        PageFactory.initElements(new AppiumFieldDecorator(Driver.getDriverInstance().getDriver()), this);
    }

}