package Pages;

import Settings.BrowserSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Ihor on 3/26/2017.
 */
public class GoogleHomePage extends BrowserSettings {
    private final WebDriver driver;

    //define page locators
    @FindBy(id = "lst-ib")
    private WebElement searchField;

    @FindBy(name = "btnK")
    private WebElement searchButton;

    @FindBy(id = "hdtb-msb")
    private WebElement toolBar;

    //initialize objects for page factory
    public GoogleHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void setSearchValue(String searchValue) {
        log("Set search value: " + searchValue);
        searchField.sendKeys(searchValue);
    }

    private void sumbitSearchField() {
        log("Submit search field");
        searchField.submit();
    }

    public void sendSearchRequest(String searchString) {
        this.setSearchValue(searchString);
        this.sumbitSearchField();

        log("Wait until the toolbar on the results page will appear");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(toolBar));
    }
}
