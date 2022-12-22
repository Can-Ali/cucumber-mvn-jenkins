package com.cydeo.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class Driver {
    /*
  Creating a private constructor so we are closing access to
  the object of this class from outside the class
   */
    private Driver() {
    }



    /*
    We make WebDriver private, because we want to close access from outside of the class
    We make it static because we will use it in a static method.

     */

    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {

            /*
            We read our browserType from configuration.properties.
            This way, we can control whic browser is opened from outside our code, from configuratio.properties.
             */
            String browserType = ConfigurationReader.getProperty("browser");
/*
Depending on the browserType that will be return from configuration.properties fole
switch statement will determine the case, and open the browser.
 */
            switch (browserType) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--no-sandbox");
//                    options.addArguments("--headless");
                    driver = new ChromeDriver(options);
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions options1 = new FirefoxOptions();
                    options1.addArguments("--no-sandbox");
                    driver = new FirefoxDriver(options1);
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;

            }
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
}
