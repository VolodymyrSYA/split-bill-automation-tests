package com.split.bill.pageobject;

import com.codeborne.selenide.SelenideElement;
import com.split.bill.models.BillData;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public final class BillPage {

    private final SelenideElement productNameInput = $("[ng-model='item.product.title']");

    private final SelenideElement productPriceForOneInput = $("[ng-model='item.product.price']");

    private final SelenideElement productAmountInput = $("[ng-model='item.product.amount']");

    private final SelenideElement closeBillButton = $("[title='Close bill']");

    private final SelenideElement billTotalCostLabel = $x("//h3[contains(., 'Bill total cost')]");

    private final SelenideElement billNameInput = $("[ng-model='selectedBill.name']");

    @Step("Fill bill product {billData.billProduct}")
    public BillPage fillBillProduct(final BillData billData) {
        productNameInput.val(billData.getBillProduct().getProductName());
        productPriceForOneInput.val(billData.getBillProduct().getPriceForOne());
        productAmountInput.val(billData.getBillProduct().getAmount());
        billNameInput.click();
        collectBillSummary(billData);

        return this;
    }

    private void collectBillSummary(final BillData billData) {
        billData.setBillName(billNameInput.val());
        billData.setBillTotalCostData(billTotalCostLabel.getText());
    }
}
