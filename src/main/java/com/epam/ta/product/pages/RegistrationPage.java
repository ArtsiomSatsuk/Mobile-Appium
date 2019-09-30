package com.epam.ta.product.pages;

import com.epam.ta.core.driver.Driver;
import com.epam.ta.core.service.helpers.PageHelper;
import com.epam.ta.core.service.helpers.ScreenshotMaker;
import com.epam.ta.product.elements.registrationpage.RegistrationPopUpWindow;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class RegistrationPage extends AbstractPage {

    @AndroidFindBy(id = "com.checklist.android:id/skip")
    private AndroidElement skipRegistrationLink;
    @AndroidFindBy(xpath = "//android.widget.ScrollView")
    private MobileElement allScreen;

    private RegistrationPopUpWindow registrationPopUpWindow = new RegistrationPopUpWindow();
    private By skipRegistrationLinkLocator = By.id("com.checklist.android:id/skip");

    @Step(value = "clicking on 'skip introduction link'")
    public RegistrationPage clickSkipRegistrationLink() {
        scrollDownUntilPresenceOfElement(skipRegistrationLinkLocator);
        waiter.waitUntilElementToBeClickable(Driver.getDriverInstance().getDriver().findElement(skipRegistrationLinkLocator)).click();
        logger.info("[Link to skip registration form has been clicked on 'RegistrationPage']");
        return this;
    }

    @Attachment(value = "Screenshot from {method}", type = "image/jpg")
    @Step(value = "clicking button 'Later' on popped up window")
    public EntryPage clickButtonLaterOnPoppedUpWindow() {
        registrationPopUpWindow.clickButtonLater();
        ScreenshotMaker.captureScreen("[Button 'Later' clicked on popped up window]");
        return new EntryPage();
    }

    public RegistrationPage scrollDownUntilPresenceOfElement(By elementToBePresentLocator) {
        TouchAction touchAction = new TouchAction((AndroidDriver) Driver.getDriverInstance().getDriver());
        waiter.waitUntilElementToBeVisible(allScreen);
        int bottomBound = allScreen.getCenter().getY() / 2;
        int upperBound = (allScreen.getCenter().getY() * 2) - bottomBound;
        touchAction.longPress(allScreen.getCenter().getX(), upperBound)
                .moveTo(allScreen.getCenter().getX(), bottomBound)
                .release()
                .perform();
        ScreenshotMaker.captureScreen(String.format("[Screen has been scrolled down from %d on 'Y' axes, to %d]", upperBound, bottomBound));
        logger.info(String.format("[Moving down from %d on 'Y' axes, to %d]", upperBound, bottomBound));
        if (PageHelper.isElementAbsent(elementToBePresentLocator)) {
            logger.info("[Searchable element hasn't been found. Scrolling down once again.]");
            scrollDownUntilPresenceOfElement(elementToBePresentLocator);
        }
        return this;
    }

}