package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmailPage extends BasePage{
    String resetPasswordUrl;
    public EmailPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    @FindBy(xpath = "//a[@data-bi-cn='SignIn']")
    public WebElement emailSignInBtn;
    @FindBy(xpath = "//input[@name='loginfmt']")
    public WebElement emailAddressField;
    /*@FindBy(xpath = "//button[@id='idSIButton9']")
    public WebElement emailAddressNextBtn;*/
    @FindBy(xpath = "//input[@value='Next']")
    public WebElement emailAddressNextBtn;
    @FindBy(xpath = "//input[@name='passwd']")
    public WebElement passwordField;
    //@FindBy(xpath = "//input[@data-report-event='Signin_Submit']")
    //@FindBy(xpath = "//input[@type='submit']")
    @FindBy(xpath = "//input[@class='win-button button_primary button ext-button primary ext-primary']")
    public WebElement signInBtn;
   /* @FindBy(xpath = "//input[@value='Sign in']")
    public WebElement signInBtn;*/
    /*@FindBy(xpath = "//input[@type='submit']")
    public WebElement staySignedInBtn;*/
    @FindBy(xpath = "//input[@value='Yes']")
    public WebElement staySignedInBtn;
    @FindBy(xpath = "//div[contains(@title, 'Inbox') and contains(., 'Inbox')]")
    public WebElement inboxMsgs;
    @FindBy(xpath = "//div[@data-convid and contains(@data-convid, 'TEST PRO Reset Password Notification')][1]")
    public WebElement firstUnreadTestProResetPasswordMsg;
    @FindBy(xpath = "//div[contains(@aria-label, 'Unread') and contains(@aria-label, 'TEST PRO Reset Password Notification')]")
    public WebElement unreadTestProResetPasswordMsg;
    @FindBy(xpath = "//a[contains(@class, 'x_button') and contains(@class, 'x_button-primary')]")
    public WebElement resetPasswordBtn;
    @FindBy(xpath = "//a[@data-linkindex='2']")
    public WebElement resetPasswordLink;

    /*public EmailPage clickSignInBtn() {
        emailSignInBtn.click();
        return this;
    }*/
    public String getUrlToResetPassword() throws InterruptedException {
        //wait.until(ExpectedConditions.elementToBeClickable(resetPasswordBtn));
        Thread.sleep(2000);
        //resetPasswordBtn.getAttribute("href");
        resetPasswordUrl = resetPasswordLink.getText();
        return resetPasswordUrl;
    }
}
