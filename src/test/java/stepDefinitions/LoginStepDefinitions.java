package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginStepDefinitions {
    String BaseUrl = "https://qa.koel.app/";
    //private BaseDefinition baseDefinition;
    @Given("I open Login page")
    public void openLoginPage() {
        BaseDefinition.getThreadLocal().get(BaseUrl);
    }

    @When("I enter email {string}")
    public void enterEmail(String email) {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.provideEmail(email);
    }

    @And("I enter password {string}")
    public void enterPassword(String password) {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.providePassword(password);
    }

    @And("I submit")
    public void clickSubmit() {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.clickSubmit();
    }

    @Then("I am logged in")
    public void userisLoggedIn() {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }

    @Then("I am not logged in")
    public void userIsNotLoggedIn() {
        Assert.assertEquals(BaseDefinition.getThreadLocal().getCurrentUrl(), BaseUrl);
    }

    @And("I log into app with valid {string} and {string}")
    public void logIntoApp(String email, String password) {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.provideEmail(email)
                .providePassword(password)
                .clickSubmit();
    }

    @Then("I should see login error")
    public void userShouldSeeLoginError() {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        Assert.assertTrue(loginPage.showLoginError().isDisplayed());
    }

    @Then("I should be redirected to Home page")
    public void userShouldBeRedirectedToHomePage() {
        String homePageUrl = "https://qa.koel.app/#!/home";
        Assert.assertEquals(BaseDefinition.getThreadLocal().getCurrentUrl(), homePageUrl);
    }

    @And("I log out")
    public void userLogsOut() {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        homePage.logOutBtn.click();
    }

    @And("I click Forgot password link")
    public void iClickForgotPasswordLink() {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.registerOrForgotPasswordLink.click();
    }

    @And("I provide email for password reset")
    public void iProvideEmailForPasswordReset() {
        RegistrationPage registrationPage = new RegistrationPage(BaseDefinition.getThreadLocal());
        registrationPage.forgotPasswordEmailField.clear();
        registrationPage.forgotPasswordEmailField.sendKeys("yelyzaveta.postnova@testpro.io");
        registrationPage.forgotPasswordFormSubmitBtn.click();
    }

    @And("I navigate to Reset password link in my email")
    public void iNavigateToResetPasswordLinkInMyEmail() throws MalformedURLException {
        //OutlookEmailAutomation automation = new OutlookEmailAutomation(baseDefinition, baseDefinition.getThreadLocal());
        OutlookEmailAutomation automation = new OutlookEmailAutomation();
        String userEmail = "yelyzaveta.postnova@testpro.io";
        String resetLink = automation.extractResetLinkFromEmail(userEmail);
        //BaseDefinition.navigateToResetLink(resetLink);
        automation.navigateToResetLink(resetLink);
    }

    @And("I set new password")
    public void iSetNewPassword() {
        RegistrationPage registrationPage = new RegistrationPage(BaseDefinition.getThreadLocal());
        registrationPage.newPasswordField.clear();
        registrationPage.newPasswordField.sendKeys("YrkeNi92");
        registrationPage.newPasswordConfirmationField.clear();
        registrationPage.newPasswordConfirmationField.sendKeys("YrkeNi92");
    }

    @And("I submit new password")
    public void iSubmitNewPassword() {
        RegistrationPage registrationPage = new RegistrationPage(BaseDefinition.getThreadLocal());
        registrationPage.newPasswordSubmitBtn.click();
    }
}
