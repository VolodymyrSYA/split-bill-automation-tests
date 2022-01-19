package com.split.bill.pageobject;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public final class NavigationBar {

    private final SelenideElement userAvatar = $("#navbar a img[class='user-avatar']");

    private final SelenideElement userNameLabel = $("#navbar b");

    private final SelenideElement logoutButton = $("#navbar button[class*=logout]");
}
