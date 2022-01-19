package com.split.bill.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class UserData {

    private final String userEmail;

    private final String userName;

    private final String password;

    @Override
    public String toString() {
        return String.format("User email - '%s', user name - '%s', password - '%s'",
                userEmail, userName, password);
    }
}
