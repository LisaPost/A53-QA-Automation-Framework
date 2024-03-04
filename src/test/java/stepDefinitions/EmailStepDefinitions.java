package stepDefinitions;

import io.cucumber.java.en.And;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.EmailPage;
import pages.OutlookAccountPage;
import pages.PasswordPage;
import pages.StaySignedInPage;

public class EmailStepDefinitions {
    //String emailUrl = "https://www.microsoft.com/en-us/microsoft-365/outlook/log-in";
    String emailUrl = "https://outlook.office365.com";
    String resetPasswordUrl;
    public void setResetPasswordUrl(String url) {
        this.resetPasswordUrl = url;
    }

    @And("I open Reset Password page")
    public void iOpenResetPasswordPage() {
        // Assertion to check if resetPasswordUrl is not null
        Assert.assertNotNull(resetPasswordUrl, "Reset password URL is null");
        BaseDefinition.getThreadLocal().get(resetPasswordUrl);
    }

    @And("I open outlook email")
    public void iOpenOutlookEmail() {
        BaseDefinition.getThreadLocal().get(emailUrl);
    }

    @And("I enter outlook email address")
    public void iEnterOutlookEmailAddress() {
        EmailPage emailPage = new EmailPage(BaseDefinition.getThreadLocal());
        /*emailPage.emailAddressField.clear();
        emailPage.emailAddressField.sendKeys("yelyzaveta.postnova@testpro.io");
        emailPage.emailAddressField.sendKeys(Keys.ENTER);*/
        emailPage.enterEmailAddress();
        emailPage.clickNextBtn();
    }

    @And("I enter outlook password")
    public void iEnterOutlookPassword() throws InterruptedException {
        PasswordPage passwordPage = new PasswordPage(BaseDefinition.getThreadLocal());
        Thread.sleep(2000);
        //Object passwordField;
        //WebElement outlookPasswordField = waitElementToBeVisible(passwordField);
        /*passwordPage.passwordField.clear();
        passwordPage.passwordField.sendKeys("stPGYWcp8TWf");*/
        passwordPage.enterPassword();
        //WebElement
        //Thread.sleep(2000);
        //passwordPage.signInBtn.click();
        //passwordPage.signInBtn.sendKeys(Keys.ENTER);
        //passwordPage.passwordField.sendKeys(Keys.ENTER);
        passwordPage.clickSignInBtn();
    }

    @And("I confirm to stay signed in")
    public void iConfirmToStaySignedIn() throws InterruptedException {
        StaySignedInPage staySignedInPage = new StaySignedInPage(BaseDefinition.getThreadLocal());
        //Thread.sleep(2000);
        staySignedInPage.staySignedInBtn.click();
        //emailPage.staySignedInBtn.sendKeys(Keys.ENTER);
    }

    @And("I navigate to Inbox messages")
    public void iNavigateToInboxMessages() {
        OutlookAccountPage outlookAccountPage = new OutlookAccountPage(BaseDefinition.getThreadLocal());
        outlookAccountPage.inboxMsgs.click();
    }

    @And("I open Reset Password Notification")
    public void iOpenResetPasswordNotification() throws InterruptedException {
        OutlookAccountPage outlookAccountPage = new OutlookAccountPage(BaseDefinition.getThreadLocal());
        Thread.sleep(2000);
        String resetUrl = outlookAccountPage.getUrlToResetPassword();
        setResetPasswordUrl(resetUrl);
    }
}
