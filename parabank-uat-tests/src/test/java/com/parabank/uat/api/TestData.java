package com.parabank.uat.api;

import com.parabank.uat.config.Config;
import io.restassured.RestAssured;

/**
 * Test data management.
 * Regression tests must be repeatable: every run starts from the same known state.
 * ParaBank has an endpoint that restores its demo data, so we call it before tests.
 */
public final class TestData {

    private TestData() {
    }

    /** Restores the ParaBank demo database (user john/demo, customer 12212, default accounts). */
    public static void resetDatabase() {
        RestAssured.given()
                .baseUri(Config.apiUrl())
                .when()
                .post("/initializeDB")
                .then()
                .statusCode(204);
    }
}
