package settings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

/**
 * Created by Alenka on 14.05.2017.
 */
public class Basic {
    public static WebDriver driver;
    private String BROWSER = System.getProperty("browser");

    public WebDriver getDriver() {
        if (BROWSER.equalsIgnoreCase("Firefox")) {
            System.setProperty(
                    "webdriver.gecko.driver",
                    getResource("/geckodriver.exe"));
            return new FirefoxDriver();
        } else if (BROWSER.equalsIgnoreCase("IE")) {
            System.setProperty(
                    "webdriver.ie.driver",
                    getResource("/IEDriverServer.exe"));
            return new InternetExplorerDriver();
        } else if (BROWSER.equalsIgnoreCase("Chrome")) {
            System.setProperty(
                    "webdriver.chrome.driver",
                    getResource("/chromedriver.exe"));
            return new ChromeDriver();
        } else{
            log("please enter one of the following browser: Chrome, IE or Firefox");
            return null;
        }
    }


    private String getResource(String resourceName) {
        try {
            return Paths.get(Basic.class.getResource(resourceName).toURI()).toFile().getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return resourceName;
    }

    @BeforeClass
    public void setUp() {
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    public static void log(String message) {
        System.out.println(message);
    }
}
