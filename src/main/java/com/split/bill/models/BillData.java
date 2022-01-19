package com.split.bill.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class BillData {

    private String billName;

    private String billTotalCostData;

    private final BillProduct billProduct;
}
