import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import pages.AccountPage;
import pages.TorqueLoginPage;
import utils.AppiumHelper;
import utils.LoadProperty;

import java.net.MalformedURLException;

public class TorqueTest {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        LoadProperty prop = new LoadProperty();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        String appPackage= prop.getProperty("torque.appPackage");

        AppiumHelper helper = new AppiumHelper();
        //start  torque app
        helper.startTorqueApp();
        //login
        TorqueLoginPage loginPage = new TorqueLoginPage(AppiumHelper.torqueDriver);
        loginPage.loginUser(username, password);
        Thread.sleep(3000);

        //open authenticator app
        helper.startAuthenticatorApp();
        //copy code
        GoogleAuthenticator authenticator = new GoogleAuthenticator();
        String currentKey= authenticator.getCurrentSecretId(AppiumHelper.authDriver, username);
        //switch torque app
        AppiumHelper.torqueDriver.activateApp(appPackage);

        //input secret key
        AccountPage accountPage = new AccountPage(AppiumHelper.torqueDriver);
        accountPage.inputSecretKey(currentKey);
        //click submit/next btn
        accountPage.clickNextBtn();

    }


    public static void disable2FA() throws MalformedURLException, InterruptedException {

        LoadProperty prop = new LoadProperty();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        String appPackage= prop.getProperty("torque.appPackage");

        //open torque app
        AppiumHelper helper = new AppiumHelper();
        helper.startTorqueApp();

        //user login
        TorqueLoginPage loginPage = new TorqueLoginPage(AppiumHelper.torqueDriver);

        System.out.println("Login in user");
        loginPage.loginUser(username, password);

        Thread.sleep(5000);
        //click account
        System.out.println("clicking account");
        (new TouchAction(AppiumHelper.torqueDriver)).tap(PointOption.point(963, 2127)).perform();
        //click google Authenticator
        AccountPage accountPage = new AccountPage(AppiumHelper.torqueDriver);
        accountPage.clickGoogle2FA();

        //open authenticator app
        helper.startAuthenticatorApp();
        //copy code
        GoogleAuthenticator authenticator = new GoogleAuthenticator();
        String currentKey= authenticator.getCurrentSecretId(AppiumHelper.authDriver, username);
        //switch back to torque App
        AppiumHelper.torqueDriver.activateApp(appPackage);

        //input secret key
        accountPage = new AccountPage(AppiumHelper.torqueDriver);
        accountPage.inputSecretKey(currentKey);
        //click submit/next btn
        accountPage.clickNextBtn();
        //click ok btn
        accountPage.clickConfirmBtn();
    }
}
