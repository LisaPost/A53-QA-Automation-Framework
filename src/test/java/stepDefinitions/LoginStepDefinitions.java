package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import pages.LoginPage;

public class LoginStepDefinitions {
    String BaseUrl = "https://qa.koel.app/";
    @Given("I open Login page")
    public void openLoginPage() {
        BaseDefinition.getThreadLocal().get(BaseUrl);
    }
    @And("I log into app with valid {string} and {string}")
    public void logIntoApp(String email, String password) {
        LoginPage loginPage = new LoginPage(BaseDefinition.getThreadLocal());
        loginPage.provideEmail(email)
                .providePassword(password)
                .clickSubmit();
    }
}
