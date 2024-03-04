package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;

public class LoginStepDefinitions {
    String BaseUrl = "https://qa.koel.app/";
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

    @Then("I should be redirected to Home page")
    public void userShouldBeRedirectedToHomePage() {
        String homePageUrl = "https://qa.koel.app/#!/home";
        Assert.assertEquals(BaseDefinition.getThreadLocal().getCurrentUrl(), homePageUrl);
    }

    @Then("I should see login error")
    public void userShouldSeeLoginError() {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        Assert.assertTrue(loginPage.showLoginError().isDisplayed());
    }
}
