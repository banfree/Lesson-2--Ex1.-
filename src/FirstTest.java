import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest
{
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("platformName","Android");
    capabilities.setCapability("deviceName","AndroidTestDevice");
    capabilities.setCapability("platformVersion","8.0");
    capabilities.setCapability("automationName","Appium");
    capabilities.setCapability("appPackage","org.wikipedia");
    capabilities.setCapability("appActivity","main.MainActivity");
    capabilities.setCapability("app","C:\\Users\\home\\Desktop\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }
    @After
    public void tearDown()
    {
        driver.quit();
    }


    @Test
    public void testShowingAndDisappearingSearchResults()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5
        );
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Search did not find any articles for this word",
                15
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );
        WebElement empty_search_page_element = waitForElementPresent(
                By.xpath("//*[contains(@text, 'Search and read the free encyclopedia in your language')]"),
                "Text that should have appeared after the search was canceled was not found",
                15
        );
        String default_text_on_empty_search_page = empty_search_page_element.getAttribute ("text");
        Assert.assertEquals(
                "Text that should have appeared after the search was canceled was not found",
                "Search and read the free encyclopedia in your language",
                default_text_on_empty_search_page
        );

    }


        private WebElement waitForElementPresent(By by, String error_message,long timeoutInSeconds)
        {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.withMessage(error_message + "\n");
            return wait.until(
                    ExpectedConditions.presenceOfElementLocated(by)
            );
        }
        private WebElement waitForElementPresent(By by, String error_message)
        {
            return waitForElementPresent(by, error_message, 5);
        }
        private WebElement waitForElementAndClick(By by, String error_message,long timeoutInSeconds)
        {
            WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
            element.click();
            return element;
        }
        private WebElement waitForElementAndSendKeys(By by, String value, String error_message,long timeoutInSeconds)
        {
            WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
            element.sendKeys(value);
            return element;
        }
        private boolean waitForElementNotPresent(By by, String error_message,long timeoutInSeconds)
        {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.withMessage(error_message + "\n");
            return wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(by)
            );
        }
    }

