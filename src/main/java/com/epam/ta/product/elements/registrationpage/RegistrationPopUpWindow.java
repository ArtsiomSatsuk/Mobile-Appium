package com.epam.ta.product.elements.registrationpage;

import com.epam.ta.product.elements.common.AbstractPageElement;
import com.epam.ta.product.pages.EntryPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class RegistrationPopUpWindow extends AbstractPageElement {

    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='android:id/button1']")
    private MobileElement buttonLater;

    public EntryPage clickButtonLater() {
        waiter.waitUntilElementToBeVisible(buttonLater);
        waiter.waitUntilElementToBeClickable(buttonLater).click();
        logger.info("[ Button 'Later' has been clicked. Registration alert window was skipped.]");
        return new EntryPage();
    }

}