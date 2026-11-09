package com.parabank.uat.ui.tests;

import com.parabank.uat.config.Config;
import com.parabank.uat.ui.BaseUiTest;
import com.parabank.uat.ui.pages.AccountsOverviewPage;
import com.parabank.uat.ui.pages.LoginPage;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * User story PB-1: Customer login
 *   As a ParaBank customer
 *   I want to log in with my username and password
 *   So that I can see my accounts
 *
 * Acceptance criteria:
 *   AC1 - valid credentials open "Accounts Overview" and greet the customer by name
 *   AC2 - invalid credentials show "The username and password could not be verified."
 */
@Tag("ui")
@Tag("smoke")
class LoginTest extends BaseUiTest {

    @Test
    @DisplayName("PB-1 AC1: valid credentials open Accounts Overview")
    void validLoginShowsAccountsOverview() {
        // Arrange + Act
        AccountsOverviewPage overview = new LoginPage(page)
                .open()
                .loginAs(Config.username(), Config.password());

        // Assert - Playwright assertions retry until the condition is true (default 5s)
        assertThat(overview.title()).hasText("Accounts Overview");
        assertThat(overview.welcomeText()).containsText("John Smith");
    }

    @Test
    @DisplayName("PB-1 AC2: invalid password shows an error message")
    void invalidPasswordShowsError() {
        // TODO 3: open the login page, log in with a wrong password using loginExpectingError(...),
        //         assert that the error message has the expected text.
        LoginPage loginPage = new LoginPage(page)
                .open()
                .loginExpectingError(Config.username(),"wrong-password");
        assertThat(loginPage.errorMessage()).hasText("The username and password could not be verified.");
    }
}
