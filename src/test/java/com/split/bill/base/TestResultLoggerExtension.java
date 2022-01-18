package com.split.bill.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.ex.UIAssertionError;
import com.codeborne.selenide.junit5.ScreenShooterExtension;
import com.split.bill.core.utils.FileUtils;
import com.split.bill.core.utils.ReportingUtils;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.lang.reflect.Method;
import java.util.Optional;

@ParametersAreNonnullByDefault
public final class TestResultLoggerExtension implements BeforeEachCallback, AfterEachCallback {
    private static final Logger log = LoggerFactory.getLogger(ScreenShooterExtension.class);

    private final boolean captureSuccessfulTests;

    public TestResultLoggerExtension() {
        this(false);
    }

    /**
     * @param captureSuccessfulTests param that indicate if need to capture successful tests
     */
    public TestResultLoggerExtension(final boolean captureSuccessfulTests) {
        this.captureSuccessfulTests = captureSuccessfulTests;
    }

    /**
     * One-liner to configure Configuration.reportsFolder property.
     *
     * @param folderWithScreenshots Folder to put screenshots to
     * @return current extension instance
     */
    @Nonnull
    public TestResultLoggerExtension to(final String folderWithScreenshots) {
        Configuration.reportsFolder = folderWithScreenshots;
        return this;
    }

    @Override
    public void beforeEach(final ExtensionContext context) {
        final Optional<Class<?>> testClass = context.getTestClass();
        final String className = testClass.map(Class::getName).orElse("EmptyClass");

        final Optional<Method> testMethod = context.getTestMethod();
        final String methodName = testMethod.map(Method::getName).orElse("emptyMethod");

        Screenshots.startContext(className, methodName);
    }

    @Override
    public void afterEach(final ExtensionContext context) {
        if (captureSuccessfulTests) {
            log.info(Screenshots.saveScreenshotAndPageSource());
        } else {
            context.getExecutionException().ifPresent(error -> {
                if (!(error instanceof UIAssertionError)) {
                    log.info(Screenshots.saveScreenshotAndPageSource());
                }
                Screenshots.getLastThreadScreenshot().ifPresent(file -> ReportingUtils.attachAllureScreenshot(
                        "test", FileUtils.convertFileToByteArray(file)));
            });
        }
        Screenshots.finishContext();
    }
}