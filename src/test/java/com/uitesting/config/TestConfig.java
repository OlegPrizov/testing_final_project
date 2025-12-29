package com.uitesting.config;

public final class TestConfig {

    private TestConfig() {}

    // keys
    private static final String WEB_BASE_URL = "webBaseUrl";
    private static final String APPIUM_SERVER_URL = "appiumServerUrl";
    private static final String DEVICE_NAME = "deviceName";
    private static final String PLATFORM_VERSION = "platformVersion";
    private static final String BROWSER = "browser";

    // defaults
    private static final String DEFAULT_WEB_BASE_URL = "https://en.wikipedia.org";
    private static final String DEFAULT_APPIUM_SERVER_URL = "http://127.0.0.1:4723";
    private static final String DEFAULT_DEVICE_NAME = "Android Emulator";
    private static final String DEFAULT_PLATFORM_VERSION = "";
    private static final String DEFAULT_BROWSER = "chrome";

    private static String prop(String key, String defaultValue) {
        String value = System.getProperty(key);
        if (value == null) return defaultValue;
        value = value.trim();
        return value.isEmpty() ? defaultValue : value;
    }

    public static String webBaseUrl() {
        return prop(WEB_BASE_URL, DEFAULT_WEB_BASE_URL);
    }

    public static String appiumServerUrl() {
        return prop(APPIUM_SERVER_URL, DEFAULT_APPIUM_SERVER_URL);
    }

    public static String deviceName() {
        return prop(DEVICE_NAME, DEFAULT_DEVICE_NAME);
    }

    public static String platformVersion() {
        return prop(PLATFORM_VERSION, DEFAULT_PLATFORM_VERSION);
    }

    public static String browser() {
        return prop(BROWSER, DEFAULT_BROWSER);
    }

    public static String udid() {
        return System.getProperty("udid", "");
    }
}
