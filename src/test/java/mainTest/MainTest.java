package mainTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import settings.Basic;
import settings.GeneralActions;

/**
 * Created by Alenka on 14.05.2017.
 */
public class MainTest extends Basic {

    public By loginFormLocator = By.xpath("//div[@class='front-signin js-front-signin']");
    public By profileIconLocator = By.id("user-dropdown-toggle");
    public By logoutButton = By.xpath("//li[@id='signout-button']/button");
    public By tweetsNumberLocator = By.xpath("(//span[@class='ProfileCardStats-statValue'])[1]");
    public By tweetsButtonLocator = By.xpath("//button[@id='global-new-tweet-button']");
    public By tweetModalWinLocator = By.xpath("//div[@class='modal-content']//h3[@id='global-tweet-dialog-header']");
    public By tweetBoxLocator = By.id("tweet-box-global");
    public By tweetImageLocator = By.xpath("//div[@class='modal-tweet-form-container']//div[@class='image-selector']");
    public By tweetGifLocator = By.xpath("//div[@class='modal-tweet-form-container']//div[contains(@class,'FoundMediaSearch')]//button");
    public By tweetPollLocator = By.xpath("//div[@class='modal-tweet-form-container']//div[@class='PollCreator']");
    public By tweetGeoLocator = By.xpath("//div[@class='modal-tweet-form-container']//div[@class='geo-picker dropdown']");
    public By tweetTweetButtonLocator = By.xpath("//div[@class='modal-tweet-form-container']//button[contains(@class,'js-tweet-btn')]");
    public By tweetConterLocator = By.xpath("//div[@class='modal-tweet-form-container']//span[@class='tweet-counter']");




    GeneralActions actions = new GeneralActions();

    @DataProvider(name = "credentials")
    public Object[][] loginToAdmin() {
        return new Object[][] {
                { "second10020@gmail.com","123456789"},
        };
    }

    /*@Test(dataProvider = "credentials")
    public void main(String login, String password) throws InterruptedException {
        log("open the twitter site");
        driver.get("https://twitter.com/");
        log("make sure that login form is displayed");
        Assert.assertTrue(driver.findElement(loginFormLocator).isDisplayed(), "The 'login' form isn't displayed");

        log("Enter user's credentials");
        actions.loginToSite(login, password);
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(profileIconLocator).isDisplayed(), "User isn't login to the site");

        log("Logout");
        WebElement profileIcon = driver.findElement(profileIconLocator);
        profileIcon.click();
        WebElement logout = driver.findElement(logoutButton);
        logout.click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='global-nav-inner']//a[@href='/login']/span")).isDisplayed(),"user isn't logout from site");

    }*/

    @Test(dataProvider = "credentials")
    public void test2 (String login, String password) throws InterruptedException {
        log("open the twitter site");
        driver.get("https://twitter.com/");
        log("make sure that login form is displayed");
        Assert.assertTrue(driver.findElement(loginFormLocator).isDisplayed(), "The 'login' form isn't displayed");

        log("Enter user's credentials");
        actions.loginToSite(login, password);
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(profileIconLocator).isDisplayed(), "User isn't login to the site");

        log("Memorize the numbers of tweets");
        String tweetsNumber = driver.findElement(tweetsNumberLocator).getText();
        System.out.println(tweetsNumber);

        log("Click the 'Tweet' button");
        actions.waitForContentLoad(tweetsButtonLocator);
        WebElement tweetButton = driver.findElement(tweetsButtonLocator);
        tweetButton.click();

        log("Check the elements on the modal window");
        Assert.assertTrue(driver.findElement(tweetModalWinLocator).isDisplayed(), "'Tweet' modal window isn't displayed");
        Assert.assertTrue(driver.findElement(tweetBoxLocator).isDisplayed(), "'Text' field isn't displayed");
        Assert.assertTrue(driver.findElement(tweetImageLocator).isDisplayed(), "'Image Select' button isn't displayed");
        Assert.assertTrue(driver.findElement(tweetGifLocator).isDisplayed(), "'Gif' button isn't displayed");
        Assert.assertTrue(driver.findElement(tweetPollLocator).isDisplayed(), "'Poll' button isn't displayed");
        Assert.assertTrue(driver.findElement(tweetGeoLocator).isDisplayed(), "'GEO' button isn't displayed");
        Assert.assertTrue(driver.findElement(tweetConterLocator).isDisplayed(), "The tweet counter isn't displayed");

        log("Check that 'Tweet' button isn't active");
        WebElement tweetTweetButton = driver.findElement(tweetTweetButtonLocator);
        Assert.assertNotNull(tweetTweetButton.getAttribute("disabled"), "The 'Tweet' button is enabled");

        log("Enter text of tweet");
        String maxValueCounter = driver.findElement(tweetConterLocator).getText();
        int valueCounter = Integer.parseInt(maxValueCounter);
        actions.generateRandomString(valueCounter-1);
        System.out.println("Entered data:" + actions.generateRandomString(valueCounter-1));
    }


}