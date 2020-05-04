import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import pages.AccountPage;
import pages.TorqueLoginPage;
import utils.AppiumHelper;
import utils.LoadProperty;

import java.net.MalformedURLException;

public class LogoutTest {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        LoadProperty prop = new LoadProperty();

        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        String appPackage= prop.getProperty("torque.appPackage");

        //start app
        AppiumHelper helper=new AppiumHelper();
        helper.startTorqueApp();
        //login
        TorqueLoginPage loginPage= new TorqueLoginPage(AppiumHelper.torqueDriver);
        loginPage.loginUser(username, password);
        Thread.sleep(3000);
        //click account
        (new TouchAction(AppiumHelper.torqueDriver)).tap(PointOption.point(963, 2127)).perform();

        //swipe down
        AppiumHelper.swipe(AppiumHelper.torqueDriver,535,535,1413,657);
        //click logout
        AccountPage accountPage = new AccountPage(AppiumHelper.torqueDriver);
        accountPage.logoutUser();

        Thread.sleep(5000);
    }

}
