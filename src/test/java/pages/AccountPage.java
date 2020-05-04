package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
    private AppiumDriver<MobileElement> driver;

    public AccountPage(AppiumDriver<MobileElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    @AndroidFindBy(id = "com.torque.android.torquewallet:id/txtStatusGoogleAuth")
    private MobileElement authenticationStatus;

    @AndroidFindBy(id = "com.torque.android.torquewallet:id/btnGoogleAuth")
    private MobileElement google2FA;

    //downloadAndInstall
    @AndroidFindBy(id = "com.torque.android.torquewallet:id/btnNext")
    private MobileElement btnNext;

    @AndroidFindBy(id = "com.torque.android.torquewallet:id/txtVerifyCode")
    private MobileElement key;


    @AndroidFindBy(id = "com.torque.android.torquewallet:id/edtGoogleAuthCode")
    private MobileElement inputSecretKey;

    @AndroidFindBy(id = "com.torque.android.torquewallet:id/confirm_button")
    private MobileElement btnConfirm;

    @AndroidFindBy(id = "com.torque.android.torquewallet:id/btnLogout")
    private MobileElement btnLogout;

    @AndroidFindBy(id = "com.torque.android.torquewallet:id/confirm_button")
    private MobileElement btnConfirmLogout;

    public void activate2FA(){
        if(authenticationStatus.getText().equals("OFF")){
            authenticationStatus.click();
        }
    }

    public void clickGoogle2FA(){
        google2FA.click();
    }

    public void clickNextBtn(){
        btnNext.click();
    }

    public String copyKey(){
        return key.getText();
    }

    public void inputSecretKey(String secretKey){
        inputSecretKey.sendKeys(secretKey);
    }
    public void logoutUser(){

        btnLogout.click();
        btnConfirmLogout.click();
    }
    //click ok button after enabling 2FA
    public void clickConfirmBtn(){
        btnConfirm.click();
    }


}
