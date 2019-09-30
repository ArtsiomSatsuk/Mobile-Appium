package com.epam.ta.product.elements.entrypage;

import com.epam.ta.product.elements.common.AbstractPageElement;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ChecklistSettingsPopUpWindow extends AbstractPageElement {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete']")
    private MobileElement deleteChecklistLink;

    public void clickDeleteButton() {
        waiter.waitUntilElementToBeClickable(deleteChecklistLink).click();
    }

}
