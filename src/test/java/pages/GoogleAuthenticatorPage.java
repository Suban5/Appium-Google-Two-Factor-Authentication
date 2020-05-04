package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GoogleAuthenticatorPage {
    private AppiumDriver<MobileElement> driver;

    public GoogleAuthenticatorPage(AppiumDriver<MobileElement> driver) {
        this.driver=driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(accessibility = "Add an account")
    private MobileElement plusButton;

    @AndroidFindBy(id = "com.google.android.apps.authenticator2:id/user_list")
    private MobileElement authenticatorParent;

    @AndroidFindBy(xpath = "/android.widget.LinearLayout[@content-desc=\"6 3 2 3 2 9 urvutest0906\"]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView")
    private MobileElement username;

    @AndroidFindBy(id = "com.google.android.apps.authenticator2:id/user_list")
    private List<MobileElement> authenticationLink;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.TextView")
    private MobileElement enterProvidedKey;

    @AndroidFindBy(id = "com.google.android.apps.authenticator2:id/account_name")
    private MobileElement accountName;

    @AndroidFindBy(id ="com.google.android.apps.authenticator2:id/key_value")
    private MobileElement secretKey;

    @AndroidFindBy(id = "com.google.android.apps.authenticator2:id/add_account_button_enter_key")
    private MobileElement btnAdd;

    @AndroidFindBy(accessibility = "Remove")
    private MobileElement deleteUserIcon;
    @AndroidFindBy(id = "android:id/button1")
    private MobileElement confirmedRemove;

    public void clickPlusButton(){
        plusButton.click();
    }

    public void clickProvidedKey(){
        enterProvidedKey.click();
    }

    public void addAccount(String name, String key){
        accountName.click();
        accountName.sendKeys(name);
     //   secretKey.sendKeys(key);
     //   btnAdd.click();
    }
    public String getUsername(){
       return username.getText();
    }

    public void deleteUser(){
        deleteUserIcon.click();
    }

    public void confirmDelete(){
        confirmedRemove.click();
    }




}
