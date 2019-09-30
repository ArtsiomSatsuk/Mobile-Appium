package com.epam.ta.product.pages;

import com.epam.ta.core.service.helpers.ScreenshotMaker;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.Arrays;

public class AddTaskPage extends AbstractPage {

    @AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='inputField']")
    private MobileElement taskNameInput;
    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc]")
    private MobileElement addTaskButton;
    @AndroidFindBy(xpath = "//android.view.ViewGroup/android.widget.ImageButton")
    private MobileElement returnBackButton;

    public AddTaskPage createTasksInChecklist(String[] tasks) {
        Arrays.asList(tasks).forEach(task -> {
            typeNameOfTask(task);
            clickAddTask();
            ScreenshotMaker.captureScreen(String.format("[Task created - %s]", task));
        });
        return this;
    }

    public ChecklistPage clickReturnBackButton() {
        waiter.waitUntilElementToBeClickable(returnBackButton).click();
        return new ChecklistPage();
    }

    private AddTaskPage typeNameOfTask(String taskName) {
        waiter.waitUntilElementToBeClickable(taskNameInput).sendKeys(taskName);
        return this;
    }

    private AddTaskPage clickAddTask() {
        waiter.waitUntilElementToBeClickable(addTaskButton).click();
        return this;
    }

}