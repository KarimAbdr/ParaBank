package com.parabank.uat.api;

import com.parabank.uat.config.Config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

/**
 * API tests for the ParaBank REST service (the same backend the Angular/JS frontend calls).
 * API tests are faster and more stable than UI tests, so a good regression suite
 * checks business rules here and keeps UI tests for the main user journeys.
 *
 * Useful endpoints (base: Config.apiUrl()):
 *   GET  /login/{username}/{password}          -> customer JSON, 400 if wrong
 *   GET  /customers/{customerId}/accounts      -> JSON array of accounts
 *   GET  /accounts/{accountId}                 -> one account {id, customerId, type, balance}
 *   POST /transfer?fromAccountId=&toAccountId=&amount=
 */
@Tag("api")
class AccountsApiTest {

    private RequestSpecification api;

    @BeforeEach
    void setUp() {
        TestData.resetDatabase();
        api = RestAssured.given()
                .baseUri(Config.apiUrl())
                .accept(ContentType.JSON);
    }

    @Test
    @DisplayName("API: valid login returns the customer profile")
    void loginReturnsCustomer() {
        api.when()
                .get("/login/{user}/{pass}", Config.username(), Config.password())
           .then()
                .statusCode(200)
                .body("id", equalTo(Config.customerId()))
                .body("firstName", equalTo("John"))
                .body("lastName", equalTo("Smith"));
    }

    @Test
    @Disabled("TODO 6.1 - tutorial step 6")
    @DisplayName("API: invalid login is rejected with 400")
    void invalidLoginIsRejected() {
        // TODO 6.1
    }

    @Test
    @Disabled("TODO 6.2 - tutorial step 6")
    @DisplayName("API: customer 12212 has accounts and the first one is 12345")
    void customerHasAccounts() {
        // TODO 6.2
    }

    @Test
    @Disabled("TODO 6.3 - tutorial step 6")
    @DisplayName("API: transfer of $50 increases the target account balance by $50")
    void transferIncreasesTargetBalance() {
        // TODO 6.3
    }
}
