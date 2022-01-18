package com.split.bill.pageobject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
public final class AuthenticationPage {

    private final SelenideElement userEmailField = $("[name='email']");

    private final SelenideElement userPasswordField = $("[name='password']");

    private final SelenideElement newUserNameField = $("[name='user_name']");

    private final SelenideElement isNewUserCheckbox = $("input[type='checkbox']");

    private final SelenideElement signUpButton = $("button[ng-if='isNewUser']");

    private final SelenideElement signInButton = $("button[ng-if='!isNewUser']");

    @Step
    public AuthenticationPage open() {
        Selenide.open("/");
        return this;
    }

    @Step
    public AuthenticationPage setUserEmail(final String userEmail) {
        userEmailField.val(userEmail);
        return this;
    }

    @Step
    public AuthenticationPage setPassword(final String userPassword) {
        userPasswordField.val(userPassword);
        return this;
    }

    @Step
    public AuthenticationPage setNewUserName(final String newUserName) {
        newUserNameField.val(newUserName);
        return this;
    }

    @Step
    public AuthenticationPage selectIsNewUserCheckbox() {
        isNewUserCheckbox.setSelected(true);
        return this;
    }

    @Step
    public AuthenticationPage unselectIsNewUserCheckbox() {
        isNewUserCheckbox.setSelected(false);
        return this;
    }

}
