package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
    public static void ExplicitWait(AppiumDriver<MobileElement> driver, MobileElement element, int timeOutInSeconds) {
        (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions
                .visibilityOf(element));
    }
}
