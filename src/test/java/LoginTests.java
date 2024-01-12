import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

//@Parameters({"BaseUrl"})
public class LoginTests extends BaseTest {
    @Test(dataProvider = "LoginPositiveTest", dataProviderClass = BaseTest.class)
    public void loginValid(String username, String password) {
        provideEmail(username);
        providePassword(password);
        login();

        WebElement avatar = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[@data-testid='view-profile-link']")));
        Assert.assertTrue(avatar.isDisplayed());
    }
    @Test(dataProvider = "LoginNegativeTests", dataProviderClass = BaseTest.class)
    public void loginInvalid(String username, String password, String BaseUrl) {
        provideEmail(username);
        providePassword(password);
        login();
        Assert.assertEquals(driver.getCurrentUrl(), BaseUrl);
    }
}