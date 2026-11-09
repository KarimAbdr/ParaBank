package com.parabank.uat.bdd;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.parabank.uat.api.TestData;
import com.parabank.uat.config.Config;
import com.parabank.uat.ui.pages.AccountsOverviewPage;
import com.parabank.uat.ui.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Step definitions: glue between the English sentences in login.feature and our Page Objects.
 * Note that we REUSE the same page objects as the JUnit tests - Cucumber is only another way to drive them.
 */
public class LoginSteps {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    private LoginPage loginPage;
    private AccountsOverviewPage overviewPage;

    @Before
    public void startBrowser() {
        TestData.resetDatabase();
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(Config.headless()));
        context = browser.newContext(new Browser.NewContextOptions().setBaseURL(Config.baseUrl()));
        page = context.newPage();
    }

    @After
    public void stopBrowser() {
        context.close();
        browser.close();
        playwright.close();
    }

    @Given("the customer is on the ParaBank home page")
    public void openHomePage() {
        loginPage = new LoginPage(page).open();
    }

    @When("the customer logs in as {string} with password {string}")
    public void logIn(String username, String password) {
        overviewPage = loginPage.loginAs(username, password);
    }

    @Then("the page title is {string}")
    public void pageTitleIs(String expected) {
        assertThat(overviewPage.title()).hasText(expected);
    }

    @Then("the customer is greeted as {string}")
    public void customerIsGreetedAs(String fullName) {
        assertThat(overviewPage.welcomeText()).containsText(fullName);
    }

    // TODO 8: add the step definition for:
    //   Then the error message "..." is shown
}
