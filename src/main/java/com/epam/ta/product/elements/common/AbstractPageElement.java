package com.epam.ta.product.elements.common;

import com.epam.ta.core.driver.Driver;
import com.epam.ta.core.service.waiters.Waiter;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

public class AbstractPageElement {

    protected Waiter waiter = new Waiter();

    protected static Logger logger = LogManager.getLogger(AbstractPageElement.class);

    protected AbstractPageElement() {
        PageFactory.initElements(new AppiumFieldDecorator(Driver.getDriverInstance().getDriver()), this);
    }

}