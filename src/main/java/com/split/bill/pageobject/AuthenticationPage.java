package com.split.bill.pageobject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.split.bill.models.UserData;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.*;

@Getter
public final class AuthenticationPage {

    private final SelenideElement userEmailField = $("[name='email']");

    private final SelenideElement userPasswordField = $("[name='password']");

    private final SelenideElement newUserNameField = $("[name='user_name']");

    private final SelenideElement isNewUserCheckbox = $("input[type='checkbox']");

    private final SelenideElement signUpButton = $("button[ng-if='isNewUser']");

    private final SelenideElement signInButton = $("button[ng-if='!isNewUser']");

    private final SelenideElement userCreatedLabel = $("p[ng-if='userCreated']");

    @Step("Open authentication page")
    public AuthenticationPage openAuthenticationPage() {
        Selenide.open("/");
        return this;
    }

    @Step("Set user email '{0}'")
    public AuthenticationPage setUserEmail(final String userEmail) {
        userEmailField.val(userEmail);
        return this;
    }

    @Step("Set user password '{0}'")
    public AuthenticationPage setPassword(final String userPassword) {
        userPasswordField.val(userPassword);
        return this;
    }

    @Step("Set new user name '{0}'")
    public AuthenticationPage setNewUserName(final String newUserName) {
        newUserNameField.val(newUserName);
        return this;
    }

    @Step("Select 'New User?' checkbox")
    public AuthenticationPage selectIsNewUserCheckbox() {
        isNewUserCheckbox.setSelected(true);
        return this;
    }

    @Step("Unselect 'New User?' checkbox")
    public AuthenticationPage unselectIsNewUserCheckbox() {
        isNewUserCheckbox.setSelected(false);
        return this;
    }

    @Step("Press 'Sign Up' button")
    public AuthenticationPage submitUserRegistration() {
        signUpButton.click();
        return this;
    }

    @Step("Press 'Sign In' button")
    public AuthenticationPage submitUserLogin() {
        signInButton.click();
        return this;
    }

    @Step("Register new user '{userData.userName}'")
    public void registerNewUser(final UserData userData) {
        openAuthenticationPage().setUserEmail(userData.getUserEmail()).setPassword(userData.getPassword())
                .selectIsNewUserCheckbox().setNewUserName(userData.getUserName())
                .submitUserRegistration();
    }

    @Step("User '{userData.userName}' login")
    public void userLogin(final UserData userData) {
        openAuthenticationPage().setUserEmail(userData.getUserEmail()).setPassword(userData.getPassword())
                .unselectIsNewUserCheckbox()
                .submitUserLogin();
    }

}
