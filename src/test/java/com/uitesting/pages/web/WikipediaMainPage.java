package com.uitesting.pages.web;

import com.uitesting.config.TestConfig;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WikipediaMainPage extends BaseWebPage {

    private static final By PAGE_BODY = By.id("www-wikipedia-org");
    private static final By WELCOME_MESSAGE = By.id("mp-welcome");
    private static final By SEARCH_INPUT = By.name("search");
    private static final By RANDOM_ARTICLE_LINK = By.id("n-randompage");
    private static final By FEATURED_ARTICLE_TITLE = By.cssSelector("#mp-tfa b a");
    private static final By ARTICLE_TITLE = By.id("firstHeading");

    public WikipediaMainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean isWelcomeMessageVisible() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.id("mp-topbanner")),
                ExpectedConditions.presenceOfElementLocated(PAGE_BODY)
        ));

        return !driver.findElements(WELCOME_MESSAGE).isEmpty()
                && driver.findElement(WELCOME_MESSAGE).isDisplayed();
    }

    public WikipediaArticlePage searchFor(String term) {
        for (int i = 0; i < 3; i++) {
            try {
                WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
                input.click();
                input.sendKeys(Keys.chord(Keys.COMMAND, "a"), Keys.DELETE);
                input.sendKeys(term);
                input.sendKeys(Keys.ENTER);

                wait.until(ExpectedConditions.presenceOfElementLocated(ARTICLE_TITLE));

                return new WikipediaArticlePage(driver, wait);
            } catch (StaleElementReferenceException | ElementNotInteractableException ignored) {
            }
        }

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
        input.click();
        input.sendKeys(term);
        input.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.presenceOfElementLocated(ARTICLE_TITLE));

        return new WikipediaArticlePage(driver, wait);
    }

    public WikipediaArticlePage openRandomArticle() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(RANDOM_ARTICLE_LINK)).click();
        } catch (TimeoutException e) {
            driver.navigate().to(TestConfig.webBaseUrl() + "/wiki/Special:Random");
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(ARTICLE_TITLE));

        return new WikipediaArticlePage(driver, wait);
    }

    public String featuredArticleTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(FEATURED_ARTICLE_TITLE)).getText();
    }
}