import io.appium.java_client.AppiumDriver;
import pages.GoogleAuthenticatorPage;
import utils.AppiumHelper;

import java.net.MalformedURLException;

public class Test123 {
    public static void main(String[] args) throws MalformedURLException {
        AppiumHelper helper=new AppiumHelper();
        helper.startAuthenticatorApp();

        GoogleAuthenticatorPage authenticatorPage=new GoogleAuthenticatorPage(AppiumHelper.authDriver);
        authenticatorPage.clickPlusButton();
        authenticatorPage.clickProvidedKey();
        authenticatorPage.addAccount("123 ","test2131231aasd");
        authenticatorPage.addAccount("test ","test2131231aasd");



    }
}
