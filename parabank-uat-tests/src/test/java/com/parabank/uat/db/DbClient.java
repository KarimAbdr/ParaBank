package com.parabank.uat.db;

import com.parabank.uat.config.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Tiny JDBC helper for test assertions against the application database.
 * Read-only by convention: tests check data here, they change data through the UI or API.
 *
 * Tables you will need:
 *   CUSTOMER    (ID, FIRST_NAME, LAST_NAME, USERNAME, ...)
 *   ACCOUNT     (ID, CUSTOMER_ID, TYPE, BALANCE)            TYPE: 0 = CHECKING, 1 = SAVINGS
 *   TRANSACTION (ID, ACCOUNT_ID, TYPE, DATE, AMOUNT, DESCRIPTION)
 */
public final class DbClient {

    private DbClient() {
    }

    /**
     * Runs a SELECT and returns all rows. Each row is a map: column name (UPPER CASE) -> value.
     * Always pass values as parameters (?), never glue them into the SQL string (SQL injection).
     */
    public static List<Map<String, Object>> query(String sql, Object... params) {
        try (Connection connection = DriverManager.getConnection(Config.dbUrl(), Config.dbUser(), Config.dbPassword());
             PreparedStatement statement = connection.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]); // JDBC parameters start at 1
            }

            List<Map<String, Object>> rows = new ArrayList<>();
            try (ResultSet resultSet = statement.executeQuery()) {
                ResultSetMetaData meta = resultSet.getMetaData();
                while (resultSet.next()) {
                    Map<String, Object> row = new LinkedHashMap<>();
                    for (int col = 1; col <= meta.getColumnCount(); col++) {
                        row.put(meta.getColumnLabel(col).toUpperCase(), resultSet.getObject(col));
                    }
                    rows.add(row);
                }
            }
            return rows;
        } catch (SQLException e) {
            throw new IllegalStateException("SQL failed: " + sql, e);
        }
    }

    /** Convenience for queries that return exactly one value, e.g. SELECT BALANCE ... WHERE ID = ? */
    public static Object queryForValue(String sql, Object... params) {
        List<Map<String, Object>> rows = query(sql, params);
        if (rows.size() != 1 || rows.get(0).size() != 1) {
            throw new IllegalStateException("Expected exactly 1 row and 1 column, got " + rows + " for: " + sql);
        }
        return rows.get(0).values().iterator().next();
    }
}
