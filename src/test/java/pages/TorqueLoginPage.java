package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import utils.Helper;

public class TorqueLoginPage {
    private AppiumDriver<MobileElement> driver;
    public TorqueLoginPage(AppiumDriver<MobileElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @AndroidFindBy(id = "com.torque.android.torquewallet:id/edtEmail")
    private MobileElement txtUsername;
    @AndroidFindBy(id = "com.torque.android.torquewallet:id/edtPassword")
    private MobileElement txtPassword;
    @AndroidFindBy(id = "com.torque.android.torquewallet:id/btnLogin")
    private MobileElement btnLogin;


    public void loginUser(String username, String password){
        txtUsername.clear();
        txtUsername.sendKeys(username);
        txtPassword.clear();
        txtPassword.sendKeys(password);
        btnLogin.click();
    }




}
