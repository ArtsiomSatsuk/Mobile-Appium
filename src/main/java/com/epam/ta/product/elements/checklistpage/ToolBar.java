package com.epam.ta.product.elements.checklistpage;

import com.epam.ta.product.elements.common.AbstractPageElement;
import com.epam.ta.product.pages.EntryPage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class ToolBar extends AbstractPageElement {

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Close drawer']")
    private WebElement backToEntryPageButton;

    public EntryPage clickBackToEntryPageButton() {
        waiter.waitUntilElementToBeClickable(backToEntryPageButton).click();
        logger.info("[Directing back to Entry Page. Back to Entry Page button has been clicked.]");
        return new EntryPage();
    }


}