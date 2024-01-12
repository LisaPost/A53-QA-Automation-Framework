import org.testng.Assert;
import org.testng.annotations.*;

public class AddSongToPlaylistTests extends BaseTest {
    @Test(dataProvider = "AddSongPositiveTest", dataProviderClass = BaseTest.class)
    public void addNewSongToPlaylist(String username, String password,
                                     String playlistName, String song) {
        String expectedSongAddedMsg = "Added 1 song into \"" + playlistName + ".\"";
        provideEmail(username);
        providePassword(password);
        login();
        searchSong(song);
        viewAll();
        selectFirstItem();
        addSelectedSong();
        //createPlaylist(playlistName);
        Assert.assertEquals(getSongAddedMsg(), expectedSongAddedMsg);
    }
}