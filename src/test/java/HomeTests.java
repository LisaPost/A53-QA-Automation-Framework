import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class HomeTests extends BaseTest {
    @Test(dataProvider = "AddPlaylistPositiveTest", dataProviderClass = BaseTest.class, priority = 3)
    public void createSimplePlaylist(String username, String password, String playlistName) {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        String expectedPlaylistCreatedMsg = "Created playlist \"" + playlistName + ".\"";

        loginPage.login(username, password);
        homePage.clickCreatePlaylistBtn();
        homePage.selectCreateSimplePlaylistOption();
        homePage.enterPlaylistName(playlistName);

        Assert.assertEquals(homePage.getPlaylistCreatedMsg(), expectedPlaylistCreatedMsg);
    }
    @Test(dataProvider = "AddSongPositiveTest", dataProviderClass = BaseTest.class, priority = 4)
    public void addNewSongToPlaylist (String username, String password, String playlistName, String song) {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        String expectedSongAddedMsg = "Added 1 song into \""+ playlistName + ".\"";

        loginPage.login(username, password);
        homePage.searchSong(song);
        homePage.viewAll();
        homePage.selectFirstSongOnList();
        homePage.addSelectedSongToExistingPlaylist();

        Assert.assertEquals(homePage.getSongAddedMsg(), expectedSongAddedMsg);
    }
    @Test(dataProvider = "LoginPositiveTest", dataProviderClass = BaseTest.class, priority = 6)
    public void playSong(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        loginPage.login(username, password);
        homePage.clickPlay();

        Assert.assertTrue(homePage.isSongPlaying());
    }
}
