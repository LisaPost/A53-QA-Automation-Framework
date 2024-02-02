package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{
    public RegistrationPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    @FindBy(xpath = "//a[@href='registration']")
    WebElement regLink;

    public WebElement getRegLink() {
        return regLink;
    }
    @SuppressWarnings("UnusedReturnValue")
    public RegistrationPage clickRegLink() {
        regLink.click();
        return this;
    }
}
