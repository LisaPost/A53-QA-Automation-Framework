package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.PlaylistPage;

public class DeletePlaylistStepDefinitions {
    public String playlistName = null;
    String expectedPlaylistDeletedMsg = "Deleted playlist \"" + playlistName + ".\"";

    @And("I select playlist {string}")
    public void selectPlaylist(String playlistName) {
        PlaylistPage playlistPage = new PlaylistPage(BaseDefinition.getThreadLocal());
        playlistPage.selectPlaylist.click();
    }

    @When("I tap Delete Playlist button")
    public void tapDeletePlaylistButton() {
        PlaylistPage playlistPage = new PlaylistPage(BaseDefinition.getThreadLocal());
        playlistPage.deletePlaylistBtn.click();
        playlistPage.submitOkBtn.click();
    }

    @Then("{string} should be deleted")
    public void shouldBeDeleted(String playlistName) {
        PlaylistPage playlistPage = new PlaylistPage(BaseDefinition.getThreadLocal());
        this.playlistName = playlistName;
        expectedPlaylistDeletedMsg = "Deleted playlist \"" + playlistName + ".\"";
        Assert.assertEquals(playlistPage.getPlaylistDeletedMsg(), expectedPlaylistDeletedMsg);
    }
}
