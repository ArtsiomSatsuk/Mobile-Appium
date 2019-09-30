package com.epam.ta.product.pages;

import com.epam.ta.core.service.helpers.ScreenshotMaker;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class WelcomePage extends AbstractPage {

    @AndroidFindBy(id = "com.checklist.android:id/skip")
    private WebElement skipIntroductionLink;

    public RegistrationPage skipIntroductionLink() {
        ScreenshotMaker.captureScreen("[Welcome introduction]");
        waiter.waitUntilElementToBeClickable(skipIntroductionLink).click();
        logger.info("[Welcome introduction has been skipped on 'WelcomePage']");
        return new RegistrationPage();
    }

}