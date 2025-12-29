package com.uitesting.tests.mobile;

import com.uitesting.pages.mobile.ArticlePage;
import com.uitesting.pages.mobile.OnboardingPage;
import com.uitesting.pages.mobile.SearchPage;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class WikipediaMobileTests extends BaseMobileTest {

    private SearchPage searchPage;
    private OnboardingPage onboardingPage;

    @BeforeMethod(alwaysRun = true)
    public void initPages() {
        if (driver == null || wait == null) {
            throw new SkipException("Mobile tests are skipped because the Appium driver was not initialized");
        }
        onboardingPage = new OnboardingPage(driver, wait);
        searchPage = new SearchPage(driver, wait);
        onboardingPage.skipIfPresent();
    }

    @Test(groups = "mobile")
    public void searchShowsTitlesAndDescriptions() {
        searchPage.openSearch();
        searchPage.typeQuery("Selenium");

        List<String> titles = searchPage.resultTitleTexts();

        Assert.assertFalse(
                titles.isEmpty(),
                "The search results list should contain at least one title"
        );

        Assert.assertTrue(
                titles.stream().anyMatch(t -> t.toLowerCase().contains("selenium")),
                "At least one search result title should contain the search query"
        );

        Assert.assertFalse(
                searchPage.resultDescriptions().isEmpty(),
                "Search results should include descriptions"
        );
    }

    @Test(groups = "mobile")
    public void openArticleFromSearch() {
        searchPage.openSearch();
        searchPage.typeQuery("Russia");

        String selectedTitle = searchPage.openResultWithText("Russia");
        ArticlePage articlePage = new ArticlePage(driver, wait);
        String openedTitle = articlePage.waitForTitleContaining(selectedTitle);

        Assert.assertTrue(
                openedTitle.toLowerCase().contains(selectedTitle.toLowerCase())
                        || selectedTitle.toLowerCase().contains(openedTitle.toLowerCase()),
                "The opened article title should correspond to the selected search result"
        );
    }

    @Test(groups = "mobile")
    public void clearSearchResetsResults() {
        searchPage.openSearch();
        searchPage.typeQuery("Selenium");

        Assert.assertFalse(
                searchPage.resultTitles().isEmpty(),
                "Search results should be displayed after entering a query"
        );

        searchPage.clearSearch();
        searchPage.waitResultsDisappear();

        Assert.assertEquals(
                searchPage.resultTitlesCount(),
                0,
                "Search results should be cleared after resetting the search input"
        );
    }
}