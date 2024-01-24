import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PlaylistPage;

public class PlaylistTests extends BaseTest {
    @Test(dataProvider = "AddPlaylistPositiveTest", dataProviderClass = BaseTest.class, priority = 5)
    public void deletePlaylist(String username, String password, String emptyPlaylistName) {
        LoginPage loginPage = new LoginPage(getDriver());
        PlaylistPage playlistPage = new PlaylistPage(getDriver());

        String expectedPlaylistDeletedMsg = "Deleted playlist \"" + emptyPlaylistName + ".\"";

        loginPage.login(username, password);
        playlistPage.selectPlaylist().tapDeletePlaylistBtn();

        Assert.assertEquals(playlistPage.getPlaylistDeletedMsg(), expectedPlaylistDeletedMsg);
    }
}
