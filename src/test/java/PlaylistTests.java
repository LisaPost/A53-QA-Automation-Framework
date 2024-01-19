import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.PlaylistPage;

public class PlaylistTests extends BaseTest {
    @Test(dataProvider = "AddPlaylistPositiveTest", dataProviderClass = BaseTest.class, priority = 5)
    public void deletePlaylist(String username, String password, String emptyPlaylistName) {
        LoginPage loginPage = new LoginPage(driver);
        PlaylistPage playlistPage = new PlaylistPage(driver);

        String expectedPlaylistDeletedMsg = "Deleted playlist \"" + emptyPlaylistName + ".\"";

        loginPage.login(username, password);
        playlistPage.selectPlaylist().tapDeletePlaylistBtn();

        Assert.assertEquals(playlistPage.getPlaylistDeletedMsg(), expectedPlaylistDeletedMsg);
    }
}
