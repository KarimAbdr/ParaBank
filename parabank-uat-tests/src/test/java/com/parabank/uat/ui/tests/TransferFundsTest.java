package com.parabank.uat.ui.tests;

import com.parabank.uat.ui.BaseUiTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * User story PB-3: Transfer funds between own accounts
 *   As a logged-in customer
 *   I want to move money between my accounts
 *   So that I can manage my balances
 *
 * Acceptance criteria:
 *   AC1 - after the transfer the page says "Transfer Complete!"
 *   AC2 - the confirmation shows the transferred amount, e.g. "$25.00"
 */
@Tag("ui")
@Tag("regression")
class TransferFundsTest extends BaseUiTest {

    @Test
    @Disabled("TODO 5 - tutorial step 5")
    @DisplayName("PB-3 AC1+AC2: customer transfers $25 from 12345 to 12456")
    void customerTransfersFunds() {
        // TODO 5.4
    }
}
