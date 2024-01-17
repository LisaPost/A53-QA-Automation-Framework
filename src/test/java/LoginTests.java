import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class LoginTests extends BaseTest {
    //create page objects which will take part in login tests:
    //LoginPage loginPage = new LoginPage(driver);
    //HomePage homePage = new HomePage(driver);
    /*
    * with objects created before methods test are failing with error:
    * org.gradle.api.internal.tasks.testing.TestSuiteExecutionException:
    * Could not complete execution for Gradle Test Executor 5.
    * Caused by: org.testng.TestNGException:
       Cannot instantiate class LoginTests
      Why?
    * */
    //tests themselves:
    @Test(dataProvider = "LoginPositiveTest", dataProviderClass = BaseTest.class, priority = 1)
    //@Test
    public void loginWithCorrectCredentials(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.provideEmail(username);
        loginPage.providePassword(password);
        loginPage.clickSubmit();

        Assert.assertTrue(homePage.getUserAvatar().isDisplayed());
    }
    @Test(dataProvider = "LoginNegativeTests", dataProviderClass = BaseTest.class, priority = 2)
    public void loginWithIncorrectCredentials(String username, String password, String BaseUrl) {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.provideEmail(username);
        loginPage.providePassword(password);
        loginPage.clickSubmit();

        Assert.assertEquals(driver.getCurrentUrl(), BaseUrl);
    }
}
