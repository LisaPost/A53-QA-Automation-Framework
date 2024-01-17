package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
    //constructor:
    public LoginPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    //locators:
    By emailField = By.cssSelector("input[type='email']");
    By passwordField = By.cssSelector("input[type='password']");
    By submitBtn = By.cssSelector("button[type='submit']");
    //page methods:
    public void provideEmail(String username) {
        findElement(emailField).sendKeys(username);
    }
    public void providePassword(String password) {
        findElement(passwordField).sendKeys(password);
    }
    public void clickSubmit() {
        findElement(submitBtn).click();
    }
    public void login(String username, String password){
        provideEmail(username);
        providePassword(password);
        clickSubmit();
    }
}
