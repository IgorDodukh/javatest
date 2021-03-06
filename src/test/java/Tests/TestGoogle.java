package Tests;

import Pages.GoogleHomePage;
import Pages.SearchResultsPage;
import Settings.BrowserSettings;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Ihor on 3/26/2017.
 */
public class TestGoogle extends BrowserSettings {
    private WebDriver driver;

    private GoogleHomePage googleHomePage;
    private SearchResultsPage searchResultsPage;

    private String searchValue = "qa";
    private String neededUrl = "https://uk.wikipedia.org/wiki/QA";


    /**
     * The test performs search by "searchValue" value in Google.
     * The test checks that the first found link is to Wikipedia.
     * The test checks that "searchValue" value exists on the each found page.
     * */
    @Test
    public void testSearchResults() throws InterruptedException {
        driver = getDriver();
        log("Initialize new Home Page");
        googleHomePage = new GoogleHomePage(driver);

        log("Verify login page title");
        String googlePageTitle = driver.getTitle();

        log("Send Search request");
        googleHomePage.sendSearchRequest(searchValue);

        log("Waiting of appearing search results");
        searchResultsPage = new SearchResultsPage(driver);

        log("Check that page title has expected value");
        Assert.assertEquals(googlePageTitle, "Google", "Not expected page title");

        searchResultsPage.checkFirstUrl(neededUrl);
        searchResultsPage.navigateThroughResultsLinks();
    }
}
