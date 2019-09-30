package com.epam.ta.steps;

import com.epam.ta.product.bo.Checklist;
import com.epam.ta.product.pages.ChecklistPage;
import com.epam.ta.product.pages.EntryPage;
import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

public class ManageChecklistSteps {

    @Step("Creating checklist without tasks")
    public ManageChecklistSteps createChecklistWithoutTasks(Checklist checklist) {
        new EntryPage()
                .clickAddChecklistButton()
                .typeNameOfChecklistInInput(checklist)
                .clickCreateButton()
                .clickBackToEntryPageButton();
        return this;
    }

    public boolean isChecklistExists(Checklist checklist) {
        return new EntryPage().isChecklistExists(checklist);
    }

    public SoftAssert areAllTasksExistInChecklist(String[] tasks) {
        return new ChecklistPage().areCreatedTasksExist(tasks);
    }

    public void deleteChecklist(Checklist checklist) {
        new EntryPage()
                .clickSettingsButtonOfChecklist(checklist.getName())
                .clickDeleteButton();
    }

    @Step("Creating checklist with tasks")
    public ManageChecklistSteps createChecklistWithTasks(Checklist checklist, String[] tasks) {
        new EntryPage()
                .clickAddChecklistButton()
                .typeNameOfChecklistInInput(checklist)
                .clickCreateButton()
                .clickAddTask()
                .createTasksInChecklist(tasks)
                .clickReturnBackButton();
        return this;
    }

}