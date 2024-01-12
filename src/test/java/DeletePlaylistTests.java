import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

public class DeletePlaylistTests extends BaseTest {
    @Test(dataProvider = "AddPlaylistPositiveTest", dataProviderClass = BaseTest.class)
    public void deletePlaylist(String username, String password, String playlistName) throws InterruptedException {
        String expectedPlaylistDeletedMsg = "Deleted playlist \"" + playlistName + ".\"";
        provideEmail(username);
        providePassword(password);
        login();
        createPlaylist(playlistName);
        selectPlaylist();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='del btn-delete-playlist']"))).click();
        Thread.sleep(2000);
        Assert.assertEquals(getPlaylistDeletedMsg(), expectedPlaylistDeletedMsg);
    }
}