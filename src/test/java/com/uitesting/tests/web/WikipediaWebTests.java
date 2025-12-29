package com.uitesting.tests.web;

import com.uitesting.pages.web.WikipediaArticlePage;
import com.uitesting.pages.web.WikipediaMainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WikipediaWebTests extends BaseWebTest {

    private WikipediaMainPage mainPage() {
        return new WikipediaMainPage(driver, wait);
    }

    @Test(groups = "web")
    public void openEnglishMainPageShowsWelcomeBlock() {
        Assert.assertTrue(
                mainPage().isWelcomeMessageVisible(),
                "The English Wikipedia main page should display the welcome block"
        );
    }

    @Test(groups = "web")
    public void searchOpensExactArticle() {
        WikipediaArticlePage articlePage = mainPage().searchFor("Russia");

        Assert.assertEquals(
                articlePage.title(),
                "Russia",
                "The opened article title should exactly match the search query"
        );
    }

    @Test(groups = "web")
    public void featuredArticleSectionHasTitle() {
        String featuredTitle = mainPage().featuredArticleTitle();

        Assert.assertFalse(
                featuredTitle.isBlank(),
                "The featured article section should contain a non-empty title"
        );
    }

    @Test(groups = "web")
    public void randomArticleShowsHeadingAndContents() {
        WikipediaArticlePage articlePage = mainPage().openRandomArticle();

        Assert.assertFalse(
                articlePage.title().isBlank(),
                "The random article page should display a non-empty heading"
        );

        Assert.assertTrue(
                driver.getCurrentUrl().contains("/wiki/"),
                "The random article page URL should point to a wiki article"
        );
    }
}