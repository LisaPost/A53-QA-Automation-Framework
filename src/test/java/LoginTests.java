import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;

public class LoginTests extends BaseTest {
    @Test(dataProvider = "LoginPositiveTest", dataProviderClass = BaseTest.class)
    public void loginWithCorrectCredentials(String username, String password) {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.provideEmail(username).providePassword(password).clickSubmit();

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }
    @Test(dataProvider = "LoginNegativeTests", dataProviderClass = BaseTest.class)
    public void loginWithIncorrectCredentials(String username, String password, String BaseUrl) {
        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.provideEmail(username).providePassword(password).clickSubmit();

        Assert.assertEquals(driver.getCurrentUrl(), BaseUrl);
    }
    @Test(dataProvider = "LoginPositiveTest", dataProviderClass = BaseTest.class)
    public void logout(String username, String password) {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        RegistrationPage registrationPage = new RegistrationPage(getDriver());

        loginPage.login(username, password);
        homePage.logout();

        Assert.assertTrue(registrationPage.getRegLink().isDisplayed());

    }
}
