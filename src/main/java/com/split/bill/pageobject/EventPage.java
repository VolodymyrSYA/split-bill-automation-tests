package com.split.bill.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public final class EventPage {

    private final SelenideElement addBillButton = $(".btn-success");

    @Step("Get bill by title '{0}'")
    public SelenideElement getBillByTitle(final String title) {
        return $x(String.format("//table//td[text()='%s']/..", title)).scrollTo();
    }

    @Step("Add bill")
    public BillPage addBill() {
         addBillButton.click();
        return new BillPage();
    }
}
