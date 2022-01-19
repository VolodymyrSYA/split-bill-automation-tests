package com.split.bill.testdata;

import com.github.javafaker.Faker;
import com.split.bill.models.UserData;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class UserDataGeneration {

    public static final String GMAIL_DOMAIN_NAME = "@gmail.com";
    public static final int MINIMUM_PASSWORD_LENGTH = 8;
    public static final int MAXIMUM_PASSWORD_LENGTH = 24;

    private final Faker faker;

    public UserData generateUserData() {
        final String userName = faker.name().username();
        return UserData.builder().userEmail(userName.concat(GMAIL_DOMAIN_NAME)).userName(userName)
                .password(faker.internet().password(MINIMUM_PASSWORD_LENGTH, MAXIMUM_PASSWORD_LENGTH,
                        true, true))
                .build();
    }

}
