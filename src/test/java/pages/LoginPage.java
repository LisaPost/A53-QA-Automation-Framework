package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
        //PageFactory.initElements(givenDriver, this);
    }
    @FindBy(css = "[type='email']")
    WebElement emailField;
    @FindBy(css = "[type='password']")
    WebElement passwordField;
    @FindBy(css = "[type='submit']")
    WebElement submitBtn;
    @FindBy(xpath = "//a[@href='registration']")
    public WebElement regLink;
    @FindBy(xpath = "//div[@class='login-wrapper']//form[@class='error']")
    public WebElement loginError;
    @FindBy(xpath = "//form[@data-testid='login-form']")
    public WebElement loginForm;
    public LoginPage provideEmail(String username) {
        emailField.sendKeys(username);
        return this;
    }
    public LoginPage providePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }
    @SuppressWarnings("UnusedReturnValue")
    public LoginPage clickSubmit() {
        submitBtn.click();
        return this;
    }
    @SuppressWarnings("UnusedReturnValue")
    public LoginPage login(String username, String password){
        provideEmail(username);
        providePassword(password);
        clickSubmit();
        return this;
    }
    public WebElement showLoginError() {
        return loginError;
    }
}
