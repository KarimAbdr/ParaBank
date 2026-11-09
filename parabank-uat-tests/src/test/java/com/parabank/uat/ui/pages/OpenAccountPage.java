package com.parabank.uat.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * "Open New Account" page.
 *
 * HTML (simplified):
 *   <select id="type">            options: CHECKING (value 0), SAVINGS (value 1)
 *   <select id="fromAccountId">   options are loaded by AJAX after the page opens
 *   <input type="button" value="Open New Account">
 *
 *   after success:  <div id="openAccountResult"> <h1 class="title">Account Opened!</h1>
 *                     ... <a id="newAccountId">13566</a>
 *
 * TUTORIAL: Step 4.
 */
public class OpenAccountPage {

    private final Page page;

    // TODO 4.1: declare the Locator fields you need (account type select, from-account select,
    //           "Open New Account" button, result title, new account number link).
    //           Look at LoginPage to see the pattern.

    public OpenAccountPage(Page page) {
        this.page = page;
        // TODO 4.1: initialise the locators here with page.locator("...") or page.getByRole(...)
    }

    /**
     * Opens a new account.
     *
     * @param accountType   "CHECKING" or "SAVINGS" (the visible text of the option)
     * @param fromAccountId existing account that funds the new one, e.g. "12345"
     */
    public OpenAccountPage openAccount(String accountType, String fromAccountId) {
        // TODO 4.2: select the type, select the from-account, click the button
        throw new UnsupportedOperationException("TODO 4.2 - see tutorial step 4");
    }

    public Locator resultTitle() {
        // TODO 4.3
        throw new UnsupportedOperationException("TODO 4.3 - see tutorial step 4");
    }

    public Locator newAccountNumber() {
        // TODO 4.3
        throw new UnsupportedOperationException("TODO 4.3 - see tutorial step 4");
    }
}
