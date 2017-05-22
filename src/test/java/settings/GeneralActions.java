package settings;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static settings.Basic.driver;

/**
 * Created by Alenka on 14.05.2017.
 */
public class GeneralActions {

    public By loginInputLocator = By.id("signin-email");
    public By passwordInputLocator = By.id("signin-password");
    public By loginButtonLocator = By.xpath("//table[@class = 'flex-table password-signin']//button");
    private WebDriverWait wait;


    public void loginToSite (String login, String password){
        WebElement loginInput = driver.findElement(loginInputLocator);
        loginInput.sendKeys(login);
        WebElement passwordInput = driver.findElement(passwordInputLocator);
        passwordInput.sendKeys(password);
        WebElement loginButton = driver.findElement(loginButtonLocator);
        loginButton.click();


    }
    public String generateRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public void waitForContentLoad(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public int getDaysBetween (Timestamp start, Timestamp end)   {

        boolean negative = false;
        if (end.before(start))  {
            negative = true;
            Timestamp temp = start;
            start = end;
            end = temp;
        }

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(start);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        GregorianCalendar calEnd = new GregorianCalendar();
        calEnd.setTime(end);
        calEnd.set(Calendar.HOUR_OF_DAY, 0);
        calEnd.set(Calendar.MINUTE, 0);
        calEnd.set(Calendar.SECOND, 0);
        calEnd.set(Calendar.MILLISECOND, 0);


        if (cal.get(Calendar.YEAR) == calEnd.get(Calendar.YEAR))   {
            if (negative)
                return (calEnd.get(Calendar.DAY_OF_YEAR) - cal.get(Calendar.DAY_OF_YEAR)) * -1;
            return calEnd.get(Calendar.DAY_OF_YEAR) - cal.get(Calendar.DAY_OF_YEAR);
        }

        int days = 0;
        while (calEnd.after(cal))    {
            cal.add (Calendar.DAY_OF_YEAR, 1);
            days++;
        }
        if (negative)
            return days * -1;
        return days;
    }
}
