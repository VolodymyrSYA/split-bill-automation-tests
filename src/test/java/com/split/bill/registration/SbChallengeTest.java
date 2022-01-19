package com.split.bill.registration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Screenshots;
import com.split.bill.base.BaseTest;
import com.split.bill.core.utils.FileUtils;
import com.split.bill.core.utils.ReportingUtils;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.Preconditions;

import static org.assertj.core.api.Assertions.assertThat;

class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Verify user successful registration")
    void userRegistrationTest() {
        Preconditions.notNull(userData, "User data should be present");

        authenticationPage.registerNewUser(userData);

        Allure.step("Verify user is successfully created");
        final var userCreatedLabel = authenticationPage.getUserCreatedLabel();
        final String expectedUserCreatedLabelValue = "User created.";

        userCreatedLabel.shouldBe(Condition.visible);
        assertThat(userCreatedLabel.getText())
                .as("User created label should have exact text: %s", expectedUserCreatedLabelValue)
                .isEqualTo(expectedUserCreatedLabelValue);
        ReportingUtils.attachAllureScreenshot("User registration test results",
                FileUtils.convertFileToByteArray(Screenshots.takeScreenShotAsFile()));
    }
}
