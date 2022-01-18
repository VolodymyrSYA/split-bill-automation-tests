package com.split.bill.core.uiclient;

import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Optional;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Browsers.FIREFOX;
import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver;
import static java.lang.System.getProperty;
import static java.lang.System.setProperty;
import static org.openqa.selenium.firefox.FirefoxDriver.MARIONETTE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DriverSetup {

    public static void setupDriverConfig() {
        String driverName = Optional.ofNullable(getProperty("browserName")).orElse(CHROME);
        if (driverName.equals(CHROME)) {
            WebDriverRunner.setWebDriver(getChromeWebDriver());
        }
        if (driverName.equals(FIREFOX)) {
            WebDriverRunner.setWebDriver(getFirefoxDriver());
        }
    }

    private static WebDriver getChromeWebDriver() {
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications")
                .addArguments("--disable-popup-blocking");

        final WebDriverManager webDriverManager = chromedriver();
        webDriverManager.setup();

        setProperty("webdriver.chrome.driver", webDriverManager.getDownloadedDriverPath());
        final ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);
        chromeDriver.manage().window().maximize();

        return chromeDriver;
    }

    private static WebDriver getFirefoxDriver() {
        final FirefoxOptions firefoxOptions = new FirefoxOptions();

        firefoxOptions.addPreference("dom.disable_beforeunload", true)
                .addPreference("dom.webnotifications.enabled", false);
        firefoxOptions.setCapability(MARIONETTE, true);

        final WebDriverManager webDriverManager = firefoxdriver();
        webDriverManager.setup();

        setProperty("webdriver.gecko.driver", webDriverManager.getDownloadedDriverPath());
        final FirefoxDriver firefoxDriver = new FirefoxDriver(firefoxOptions);
        firefoxDriver.manage().window().maximize();

        return firefoxDriver;
    }
}
