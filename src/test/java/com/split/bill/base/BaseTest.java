package com.split.bill.base;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import com.split.bill.core.uiclient.DriverSetup;
import com.split.bill.models.UserData;
import com.split.bill.pageobject.AuthenticationPage;
import com.split.bill.pageobject.EventPage;
import com.split.bill.pageobject.EventsPage;
import com.split.bill.testdata.BillProductDataGenerator;
import com.split.bill.testdata.EventDataGenerator;
import com.split.bill.testdata.UserDataGenerator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@ExtendWith({TestResultLoggerExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BaseTest {

    private static final String SB_CHALLENGE_BASE_URL = "https://sb-challenge.in6k.com/#/";

    protected AuthenticationPage authenticationPage;

    protected EventsPage eventsPage;

    protected EventPage eventPage;

    protected UserDataGenerator userDataGenerator;

    protected EventDataGenerator eventDataGenerator;

    protected BillProductDataGenerator billProductDataGenerator;

    protected UserData userData;

    protected String eventTitle;

    @BeforeAll
    public void setup() {
        Configuration.reportsFolder = "build/test-result/reports";
        Configuration.baseUrl = SB_CHALLENGE_BASE_URL;

        final Faker faker = Faker.instance();
        userDataGenerator = new UserDataGenerator(faker);
        eventDataGenerator = new EventDataGenerator(faker);
        billProductDataGenerator = new BillProductDataGenerator(faker);

        authenticationPage = new AuthenticationPage();
        eventsPage = new EventsPage();
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
