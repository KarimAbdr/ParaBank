package com.parabank.uat.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Single place for environment settings.
 * Values come from src/test/resources/config.properties and can be
 * overridden with -Dkey=value (that is how CI points tests at another environment).
 */
public final class Config {

    private static final Properties PROPS = load();

    private Config() {
        // utility class: no instances
    }

    public static String baseUrl()    { return get("baseUrl"); }
    public static String apiUrl()     { return get("apiUrl"); }
    public static String username()   { return get("username"); }
    public static String password()   { return get("password"); }
    public static int customerId()    { return Integer.parseInt(get("customerId")); }
    public static boolean headless()  { return Boolean.parseBoolean(get("headless")); }
    public static double slowMo()     { return Double.parseDouble(get("slowMo")); }
    public static String dbUrl()      { return get("dbUrl"); }
    public static String dbUser()     { return get("dbUser"); }
    public static String dbPassword() { return get("dbPassword"); }

    private static String get(String key) {
        String fromCommandLine = System.getProperty(key);
        if (fromCommandLine != null) {
            return fromCommandLine;
        }
        String fromFile = PROPS.getProperty(key);
        if (fromFile == null) {
            throw new IllegalStateException("Missing config key: " + key);
        }
        return fromFile;
    }

    private static Properties load() {
        Properties props = new Properties();
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in == null) {
                throw new IllegalStateException("config.properties not found on classpath");
            }
            props.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read config.properties", e);
        }
        return props;
    }
}
