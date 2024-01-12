import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
public class RegistrationTests extends BaseTest {
    String registrationUrl = "https://qa.koel.app/registration";
    @Test
    public void registrationNavigation() {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("a[href='registration']"))).click();

        Assert.assertEquals(driver.getCurrentUrl(), registrationUrl);
    }
}