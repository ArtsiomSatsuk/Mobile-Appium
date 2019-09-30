package com.epam.ta.product.elements.entrypage;

import com.epam.ta.product.elements.common.AbstractPageElement;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ChecklistCreationPopUpWindow extends AbstractPageElement {

    @AndroidFindBy(id = "android:id/button1")
    private MobileElement createChecklistButton;
    @AndroidFindBy(id = "com.checklist.android:id/text")
    private MobileElement checklistNameInput;

    public void clickCreateButton() {
        waiter.waitUntilElementToBeClickable(createChecklistButton).click();
        logger.info("[Button 'Create' has been clicked]");
    }

    public void typeNameOfChecklistInInput(String checklistName) {
        waiter.waitUntilElementToBeClickable(checklistNameInput).click();
        checklistNameInput.sendKeys(checklistName);
        logger.info(String.format("[Name of checklist has been typed in input. Name - %s]", checklistName));
    }

}