import org.testng.Assert;
import org.testng.annotations.*;

public class AddPlaylistTests extends BaseTest {
    //@Test(dataProvider = "LoginPositiveTest", dataProviderClass = BaseTest.class)
    @Test(dataProvider = "AddPlaylistPositiveTest", dataProviderClass = BaseTest.class)
    public void createNewPlaylist(String username, String password, String playlistName) {
        String expectedPlaylistCreatedMsg = "Created playlist \"" + playlistName + ".\"";
        provideEmail(username);
        providePassword(password);
        login();
        createPlaylist(playlistName);
        Assert.assertEquals(getPlaylistCreatedMsg(), expectedPlaylistCreatedMsg);
    }

    @Test(dataProvider = "RenamePlaylistPositiveTest", dataProviderClass = BaseTest.class)
    public void renamePlaylist(String username, String password, String playlistName, String newPlaylistName) {
        String expectedPlaylistUpdatedMsg = "Updated playlist \"" + newPlaylistName + ".\"";
        loginToApp(username, password);
        createPlaylist(playlistName);
        selectPlaylist();
        contextClickOnSelectedPlaylist();
        selectEditPlaylistOption();
        editPlaylist(newPlaylistName);
        Assert.assertEquals(getPlaylistUpdatedMsg(), expectedPlaylistUpdatedMsg);
    }
}
