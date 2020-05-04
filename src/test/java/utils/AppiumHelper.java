package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.LoadProperty;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofSeconds;

public class AppiumHelper {
    public static AppiumDriver<MobileElement> torqueDriver;
    public static AppiumDriver<MobileElement> authDriver;

    public static void swipe(AppiumDriver<MobileElement> driver, int startX, int endX, int startY, int endY) {
        try {
            new TouchAction(driver).press(PointOption.point(startX, startY)).waitAction(WaitOptions.waitOptions(ofSeconds(1)))
                    .moveTo(PointOption.point(endX, endY)).release().perform();
        } catch (Exception e) {
            System.out.println("unable to swipe");
        }
    }

    public void startAuthenticatorApp() throws MalformedURLException {
        LoadProperty prop = new LoadProperty();
        String authenticatorPackage = prop.getProperty("authenicator.appPackage");
        String authenticatorActivity = prop.getProperty("authenicator.appActivity");
        String host = prop.getProperty("appium.host");
        String port = prop.getProperty("authenicator.appium.port");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "android device");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        caps.setCapability(MobileCapabilityType.NO_RESET, true);
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 600);
        caps.setCapability("appPackage", authenticatorPackage);
        caps.setCapability("appActivity", authenticatorActivity);
        authDriver = new AndroidDriver<MobileElement>(new URL("http://"+host+":" + port + "/wd/hub"), caps);
        authDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    public void startTorqueApp() throws MalformedURLException {
        LoadProperty prop = new LoadProperty();
        String torquePackage = prop.getProperty("torque.appPackage");
        String torqueActivity = prop.getProperty("torque.appActivity");
        String host = prop.getProperty("appium.host");
        String port = prop.getProperty("torque.appium.port");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "android device");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        caps.setCapability(MobileCapabilityType.NO_RESET, true);
        caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 600);
        caps.setCapability("appPackage", torquePackage);
        caps.setCapability("appActivity", torqueActivity);
        torqueDriver = new AndroidDriver<MobileElement>(new URL("http://"+host+":" + port + "/wd/hub"), caps);
        torqueDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        String cmd = "adb shell input keyevent 187";

        try {
            Runtime.getRuntime().exec(cmd);
            Thread.sleep(500);
            Runtime.getRuntime().exec(cmd);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
