package com.parabank.uat.ui;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.parabank.uat.api.TestData;
import com.parabank.uat.config.Config;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import java.nio.file.Paths;

/**
 * Parent class for every UI test. It owns the browser lifecycle so tests only describe behaviour.
 *
 *   once per test class : start Playwright + browser (expensive)
 *   before every test   : reset test data, new BrowserContext (= clean incognito profile) + new Page
 *   after every test    : save a Playwright trace to target/traces (open it at https://trace.playwright.dev), close context
 */
public abstract class BaseUiTest {

    private static Playwright playwright;
    private static Browser browser;

    private BrowserContext context;
    protected Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(Config.headless())
                .setSlowMo(Config.slowMo()));
    }

    @BeforeEach
    void openPage() {
        TestData.resetDatabase();

        context = browser.newContext(new Browser.NewContextOptions().setBaseURL(Config.baseUrl()));
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true));
        page = context.newPage();
    }

    @AfterEach
    void closePage(TestInfo testInfo) {
        String testName = testInfo.getTestClass().map(Class::getSimpleName).orElse("Test")
                + "-" + testInfo.getTestMethod().map(m -> m.getName()).orElse("unknown");
        context.tracing().stop(new Tracing.StopOptions()
                .setPath(Paths.get("target", "traces", testName + ".zip")));
        context.close();
    }

    @AfterAll
    static void closeBrowser() {
        browser.close();
        playwright.close();
    }
}
