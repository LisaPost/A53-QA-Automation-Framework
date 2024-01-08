import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework18 extends BaseTest {
    @Test
    public void playSong() throws InterruptedException {
        try {
            navigateToLoginPage();
            provideEmail("yelyzaveta.postnova@testpro.io");
            providePassword("YrkeNi92");
            login();
            Thread.sleep(2000);
            playNextSong();
            Thread.sleep(2000);
            WebElement soundBarPlay = driver.findElement(By.xpath("//div[@data-testid='sound-bar-play']"));
            Assert.assertTrue(soundBarPlay.isDisplayed());
        } catch (InterruptedException e) {
            System.out.println("Smth went wrong: " + e);
        }
    }
}
