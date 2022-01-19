package com.split.bill.testdata;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

@RequiredArgsConstructor
public final class EventDataGenerator {

    private final Faker faker;

    @Step("Generate event title")
    public String generateEventTitle() {
        return faker.ancient().titan().concat("_")
                .concat(RandomStringUtils.randomAlphabetic(5));
    }
}
