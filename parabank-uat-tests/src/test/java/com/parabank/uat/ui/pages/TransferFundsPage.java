package com.parabank.uat.ui.pages;

import com.microsoft.playwright.Page;

/**
 * "Transfer Funds" page.
 *
 * HTML (simplified):
 *   <input id="amount">
 *   <select id="fromAccountId">   (loaded by AJAX)
 *   <select id="toAccountId">     (loaded by AJAX)
 *   <input type="submit" value="Transfer">
 *
 *   after success:  <div id="showResult">
 *                     <h1 class="title">Transfer Complete!</h1>
 *                     <span id="amountResult">$25.00</span> has been transferred from account
 *                     #<span id="fromAccountIdResult">12345</span> to account #<span id="toAccountIdResult">12456</span>.
 *
 * TUTORIAL: Step 5. This time you write the whole class yourself (fields, constructor, methods).
 */
public class TransferFundsPage {

    private final Page page;

    public TransferFundsPage(Page page) {
        this.page = page;
        // TODO 5.1: locators
    }

    // TODO 5.2: public TransferFundsPage transfer(String amount, String fromAccountId, String toAccountId)
    // TODO 5.3: public Locator resultTitle()
    // TODO 5.3: public Locator amountResult()
}
