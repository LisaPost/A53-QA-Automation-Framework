package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StaySignedInPage extends BasePage{
    public StaySignedInPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    /*@FindBy(xpath = "//input[@type='submit']")
    public WebElement staySignedInBtn;*/
    @FindBy(xpath = "//input[@value='Yes']")
    public WebElement staySignedInBtn;
}
