package com.split.bill.pageobject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public final class EventsPage {

    private final NavigationBar navigationBar = new NavigationBar();

    private final SelenideElement newEventTitleField = $("input[placeholder='title']");

    private final SelenideElement newEventDateField = $("input[datepicker-options='dateOptions']");

    private final SelenideElement addNewEventButton = $("button[ng-click='addEvent(newEvent)']");

    private final SelenideElement datePickerContextButton = $("button i");

    @Step("Open 'Events' page")
    public EventsPage openEventsPage() {
        Selenide.open("/events");
        return this;
    }

    @Step("Get event by title '{0}'")
    public SelenideElement getEventByTitle(final String title) {
        return $x(String.format("//table//td[text()='%s']/..", title)).scrollTo();
    }

    @Step
    public EventsPage open() {
        Selenide.open("/events");
        return this;
    }

    @Step("Set new event title '{0}'")
    public EventsPage setEventTitle(final String eventTitle) {
        newEventTitleField.val(eventTitle);
        return this;
    }

    @Step("Set new event current date")
    public EventsPage setEventDateCurrentDate() {
        openDatePicker().getTodayButton().click();
        return this;
    }

    @Step("Open date picker context window")
    public DatePicker openDatePicker() {
        datePickerContextButton.click();
        return new DatePicker();
    }

    @Step("Press 'Add' new event button")
    public EventsPage pressAddButton() {
        addNewEventButton.click();
        return this;
    }

    @Step("Create new event '{0}'")
    public EventsPage createNewEvent(final String eventTitle) {
        setEventTitle(eventTitle)
                .setEventDateCurrentDate()
                .pressAddButton();
        return this;
    }

    @Step("Open event '{0}'")
    public EventPage openEvent(final String eventTitle) {
        getEventByTitle(eventTitle).click();
        return new EventPage();
    }
}
