package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PasswordPage extends BasePage{
    public PasswordPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    //@FindBy(xpath = "//input[@name='passwd']")
    @FindBy(xpath = "//input[@type='password']")
    public WebElement passwordField;
    //@FindBy(xpath = "//input[@data-report-event='Signin_Submit']")
    //@FindBy(xpath = "//input[@type='submit']")
   /* @FindBy(xpath = "//input[@class='win-button button_primary button ext-button primary ext-primary']")
    public WebElement signInBtn;*/
    @FindBy(xpath = "//input[@value='Sign in']")
    public WebElement signInBtn;
    /*@FindBy(xpath = "//input[@type='submit']")
    public WebElement staySignedInBtn;*/

    public void enterPassword() {
        WebElement outlookPasswordField = waitElementToBeVisible(passwordField);
        actions.moveToElement(outlookPasswordField).sendKeys("stPGYWcp8TWf");
        //passwordPage.passwordField.sendKeys("stPGYWcp8TWf");
    }

    public void clickSignInBtn() {
        WebElement outlookSignInBtn = waitElementToBeVisible(signInBtn);
        actions.moveToElement(outlookSignInBtn).click().perform();
    }
}
