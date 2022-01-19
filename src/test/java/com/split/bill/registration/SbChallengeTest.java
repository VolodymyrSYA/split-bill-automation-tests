package com.split.bill.registration;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Screenshots;
import com.split.bill.base.BaseTest;
import com.split.bill.core.utils.FileUtils;
import com.split.bill.core.utils.ReportingUtils;
import com.split.bill.models.BillData;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SbChallengeTest extends BaseTest {

    @Test
    @Order(1)
    @DisplayName("Verify user successful registration")
    void userRegistrationTest() {
        Allure.step("Precondition", () -> userData = userDataGenerator.generateUserData());

        authenticationPage.registerNewUser(userData);

        Allure.step("Verify user is created successfully");
        final var userCreatedLabel = authenticationPage.getUserCreatedLabel()
                .shouldBe(Condition.visible);
        final String expectedUserCreatedLabelValue = "User created.";

        assertThat(userCreatedLabel.getText())
                .as("'User created label' should have exact text: %s", expectedUserCreatedLabelValue)
                .isEqualTo(expectedUserCreatedLabelValue);
        ReportingUtils.attachAllureScreenshot("User registration test results",
                FileUtils.convertFileToByteArray(Screenshots.takeScreenShotAsFile()));
    }

    @Test
    @Order(2)
    @DisplayName("Verify user successful login")
    void userLoginTest() {
        Allure.step("Precondition", () -> {
            userData = userDataGenerator.generateUserData();
            authenticationPage.registerNewUser(userData);
        });

        authenticationPage.userLogin(userData);

        Allure.step("Verify user is logged in successfully");
        final var userNameLabel = eventsPage.getNavigationBar().getUserNameLabel()
                .shouldBe(Condition.visible);

        assertThat(userNameLabel.getText())
                .as("Logged in 'user name label' should have exact text: %s", userData.getUserName())
                .isEqualTo(userData.getUserName());
        ReportingUtils.attachAllureScreenshot("User login test results",
                FileUtils.convertFileToByteArray(Screenshots.takeScreenShotAsFile()));
    }

    @Test
    @Order(3)
    @DisplayName("Verify new event creation")
    void createNewEventTest() {
        Allure.step("Precondition", () -> {
            userData = userDataGenerator.generateUserData();
            eventTitle = eventDataGenerator.generateEventTitle();
            authenticationPage.registerNewUser(userData);
            authenticationPage.userLogin(userData);
        });

        eventsPage.createNewEvent(eventTitle);

        Allure.step(String.format("Verify the event '%s' was created",
                eventTitle));
        eventsPage.getEventByTitle(eventTitle)
                .shouldBe(Condition.appear, Condition.visible, Condition.enabled);
        ReportingUtils.attachAllureScreenshot("Create new event test results",
                FileUtils.convertFileToByteArray(Screenshots.takeScreenShotAsFile()));
    }

    @Test
    @Order(4)
    @DisplayName("Verify new bill creation")
    void createNewBillTest() {
        Allure.step("Precondition", () -> {
            userData = userDataGenerator.generateUserData();
            eventTitle = eventDataGenerator.generateEventTitle();
            authenticationPage.registerNewUser(userData);
            authenticationPage.userLogin(userData);
            eventsPage.createNewEvent(eventTitle);
        });

        eventPage = eventsPage.openEvent(eventTitle);
        var billData = BillData.builder().billProduct(billProductDataGenerator
                .generateBillProductData()).build();
        eventPage.addBill().fillBillProduct(billData);
        eventsPage.openEventsPage().openEvent(eventTitle);

        Allure.step("Verify the event bill was created");
        eventPage.getBillByTitle(billData.getBillName())
                .shouldBe(Condition.appear, Condition.visible, Condition.enabled);

        ReportingUtils.attachAllureScreenshot("Create new bill test results",
                FileUtils.convertFileToByteArray(Screenshots.takeScreenShotAsFile()));
    }
}
