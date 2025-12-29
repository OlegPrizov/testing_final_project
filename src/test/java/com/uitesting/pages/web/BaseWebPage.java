package com.uitesting.pages.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public abstract class BaseWebPage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    protected BaseWebPage(WebDriver driver, WebDriverWait wait) {
        this.driver = Objects.requireNonNull(driver, "driver must not be null");
        this.wait = Objects.requireNonNull(wait, "wait must not be null");
    }
}