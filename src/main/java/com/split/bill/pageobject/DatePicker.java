package com.split.bill.pageobject;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public final class DatePicker {

    private final SelenideElement todayButton = $("[ng-if='showButtonBar'] .btn-info");

    private final SelenideElement clearButton = $("[ng-if='showButtonBar'] .btn-danger");

    private final SelenideElement closeButton = $("[ng-if='showButtonBar'] .btn-success");
}
