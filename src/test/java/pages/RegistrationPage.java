package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends BasePage{
    public RegistrationPage(WebDriver givenDriver) {
        super(givenDriver);
        //PageFactory.initElements(givenDriver, this);
    }
    @FindBy(xpath = "//a[@href='registration']")
    public WebElement regLink;

    /*public WebElement getRegLink() {
        return regLink;
    }*/
}
