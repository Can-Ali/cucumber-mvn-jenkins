package com.cydeo.utilities;
import java.util.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class BrowserUtils {
    public static void sleep(int time) {
        time *= 1000;

        try {
            Thread.sleep(time);
        } catch (Exception e) {

        }

    }

    public static void switchWindowAndVerify(String expectedInUrl, String expectedInTitle) {

        Set<String> allWindowsHandle = Driver.getDriver().getWindowHandles();
        for (String each : allWindowsHandle) {
            Driver.getDriver().switchTo().window(each);

            if (Driver.getDriver().getCurrentUrl().contains(expectedInUrl)) {
                break;
            }
        }
        String actualTitle = Driver.getDriver().getTitle();

        Assert.assertTrue(actualTitle.contains(expectedInTitle));

    }

    public static void verifyTitle(String expectedTitle) {
        Assert.assertEquals(Driver.getDriver().getTitle(), expectedTitle);
    }

    public static void waitForInvisibilityOf(WebElement webElement) {

        WebDriverWait wait= new WebDriverWait(Driver.getDriver(), 10);
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }
}
