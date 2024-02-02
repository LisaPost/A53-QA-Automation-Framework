import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;

public class RegistrationTests extends BaseTest{
    String registrationUrl = "https://qa.koel.app/registration";
    @Test
    public void registrationNavigation() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickRegLink();

        Assert.assertEquals(driver.getCurrentUrl(), registrationUrl);
    }
}
