package com.test.enums;

public enum Browser {
    CHROME("chrome"),
    FIREFOX("firefox");

    private final String browserName;

    Browser(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserName() {
        return browserName;
    }
} 