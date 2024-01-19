import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    @Test(dataProvider = "LoginPositiveTest", dataProviderClass = BaseTest.class, priority = 1)
    public void loginWithCorrectCredentials(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.provideEmail(username).providePassword(password).clickSubmit();

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }
    @Test(dataProvider = "LoginNegativeTests", dataProviderClass = BaseTest.class, priority = 2)
    public void loginWithIncorrectCredentials(String username, String password, String BaseUrl) {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.provideEmail(username).providePassword(password).clickSubmit();

        Assert.assertEquals(driver.getCurrentUrl(), BaseUrl);
    }
}
