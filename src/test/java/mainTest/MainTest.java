package mainTest;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import settings.Basic;
import settings.GeneralActions;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    public By tweetCounterLocator = By.xpath("//div[@class='modal-tweet-form-container']//span[contains(@class,'tweet-counter')]");
    public By tweetTextLocator = By.xpath("//div[@class='tweet-content']//div[@id='tweet-box-global']");
    public By createTweetLocator = By.xpath("//div[@class='TweetBoxToolbar']//button[contains(@class,'tweet-action tweet-btn')]/span[1]");
    public By tweetTextFeedLocator = By.xpath("(//div[@class='stream']//li//div[@class='js-tweet-text-container'])[1]");
    public By followingLocator = By.xpath("(//span[@class='ProfileCardStats-statValue'])[2]");
    public By followButtonLocator = By.xpath("(//button[contains (@class, 'user-actions-follow-button')])[1]/span[1]");
    public By followerProfileLinkLocator = By.xpath("(//a[contains(@class,'ProfileCard-bg')])[1]");
    public By dateTweetLocator = By.xpath("(//span[@class='_timestamp js-short-timestamp'])[i]");
    public By tweetsListLocator = By.xpath("//div[contains(@class, 'js-stream-tweet')]");

    public By retweetBoxHeaderLocator = By.xpath("//form[contains(@class,'RetweetDialog-tweetForm')]");
    public By retweetBoxButtonLocator = By.xpath("//div[@class='tweet-button']//button//span[@class='Icon Icon--retweet']");

    public By homeButtonLocator = By.xpath("//li[@id='global-nav-home']");

    GeneralActions actions = new GeneralActions();

    @DataProvider(name = "credentials")
    public Object[][] loginToAdmin() {
        return new Object[][] {
                { "second10020@gmail.com","123456789"},
        };
    }

    @Test(dataProvider = "credentials", enabled = false)
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

    }

    @Test(dataProvider = "credentials", enabled = false)
    public void createTweetTest (String login, String password) throws InterruptedException {
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
        int tweetAmount = Integer.parseInt(tweetsNumber);

        log("Click the 'Tweet' button");
        WebElement tweetButton = driver.findElement(tweetsButtonLocator);
        tweetButton.click();

        log("Check the elements on the modal window");
        Assert.assertTrue(driver.findElement(tweetModalWinLocator).isDisplayed(), "'Tweet' modal window isn't displayed");
        Assert.assertTrue(driver.findElement(tweetBoxLocator).isDisplayed(), "'Text' field isn't displayed");
        Assert.assertTrue(driver.findElement(tweetImageLocator).isDisplayed(), "'Image Select' button isn't displayed");
        Assert.assertTrue(driver.findElement(tweetGifLocator).isDisplayed(), "'Gif' button isn't displayed");
        Assert.assertTrue(driver.findElement(tweetPollLocator).isDisplayed(), "'Poll' button isn't displayed");
        Assert.assertTrue(driver.findElement(tweetGeoLocator).isDisplayed(), "'GEO' button isn't displayed");
        Assert.assertTrue(driver.findElement(tweetCounterLocator).isDisplayed(), "The tweet counter isn't displayed");

        log("Check that 'Tweet' button isn't active");
        WebElement tweetTweetButton = driver.findElement(tweetTweetButtonLocator);
        Assert.assertNotNull(tweetTweetButton.getAttribute("disabled"), "The 'Tweet' button is enabled");

        log("Create text for tweet");
        String maxValueCounter = driver.findElement(tweetCounterLocator).getText();
        int valueCounter = Integer.parseInt(maxValueCounter);
        valueCounter = valueCounter - 5;
        String tweetText = actions.generateRandomString(valueCounter);

        log("Enter text of tweet");
        driver.findElement(tweetTextLocator).sendKeys(tweetText);
        log("Memorise new value of counter");
        String newValueCounter = driver.findElement(tweetCounterLocator).getText();
        int newCounter = Integer.parseInt(newValueCounter);

        log("Check that value of counter is decreased by difference max value of counter and of entered symbols");
        Assert.assertEquals(newCounter, 5, "The counter isn't decreased");

        log("Check that 'Tweet' button is active ");
        Assert.assertNull(tweetTweetButton.getAttribute("disabled"), "The 'Tweet' button is disabled");

        log("Create tweet");
        WebElement createTweet = driver.findElement(createTweetLocator);
        createTweet.click();

        log("Check that 'Creation pop-up isn't displayed'");
        Thread.sleep(4000);
        Assert.assertFalse(driver.findElement(tweetModalWinLocator).isDisplayed(), "'Tweet' modal window is displayed");

        log("Created tweet is displayed in news feed");
        String checkTextTweet = driver.findElement(tweetTextFeedLocator).getText();
        System.out.println(checkTextTweet);
        Assert.assertEquals(checkTextTweet, tweetText);

        log("Refresh the page");
        driver.navigate().refresh();
//        actions.waitForContentLoad(tweetsNumberLocator);

        log("Check that number of tweet is increased by 1");
        String newTweetNumber = driver.findElement(tweetsNumberLocator).getText();
        int newTweetAmount = Integer.parseInt(newTweetNumber);
        System.out.println(newTweetAmount);
        Assert.assertEquals(newTweetAmount, tweetAmount+1, "Total amount of tweets is increased by 1");
    }

    @Test(dataProvider = "credentials")
    public void retweetTest(String login, String password) throws InterruptedException {
        log("open the twitter site");
        driver.get("https://twitter.com/");
        log("make sure that login form is displayed");
        Assert.assertTrue(driver.findElement(loginFormLocator).isDisplayed(), "The 'login' form isn't displayed");

        log("Enter user's credentials");
        actions.loginToSite(login, password);
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(profileIconLocator).isDisplayed(), "User isn't login to the site");

        log("Check that user has followers");
        try{
            log("User has followers. Open followers list.");
            driver.findElement(followingLocator).click();
        }catch (NoSuchElementException e){
            log("User has no followers. Follow the new one.");
            driver.findElement(followButtonLocator).click();
            driver.get("https://twitter.com/");
            driver.findElement(followingLocator).click();
        }
        log("Open the first follower from the list");
        driver.findElement(followerProfileLinkLocator).click();

        log("Get number of tweets on the page");
        int tweetsQty = driver.findElements(tweetsListLocator).size();
        System.out.println(tweetsQty);
        String timestampString;


        log("Find tweet with created date more than 1 day compare with current date");
        for (int i = 1; i < tweetsQty + 1; i++){
            timestampString = driver.findElement(By.xpath("(//div[@id='timeline']//small[@class='time']//span)[" + i + "]"))
                    .getAttribute("data-time-ms");
            //compare current date with tweet day

            System.out.println("timestampString: " + timestampString);

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Timestamp timestamp2 = new Timestamp(Long.valueOf(timestampString));
            System.out.println("current timestamp: " + timestamp);
            System.out.println("found timestamp: " + timestamp2);
            System.out.println(actions.getDaysBetween(timestamp2, timestamp));

            if (actions.getDaysBetween(timestamp2, timestamp) > 1) {
                log("Memorize the number of retweet");
                String retweetNumber = driver.findElement(By.xpath("(//button[contains(@class,'s-actionRetweet')]//span[@class='ProfileTweet-actionCount'])[" + i + "]")).getAttribute("data-tweet-stat-count");
                System.out.println(retweetNumber);

                log("Click Retweet button");
                driver.findElement(By.xpath("(//span[@class='Icon Icon--retweet'])[" + i + "]")).click();

                log("Waiting retweet popup box");
                Thread.sleep(3000);

                log("Click Retweet button");
                driver.findElement(retweetBoxButtonLocator).click();

                log("Check the number of retweet is increased by 1");


                log("Click Home button");
                Thread.sleep(3000);
                driver.findElement(homeButtonLocator).click();
                break;
            }
        }


    }


}