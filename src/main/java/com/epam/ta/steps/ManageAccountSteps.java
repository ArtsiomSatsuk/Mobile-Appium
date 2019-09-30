package com.epam.ta.steps;

import com.epam.ta.product.pages.WelcomePage;
import io.qameta.allure.Step;

public class ManageAccountSteps {

    @Step("Opening entry page")
    public ManageChecklistSteps openEntryPage() {
        new WelcomePage()
                .skipIntroductionLink()
                .clickSkipRegistrationLink()
                .clickButtonLaterOnPoppedUpWindow();
        return new ManageChecklistSteps();
    }

}