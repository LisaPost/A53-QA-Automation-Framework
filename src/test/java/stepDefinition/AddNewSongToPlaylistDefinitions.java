package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;

public class AddNewSongToPlaylistDefinitions {
    public String playlistName;
    String expectedSongAddedMsg = "Added 1 song into \""+ playlistName + ".\"";

    @When("I search a song {string}")
    public void searchSong(String song) {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        try {
            homePage.searchField.clear();
            homePage.searchField.sendKeys(song);
            System.out.println("search field is cleared and song entered");
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
            System.out.println("issue with search field");
        }
    }

    @And("I tap View All")
    public void iTapViewAll() {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        try {
            homePage.viewAllSongsBtn.click();
            System.out.println("view all btn is tapped");
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
            System.out.println("view all btn is not tapped");
        }
    }

    @And("I select first song on list")
    public void iSelectFirstSongOnList() {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        try {
            homePage.firstSongOnList.click();
            System.out.println("first song is selected");
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
            System.out.println("first song is not selected");
        }
    }

    @And("I add song to existing playlist {string}")
    public void iAddSongToExistingPlaylist(String playlistName) {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        try {
            homePage.addToPlaylistBtn.click();
            homePage.playlistInDropdown.click();
            System.out.println("song is added to playlist: " + playlistName);
            this.playlistName = playlistName;
            expectedSongAddedMsg = "Added 1 song into \"" + this.playlistName + ".\"";
            System.out.println("Expected message: " + expectedSongAddedMsg);
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
            System.out.println("song is not added to playlist");
        }
    }

    @Then("I should see Song added to playlist message")
    public void iShouldSeeSongAddedToPlaylistMessage() {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        Assert.assertEquals(homePage.getSongAddedMsg(), expectedSongAddedMsg);
    }
}
