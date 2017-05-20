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
    public By tweetsNumberLocator = By.xpath("(//span/@data-count)[1]");

    GeneralActions actions = new GeneralActions();

    @DataProvider(name = "credentials")
    public Object[][] loginToAdmin() {
        return new Object[][] {
                { "second10020@gmail.com","123456789"},
        };
    }

    @Test(dataProvider = "credentials")
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

    }


}