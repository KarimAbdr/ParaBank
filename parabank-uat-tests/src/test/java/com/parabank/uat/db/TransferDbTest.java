package com.parabank.uat.db;

import com.parabank.uat.api.TestData;
import com.parabank.uat.config.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Data-level checks: what the user sees must match what is stored.
 * Typical UAT question: "the screen says Transfer Complete - but did the money actually move?"
 */
@Tag("db")
class TransferDbTest {

    @BeforeEach
    void resetData() {
        TestData.resetDatabase();
    }

    @Test
    @DisplayName("DB: demo customer john exists and owns accounts")
    void demoCustomerExistsInDatabase() {
        Object firstName = DbClient.queryForValue(
                "SELECT FIRST_NAME FROM CUSTOMER WHERE USERNAME = ?", Config.username());
        assertEquals("John", firstName);

        List<Map<String, Object>> accounts = DbClient.query(
                "SELECT ID, TYPE, BALANCE FROM ACCOUNT WHERE CUSTOMER_ID = ? ORDER BY ID", Config.customerId());
        assertFalse(accounts.isEmpty(), "john should have at least one account");
    }

    @Test
    @Disabled("TODO 7 - tutorial step 7")
    @DisplayName("DB: transfer of $50 moves money between ACCOUNT rows and writes TRANSACTION rows")
    void transferIsPersisted() {
        // TODO 7.1 read balances of 12345 and 12456 from ACCOUNT (before)
        // TODO 7.2 make the transfer through the API
        // TODO 7.3 read balances again (after) and compare with BigDecimal
        // TODO 7.4 check TRANSACTION has a "Funds Transfer Received" row of 50 for 12456
    }
}
