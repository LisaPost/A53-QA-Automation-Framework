import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
public class Homework16 extends BaseTest {
    @Test
    public void registrationNavigation() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String url = "https://qa.koel.app/";
        String url2 = "https://qa.koel.app/registration";
        driver.get(url);

        WebElement regLink = driver.findElement(By.cssSelector("a[href='registration']"));
        regLink.click();

        Thread.sleep(2000);

        Assert.assertEquals(driver.getCurrentUrl(), url2);

        driver.quit();

    }
}
