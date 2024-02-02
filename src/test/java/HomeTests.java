import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class HomeTests extends BaseTest {
    @Test(dataProvider = "AddPlaylistPositiveTest", dataProviderClass = BaseTest.class)
    public void createSimplePlaylist(String username, String password, String playlistName) {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        String expectedPlaylistCreatedMsg = "Created playlist \"" + playlistName + ".\"";

        loginPage.login(username, password);
        homePage.clickCreatePlaylistBtn()
                .selectCreateSimplePlaylistOption()
                .enterPlaylistName(playlistName);

        Assert.assertEquals(homePage.getPlaylistCreatedMsg(), expectedPlaylistCreatedMsg);
    }
    @Test(dataProvider = "AddSongPositiveTest", dataProviderClass = BaseTest.class)
    public void addNewSongToPlaylist (String username, String password, String playlistName, String song) throws InterruptedException{
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        String expectedSongAddedMsg = "Added 1 song into \""+ playlistName + ".\"";

        loginPage.login(username, password);
        homePage.clickCreatePlaylistBtn()
                .selectCreateSimplePlaylistOption()
                .enterPlaylistName(playlistName);
        homePage.searchSong(song).
                viewAll()
                .selectFirstSongOnList()
                .addSelectedSongToExistingPlaylist("Playlist to add a song");
        WebElement dynamicPlaylist = homePage.getPlaylistElementByName(playlistName);
        wait.until(ExpectedConditions.textToBePresentInElement(homePage.waitSongAddedMsg(), expectedSongAddedMsg));

        Assert.assertEquals(homePage.getSongAddedMsg(), expectedSongAddedMsg);
    }
    @Test(dataProvider = "LoginPositiveTest", dataProviderClass = BaseTest.class)
    public void playSong(String username, String password) {
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());

        loginPage.login(username, password);
        homePage.clickPlay();

        Assert.assertTrue(homePage.isSongPlaying());
    }
}
