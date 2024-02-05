package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HomePage;

import java.time.Duration;

public class AddNewSongStepDefinitions {
    @And("I enter playlistName {string}")
    public void enterPlaylistToAddSong(String playlistToAddSong) {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        homePage.playlistNameTextField.sendKeys(playlistToAddSong);
        homePage.playlistNameTextField.sendKeys(Keys.ENTER);
    }

    @When("I search a song {string}")
    public void searchSong(String song) {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        homePage.searchField.clear();
        homePage.searchField.sendKeys(song);
    }

    @And("I tap View All")
    public void tapViewAll() {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        homePage.viewAllSongsBtn.click();
    }

    @And("I select first song on list")
    public void selectFirstSongOnList() {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        homePage.firstSongOnList.click();
    }

    @And("I add song to existing playlist {string}")
    public void addSongToExistingPlaylist(String playlistToAddSong) {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        homePage.addToPlaylistBtn.click();
        homePage.playlistForSongInDropdown.click();
    }

    @Then("I should see Song added to {string} message")
    public void userShouldSeeSongAddedToPlaylistMessage(String playlistToAddSong) {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        String expectedSongAddedMsg = "Added 1 song into \""+ playlistToAddSong + ".\"";
        WebDriverWait wait = new WebDriverWait(BaseDefinition.getThreadLocal(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(homePage.waitSongAddedMsg(), expectedSongAddedMsg));

        Assert.assertEquals(homePage.getSongAddedMsg(), expectedSongAddedMsg);
    }
}
