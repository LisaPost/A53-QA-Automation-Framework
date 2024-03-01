package stepDefinitions;

import io.cucumber.java.en.And;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.EmailPage;

public class EmailStepDefinitions {
    //String emailUrl = "https://www.microsoft.com/en-us/microsoft-365/outlook/log-in";
    String emailUrl = "https://outlook.office365.com";
    String resetPasswordUrl;
    public void setResetPasswordUrl(String url) {
        this.resetPasswordUrl = url;
    }
    @And("I navigate to Reset password link in my email")
    public void iNavigateToResetPasswordLinkInMyEmail() throws InterruptedException {
        EmailPage emailPage = new EmailPage(BaseDefinition.getThreadLocal());
        BaseDefinition.getThreadLocal().get(emailUrl);
        //emailPage.emailSignInBtn.click();
        emailPage.emailAddressField.clear();
        emailPage.emailAddressField.sendKeys("yelyzaveta.postnova@testpro.io");
        emailPage.emailAddressField.sendKeys(Keys.ENTER);
        //emailPage.emailAddressNextBtn.click();
        emailPage.passwordField.clear();
        emailPage.passwordField.sendKeys("stPGYWcp8TWf");
        //emailPage.signInBtn.click();
        emailPage.signInBtn.sendKeys(Keys.ENTER);
        //emailPage.passwordField.sendKeys(Keys.ENTER);
        emailPage.staySignedInBtn.click();
        //emailPage.staySignedInBtn.sendKeys(Keys.ENTER);
        emailPage.inboxMsgs.click();
        emailPage.unreadTestProResetPasswordMsg.click();
        //emailPage.resetPasswordBtn.click();
        //emailPage.getUrlToResetPassword();
        //this.resetPasswordUrl = emailPage.getUrlToResetPassword();
        Thread.sleep(2000);
        String resetUrl = emailPage.getUrlToResetPassword();
        //BaseDefinition.getThreadLocal().get(resetPasswordUrl);
        setResetPasswordUrl(resetUrl);
    }
    @And("I open Reset Password page")
    public void iOpenResetPasswordPage() {
        // Assertion to check if resetPasswordUrl is not null
        Assert.assertNotNull(resetPasswordUrl, "Reset password URL is null");
        BaseDefinition.getThreadLocal().get(resetPasswordUrl);
    }
}
