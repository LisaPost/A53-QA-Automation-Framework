package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OutlookAccountPage extends BasePage{
    String resetPasswordUrl;
    public OutlookAccountPage(WebDriver givenDriver) {
        super(givenDriver);
    }
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
    public String getUrlToResetPassword() throws InterruptedException {
        //wait.until(ExpectedConditions.elementToBeClickable(resetPasswordBtn));
        Thread.sleep(2000);
        //resetPasswordBtn.getAttribute("href");
        resetPasswordUrl = resetPasswordLink.getText();
        return resetPasswordUrl;
    }
}
