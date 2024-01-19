package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    @FindBy(css = "[type='email']")
    WebElement emailField;
    @FindBy(css = "[type='password']")
    WebElement passwordField;
    @FindBy(css = "[type='submit']")
    WebElement submitBtn;
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
}
