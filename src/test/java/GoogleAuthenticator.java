import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import pages.GoogleAuthenticatorPage;
import utils.AppiumHelper;
import utils.LoadProperty;

import java.net.MalformedURLException;
import java.util.List;

public class GoogleAuthenticator {

    public void addNewUserInAuthenticatorApp(AppiumDriver<MobileElement> driver,String name,String key)  {

        GoogleAuthenticatorPage authenticatorPage=new GoogleAuthenticatorPage(driver);
        authenticatorPage.clickPlusButton();

        authenticatorPage.clickProvidedKey();
        authenticatorPage.addAccount(name,key);

    }

    public String getCurrentSecretId(AppiumDriver<MobileElement> driver, String name) {

        String usernameId = "com.google.android.apps.authenticator2:id/current_user";
        String pinId = "com.google.android.apps.authenticator2:id/pin_value";
        String userListId = "com.google.android.apps.authenticator2:id/user_list";

        MobileElement userList = driver.findElement(By.id(userListId));

        List<MobileElement> usernames = userList.findElements(By.id(usernameId));
        List<MobileElement> pins = userList.findElements(By.id(pinId));

        String pin = "";
        for (int i = 0; i < usernames.size(); i++) {
            String testUser = usernames.get(i).getText();

            if (testUser.equals(name)) {
                pin = pins.get(i).getText();
                break;
            }


        }

        return pin.replaceAll("\\s+", "");
    }

    public void clearUser(AppiumDriver<MobileElement> driver,String name){
        String usernameId = "com.google.android.apps.authenticator2:id/current_user";
        String pinId = "com.google.android.apps.authenticator2:id/pin_value";
        String userListId = "com.google.android.apps.authenticator2:id/user_list";

        MobileElement userList = driver.findElement(By.id(userListId));

        List<MobileElement> usernames = userList.findElements(By.id(usernameId));

        for (MobileElement username : usernames) {
            String testUser = username.getText();

            if (testUser.equals(name)) {
                Point point = username.getLocation();
                TouchAction action = new TouchAction(driver);
                action.longPress(PointOption.point(point.getX(), point.getY()));
                action.perform();
                break;
            }

        }

        GoogleAuthenticatorPage authenticatorPage = new GoogleAuthenticatorPage(driver);
        try {
            authenticatorPage.deleteUser();
            authenticatorPage.confirmDelete();
        }catch (NoSuchElementException e){
            System.out.println("user not found");
        }

    }


    public static void main(String[] args) {
        AppiumHelper helper=new AppiumHelper();
        try {
            helper.startAuthenticatorApp();
            GoogleAuthenticator authenticator=new GoogleAuthenticator();
            LoadProperty property=new LoadProperty();
            String name= property.getProperty("username");
            authenticator.clearUser(AppiumHelper.authDriver,name);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}


