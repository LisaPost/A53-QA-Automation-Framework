package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;
import pages.RegistrationPage;

public class RegistrationStepDefinitions {
    @When("I tap registration link")
    public void tapRegLink() {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.regLink.click();
    }

    @Then("I should be redirected to registration page")
    public void userShouldBeRedirectedToRegistrationPage() {
        String registrationUrl = "https://qa.koel.app/registration";
        Assert.assertEquals(BaseDefinition.getThreadLocal().getCurrentUrl(), registrationUrl);
    }
}
