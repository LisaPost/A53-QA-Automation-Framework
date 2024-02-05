package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HomePage;
import pages.PlaylistPage;

import java.time.Duration;

public class DeletePlaylistStepDefinitions {
    @When("I tap Delete Empty Playlist button")
    public void tapDeleteEmptyPlaylistBtn() {
        PlaylistPage playlistPage = new PlaylistPage(BaseDefinition.getThreadLocal());
        playlistPage.deletePlaylistBtn.click();
    }

    @When("I tap Delete Playlist button")
    public void tapDeletePlaylistButton() {
        PlaylistPage playlistPage = new PlaylistPage(BaseDefinition.getThreadLocal());
        playlistPage.deletePlaylistBtn.click();
        playlistPage.submitOkBtn.click();
    }
    @And("I select empty playlist {string}")
    public void userSelectsEmptyPlaylistToDelete(String emptyPlaylistToDelete) {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        homePage.selectEmptyPlaylistToDelete.click();
    }

    @And("I select playlist with song {string}")
    public void userSelectsPlaylistWithSong(String playlistToDelete) {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        homePage.selectPlaylistToDelete.click();
    }
    @Then("empty playlist {string} should be deleted")
    public void emptyPlaylistShouldBeDeleted(String emptyPlaylistToDelete) {
        PlaylistPage playlistPage = new PlaylistPage(BaseDefinition.getThreadLocal());
        String expectedPlaylistDeletedMsg = "Deleted playlist \"" + emptyPlaylistToDelete + ".\"";
        WebDriverWait wait = new WebDriverWait(BaseDefinition.getThreadLocal(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(playlistPage.waitPlaylistDeletedMsg(), expectedPlaylistDeletedMsg));

        Assert.assertEquals(playlistPage.getPlaylistDeletedMsg(), expectedPlaylistDeletedMsg);
    }
    @Then("playlist with song {string} should be deleted")
    public void playlistShouldBeDeleted(String playlistToDelete) {
        PlaylistPage playlistPage = new PlaylistPage(BaseDefinition.getThreadLocal());
        String expectedPlaylistDeletedMsg = "Deleted playlist \"" + playlistToDelete + ".\"";
        WebDriverWait wait = new WebDriverWait(BaseDefinition.getThreadLocal(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(playlistPage.waitPlaylistDeletedMsg(), expectedPlaylistDeletedMsg));

        Assert.assertEquals(playlistPage.getPlaylistDeletedMsg(), expectedPlaylistDeletedMsg);
    }

    @And("I add song to playlist {string}")
    public void addSongToExistingPlaylist(String playlistToDelete) {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        homePage.addToPlaylistBtn.click();
        homePage.playlistFromDropdown.click();
    }
}
