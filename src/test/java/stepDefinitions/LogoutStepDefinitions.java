package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;

public class LogoutStepDefinitions {
    String BaseUrl = "https://qa.koel.app/";
    @Then("I should see Logout button")
    public void iShouldSeeLogoutButton() {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        Assert.assertTrue(homePage.logoutBtn.isDisplayed());
    }

    @When("I tap Logout button")
    public void iTapLogoutButton() {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        homePage.logoutBtn.click();
    }

    @Then("I should be logged out")
    public void iShouldBeLoggedOut() {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        Assert.assertTrue(loginPage.loginForm.isDisplayed());
    }

    @Then("I should be redirected to Login page")
    public void iShouldBeRedirectedToLoginPage() {
        Assert.assertEquals(BaseDefinition.getThreadLocal().getCurrentUrl(), BaseUrl);
    }
}
