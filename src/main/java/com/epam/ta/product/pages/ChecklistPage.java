package com.epam.ta.product.pages;

import com.epam.ta.core.service.helpers.PageHelper;
import com.epam.ta.product.elements.checklistpage.ToolBar;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

public class ChecklistPage extends AbstractPage {

    private ToolBar toolBar = new ToolBar();

    @AndroidFindBy(id = "com.checklist.android:id/menu_add")
    private MobileElement addTaskButton;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.checklist.android:id/row']/android.widget.TextView")
    private MobileElement allCreatedTasks;

    public AddTaskPage clickAddTask() {
        waiter.waitUntilElementToBeClickable(addTaskButton).click();
        return new AddTaskPage();
    }

    public EntryPage clickBackToEntryPageButton() {
        toolBar.clickBackToEntryPageButton();
        return new EntryPage();
    }

    public SoftAssert areCreatedTasksExist(String[] tasks) {
        SoftAssert softAssert = new SoftAssert();
        Arrays.stream(tasks).forEach(task -> softAssert.assertTrue(isTaskExists(task), String.format("[Task with name - %s doesn't exist after creation]", task)));
        return softAssert;
    }

    private boolean isTaskExists(String task) {
        By createdTaskLocator = By.xpath(String.format("//android.widget.LinearLayout[@resource-id='com.checklist.android:id/row']/android.widget.TextView[@text='%s']", task));
        return !PageHelper.isElementAbsent(createdTaskLocator);
    }

}