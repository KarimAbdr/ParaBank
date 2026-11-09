package com.parabank.uat.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Page Object for the login form on the ParaBank home page.
 *
 * Page Object rule of thumb:
 *   - locators (HOW to find elements) live here, never in the test
 *   - public methods describe user actions in business language: loginAs(...), not clickButton3()
 *   - an action that navigates returns the next page object
 */
public class LoginPage {

    private final Page page;

    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator errorMessage;

    public LoginPage(Page page) {
        this.page = page;
        this.usernameInput = page.locator("input[name='username']");
        this.passwordInput = page.locator("input[name='password']");
        this.loginButton = page.locator("input[type='submit'][value='Log In']");
        this.errorMessage = page.locator("#rightPanel .error");
    }

    public LoginPage open() {
        page.navigate("index.htm"); // relative to baseUrl from config.properties
        return this;
    }

    /** Happy path: valid credentials take the user to Accounts Overview. */
    public AccountsOverviewPage loginAs(String username, String password) {
        submitCredentials(username, password);
        return new AccountsOverviewPage(page);
    }

    /** Negative path: stays on an error page, so we return this page object. */
    public LoginPage loginExpectingError(String username, String password) {
        submitCredentials(username, password);
        return this;
    }

    public Locator errorMessage() {
        return errorMessage;
    }

    private void submitCredentials(String username, String password) {
        usernameInput.fill(username);
        passwordInput.fill(password);
        loginButton.click();
    }
}
