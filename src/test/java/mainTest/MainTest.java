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

    public By loginFormLocator = By.id("signin-email");
    public By profileBlockLocator = By.className("dashboard dashboard-left");
    public By profileIconLocator = By.id("user-dropdown-toggle");
    public By logoutButton = By.xpath("//li[@id='signout-button']/button");

    GeneralActions actions = new GeneralActions();

    @DataProvider(name = "credentials")
    public Object[][] loginToAdmin() {
        return new Object[][] {
                { "second10020@gmail.com","123456789"},
                { "",""},
        };
    }

    @Test(dataProvider = "credentials")
    public void main(String login, String password) {
        log("open the twitter site");
        driver.get("https://twitter.com/");
        log("make sure that login form is displayed");
        Assert.assertNotNull(loginFormLocator, "The 'login' form isn't displayed");

        log("Enter user's credentials");
        actions.loginToSite(login, password);
        Assert.assertNotNull(profileBlockLocator, "User isn't login to the site");

        log("Logout");
        WebElement profileIcon = driver.findElement(profileIconLocator);
        profileIcon.click();
        WebElement logout = driver.findElement(logoutButton);
        logout.click();
        Assert.assertNull(loginFormLocator,"user isn't logout from site");
    }


}