package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends BasePage{
    public RegistrationPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    @FindBy(xpath = "//a[@href='registration']")
    public WebElement regLink;
    @FindBy(xpath = "//input[@name='email']")
    public WebElement forgotPasswordEmailField;
    @FindBy(xpath = "//input[@type='submit']")
    public WebElement forgotPasswordFormSubmitBtn;
    @FindBy(xpath = "//input[@name='password']")
    public WebElement newPasswordField;
    @FindBy(xpath = "//input[@name='password_confirmation']")
    public WebElement newPasswordConfirmationField;
    @FindBy(xpath = "//input[@type='submit']")
    public WebElement newPasswordSubmitBtn;

    /*public WebElement getRegLink() {
        return regLink;
    }*/
}
