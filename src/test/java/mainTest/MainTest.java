package mainTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import settings.Basic;

/**
 * Created by Alenka on 14.05.2017.
 */
public class MainTest extends Basic {
    public static By loginFormLocator = By.id("signin-email");

    @Test
    public static void main(String[] args) {
        log("open the twitter site");
        driver.get("https://twitter.com/");
        log("make sure that login form is displayed");
        Assert.assertNotNull(loginFormLocator, "The 'login' form isn't displayed");
    }
}