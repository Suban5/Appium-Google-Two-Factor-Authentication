import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import pages.AccountPage;
import pages.TorqueLoginPage;
import utils.AppiumHelper;
import utils.LoadProperty;

import java.net.MalformedURLException;

public class Test {

    public static void main(String[] args) {
        LoadProperty prop = new LoadProperty();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        String appPackage = prop.getProperty("torque.appPackage");

        try {
            AppiumHelper helper = new AppiumHelper();
            helper.startTorqueApp();

            //user login
            TorqueLoginPage loginPage = new TorqueLoginPage(AppiumHelper.torqueDriver);

            System.out.println("Login in user");
            loginPage.loginUser(username, password);

            Thread.sleep(5000);
            System.out.println("clicking account");
            (new TouchAction(AppiumHelper.torqueDriver)).tap(PointOption.point(963, 2127)).perform();


            System.out.println("Activating 2FA");
            String appKey = activateGoogle2FA();

            //Open Authenticator app
            helper.startAuthenticatorApp();
            Thread.sleep(6000);

            GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();
            //clear user if already exist
            googleAuthenticator.clearUser(AppiumHelper.authDriver, username);
            //add new user Authenticator app
            googleAuthenticator.addNewUserInAuthenticatorApp(AppiumHelper.authDriver, username, appKey);
            //get key from authenticator app
            String currentKey = googleAuthenticator.getCurrentSecretId(AppiumHelper.authDriver, username);
            System.out.println(currentKey);

            System.out.println("Trying to switch recent app");
            Thread.sleep(8000);
            AppiumHelper.torqueDriver.activateApp(appPackage);

            System.out.println("Pasting secret key to app");
            //paste secret code
            AccountPage accountPage = new AccountPage(AppiumHelper.torqueDriver);
            accountPage.inputSecretKey(currentKey);
            //click Next
            accountPage.clickNextBtn();

            //click ok
            accountPage.clickConfirmBtn();

            Thread.sleep(3000);
            //swipe down
            //((new TouchAction(driver)).press({x:  TouchAc 535, y: 1413}).moveTo({x: 521: y: 657}).release().perform());
            AppiumHelper.swipe(AppiumHelper.torqueDriver, 535, 535, 1413, 657);
            //click logout user
            accountPage.logoutUser();

            Thread.sleep(5000);
            //login user

            loginPage = new TorqueLoginPage(AppiumHelper.torqueDriver);
            loginPage.loginUser(username, password);
            Thread.sleep(3000);

            //open authenticator app
            helper.startAuthenticatorApp();
            //copy code
            GoogleAuthenticator authenticator = new GoogleAuthenticator();
            currentKey = authenticator.getCurrentSecretId(AppiumHelper.authDriver, username);
            //switch torque app
            AppiumHelper.torqueDriver.activateApp(appPackage);

            //input secret key
            accountPage = new AccountPage(AppiumHelper.torqueDriver);
            accountPage.inputSecretKey(currentKey);
            //click submit/next btn
            accountPage.clickNextBtn();

        } catch (MalformedURLException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

        //driver.quit();
    }


    public static String activateGoogle2FA() throws InterruptedException {
        AccountPage accountPage = new AccountPage(AppiumHelper.torqueDriver);
        accountPage.activate2FA();

        Thread.sleep(2000);
        //click NextButton
        accountPage.clickNextBtn();
        //copy code
        String key = accountPage.copyKey();
        //click next
        accountPage.clickNextBtn();
        //click next
        accountPage.clickNextBtn();
        return key;

    }


}
