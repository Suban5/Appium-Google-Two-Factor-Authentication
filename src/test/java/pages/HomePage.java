package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private AppiumDriver<MobileElement> driver;

    public HomePage(AppiumDriver<MobileElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver),this);
    }

    public void clickAccount(){
        TouchAction touchAction2 = new TouchAction(driver);
        touchAction2.tap(PointOption.point(958,2108)).perform();
    }
}
