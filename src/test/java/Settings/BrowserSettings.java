package Settings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

/**
 * Created by Ihor on 3/26/2017.
 */
public class BrowserSettings {
    protected static WebDriver getDriver() {
        return driver;
    }

    private static WebDriver driver;

    //Setup the environment before running the test suite
    @BeforeSuite
    public void setUp() {
        log("Initialize WebDriver");
        String chromeDriverPath = System.getProperty("chrome.driver.executable");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get("https://www.google.com");
    }

    //Operations after finish running the test suite
    @AfterSuite
    public void tearDown() {
        log("Quit from WebDriver");
        driver.quit();
    }

    //The method to generate log message to the report
    //Log message could be found in "target/surefire-reports/index.html"
    protected static void log(String logMessage) {
        Reporter.log(logMessage + "<br>");
    }
}
