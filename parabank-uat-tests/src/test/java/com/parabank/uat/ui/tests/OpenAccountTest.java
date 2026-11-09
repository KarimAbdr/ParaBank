package com.parabank.uat.ui.tests;

import com.parabank.uat.ui.BaseUiTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * User story PB-2: Open a new account
 *   As a logged-in customer
 *   I want to open a new SAVINGS account funded from an existing account
 *   So that I can keep my savings separately
 *
 * Acceptance criteria:
 *   AC1 - after submitting, the page says "Account Opened!"
 *   AC2 - a new account number is shown
 */
@Tag("ui")
@Tag("regression")
class OpenAccountTest extends BaseUiTest {

    @Test
    @Disabled("TODO 4 - tutorial step 4")
    @DisplayName("PB-2 AC1+AC2: customer opens a SAVINGS account")
    void customerOpensSavingsAccount() {
        // TODO 4.4: log in -> go to Open New Account -> open SAVINGS from 12345 -> assert AC1 and AC2
    }
}
