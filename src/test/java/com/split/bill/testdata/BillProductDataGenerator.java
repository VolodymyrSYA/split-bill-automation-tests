package com.split.bill.testdata;

import com.github.javafaker.Faker;
import com.split.bill.models.BillProduct;
import io.qameta.allure.Step;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class BillProductDataGenerator {

    private final Faker faker;

    @Step("Generate Bill")
    public BillProduct generateBillProductData() {
       return BillProduct.builder().productName(faker.book().title()).priceForOne(faker.number().digits(3))
                .amount(faker.number().digits(1)).build();
    }
}
