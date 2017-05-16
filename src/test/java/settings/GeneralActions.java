package settings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static settings.Basic.driver;

/**
 * Created by Alenka on 14.05.2017.
 */
public class GeneralActions {

    public By loginInputLocator = By.id("signin-email");
    public By passwordInputLocator = By.id("signin-password");
    public By loginButtonLocator = By.xpath("//table[@class = 'flex-table password-signin']//button");


    public void loginToSite (String login, String password){
        WebElement loginInput = driver.findElement(loginInputLocator);
        loginInput.sendKeys(login);
        WebElement passwordInput = driver.findElement(passwordInputLocator);
        passwordInput.sendKeys(password);
        WebElement loginButton =driver.findElement(loginButtonLocator);
        loginButton.click();

    }
}

