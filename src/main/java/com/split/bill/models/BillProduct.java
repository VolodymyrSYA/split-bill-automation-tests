package com.split.bill.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public final class BillProduct {

    private final String productName;

    private final String priceForOne;

    private final String amount;

    @Override
    public String toString() {
        return String.format("Product name - '%s', price for one = '%s', amount = '%s'", productName, priceForOne,
                amount);
    }

}
