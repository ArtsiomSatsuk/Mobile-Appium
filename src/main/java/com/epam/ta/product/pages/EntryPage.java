package com.epam.ta.product.pages;

import com.epam.ta.core.service.helpers.ScreenshotMaker;
import com.epam.ta.product.bo.Checklist;
import com.epam.ta.product.elements.entrypage.ChecklistCreationPopUpWindow;
import com.epam.ta.product.elements.entrypage.ChecklistSettingsPopUpWindow;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class EntryPage extends AbstractPage {

    private ChecklistCreationPopUpWindow checklistCreationPopUpWindow = new ChecklistCreationPopUpWindow();
    private ChecklistSettingsPopUpWindow checklistSettingsPopUpWindow = new ChecklistSettingsPopUpWindow();

    @AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.CheckBox/..//android.widget.TextView")
    private List<WebElement> allChecklists;
    @AndroidFindBy(id = "com.checklist.android:id/menu_add")
    private MobileElement addChecklistButton;

    public EntryPage clickAddChecklistButton() {
        waiter.waitUntilElementToBeClickable(addChecklistButton).click();
        logger.info("['Add checklist' button has been clicked on EntryPage]");
        return this;
    }

    public EntryPage typeNameOfChecklistInInput(Checklist checklist) {
        checklistCreationPopUpWindow.typeNameOfChecklistInInput(checklist.getName());
        ScreenshotMaker.captureScreen(String.format("[Name of checklist typed in input - %s]", checklist.getName()));
        return this;
    }

    public ChecklistPage clickCreateButton() {
        checklistCreationPopUpWindow.clickCreateButton();
        return new ChecklistPage();
    }

    public boolean isChecklistExists(Checklist checklistToBePresent) {
        return allChecklists
                .stream()
                .anyMatch(checklist -> checklist.getText().equals(checklistToBePresent.getName()));
    }

    public EntryPage clickSettingsButtonOfChecklist(String nameOfChecklist) {
        ScreenshotMaker.captureScreen(String.format("[Clicking 'settings' button in checklist - %s]", nameOfChecklist));
        By openSettingsButtonLocator = By.xpath(String.format("//android.widget.TextView[@text='%s']/../following-sibling::*[@content-desc='menu']", nameOfChecklist));
        waiter.waitUntilElementToBePresent(openSettingsButtonLocator).click();
        return this;
    }

    public EntryPage clickDeleteButton() {
        ScreenshotMaker.captureScreen("[Clicking 'Delete' button]");
        checklistSettingsPopUpWindow.clickDeleteButton();
        return this;
    }

}