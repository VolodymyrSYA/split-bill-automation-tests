package com.split.bill.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.split.bill.core.uiclient.DriverSetup;
import com.split.bill.pageobject.AuthenticationPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith({TestResultLoggerExtension.class})
public class BaseTest {

    private static final String SB_CHALLENGE_BASE_URL = "https://sb-challenge.in6k.com/#/";

    protected AuthenticationPage authenticationPage;

    @BeforeAll
    public void setup() {
        Configuration.reportsFolder = "build/test-result/reports";
        Configuration.baseUrl = SB_CHALLENGE_BASE_URL;

        authenticationPage = new AuthenticationPage();
    }

    @BeforeEach
    public void setupEach() {
        DriverSetup.setupDriverConfig();
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}
