import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework19Test extends BaseTest {
    @Test (dataProvider = "LoginAndPlaylistExample")
    public void deletePlaylist(String username, String password, String playlistName) throws InterruptedException {
        provideEmail(username);
        providePassword(password);
        login();
        createPlaylist(playlistName);
        Thread.sleep(2000);
        selectPlaylist();
        Thread.sleep(2000);
        WebElement deletePlaylistButton = driver.findElement(By.xpath("//button[@class='del btn-delete-playlist']"));
        deletePlaylistButton.click();

        Thread.sleep(2000);

        WebElement alertShow = driver.findElement(By.cssSelector("div.success.show"));
        Assert.assertEquals(alertShow.getText(), "Deleted playlist \"Playlist1.\"");
    }
}
