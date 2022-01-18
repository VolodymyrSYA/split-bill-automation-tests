package com.split.bill.core.utils;

import io.qameta.allure.Attachment;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ReportingUtils {

    @Attachment(value = "{0}", type = "image/png")
    public static byte[] attachAllureScreenshot(final String attachmentName, byte[] screenShot) {
        return screenShot;
    }
}
