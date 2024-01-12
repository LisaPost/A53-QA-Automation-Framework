import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

public class Homework20Test extends BaseTest {
    @Test (dataProvider = "LoginAndPlaylistExample", dataProviderClass = BaseTest.class)
    public void deletePlaylist(String username, String password, String playlistName){
        String expectedPlaylistDeletedMsg = "Deleted playlist \"" + playlistName + ".\"";
        provideEmail(username);
        providePassword(password);
        login();
        createPlaylist(playlistName);
        selectPlaylist();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='del btn-delete-playlist']"))).click();
        Assert.assertEquals(getPlaylistDeletedMsg(), expectedPlaylistDeletedMsg);
    }
}