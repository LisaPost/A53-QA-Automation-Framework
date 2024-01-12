import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

public class PlaySongTests extends BaseTest {
    @Test(dataProvider = "LoginPositiveTest", dataProviderClass = BaseTest.class)
    public void playSong(String username, String password) {
        provideEmail(username);
        providePassword(password);
        login();
        playNextSong();
        //WebElement soundBarPlay = driver.findElement(By.xpath("//div[@data-testid='sound-bar-play']"));
        WebElement soundBarPlay = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@data-testid='sound-bar-play']")));
        Assert.assertTrue(soundBarPlay.isDisplayed());
    }
}
