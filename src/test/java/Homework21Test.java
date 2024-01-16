import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework21Test extends BaseTest {
    @Test(dataProvider = "RenamePlaylistPositiveTest", dataProviderClass = BaseTest.class)
    public void renamePlaylist(String username, String password, String playlistName, String newPlaylistName) {
        String expectedPlaylistUpdatedMsg = "Updated playlist \"" + newPlaylistName + ".\"";
        loginToApp(username, password);
        createPlaylist(playlistName);
        selectPlaylist(playlistName);
        contextClickOnSelectedPlaylist();
        selectEditPlaylistOption();
        editPlaylist(newPlaylistName);
        Assert.assertEquals(getPlaylistUpdatedMsg(), expectedPlaylistUpdatedMsg);
    }
}
