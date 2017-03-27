package Pages;

import Settings.BrowserSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ihor on 3/26/2017.
 */
public class SearchResultsPage extends BrowserSettings {
    private final WebDriver driver;

    private By searchResultsTitle = By.cssSelector("div h3>a");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private List<WebElement> getResultsList() {
        List<WebElement> resultsTitles = driver.findElements(searchResultsTitle);
        return resultsTitles;
    }

    public void checkFirstUrl(String url) {
        String firstFoundUrl = getResultsList().get(0).getAttribute("href");
        Assert.assertEquals(firstFoundUrl, url, "First found result has not expected URL");
    }

    public void navigateThroughResultsLinks() throws InterruptedException {
        //Run through the links on the results page by clicking on each title one by one
        log("Run through the links");
        List<WebElement> resultsTitlesList = driver.findElements(searchResultsTitle);

        for (WebElement resultTitle : resultsTitlesList) {

            //add new action to open link in new tab by ctrl+shift hotkey
            Actions newTab = new Actions(driver);
            newTab.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).click(resultTitle).keyUp(Keys.CONTROL).keyUp(Keys.SHIFT).build().perform();
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

            //handle windows change
            String base = driver.getWindowHandle();
            Set<String> set = driver.getWindowHandles();

            set.remove(base);
            assert set.size() == 1;
            driver.switchTo().window((String) set.toArray()[0]);

            //Check presence of company name on the page
            String pageSource = driver.getPageSource();
            Assert.assertTrue(pageSource.contains("Ortnec"),
                    "Company name is absent by URL: " + driver.getCurrentUrl());

            //close the window and switch back to the base tab
            driver.close();
            driver.switchTo().window(base);
        }
    }
}
