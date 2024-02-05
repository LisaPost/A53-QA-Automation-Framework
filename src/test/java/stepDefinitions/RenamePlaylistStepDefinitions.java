package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HomePage;
import pages.PlaylistPage;

import java.time.Duration;

public class RenamePlaylistStepDefinitions {
    @And("I select {string}")
    public void userSelectsPlaylist(String playlistToRename) {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        homePage.selectPlaylistToRename.click();
    }

    @And("I rightclick on selected {string}")
    public void rightclickOnSelectedPlaylist(String playlistToRename) {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        WebElement playlistElement = homePage.findElement(By.xpath("//section[@id='playlists']//a[contains(text(),\"Playlist to Rename\")]"));
        Actions actions = new Actions(BaseDefinition.getThreadLocal());
        actions.contextClick(playlistElement).perform();
    }

    @When("I select Edit option")
    public void selectEditPlaylistOption() {
        PlaylistPage playlistPage = new PlaylistPage(BaseDefinition.getThreadLocal());
        playlistPage.editOption.click();
    }

    @And("I enter new playlist name {string}")
    public void enterNewPlaylistName(String newPlaylistName) {
        PlaylistPage playlistPage = new PlaylistPage(BaseDefinition.getThreadLocal());
        playlistPage.editPlaylistField.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        playlistPage.editPlaylistField.sendKeys(newPlaylistName);
        playlistPage.editPlaylistField.sendKeys(Keys.ENTER);
    }

    @Then("playlist name should be changed to {string}")
    public void playlistNameShouldBeChanged(String newPlaylistName) {
        PlaylistPage playlistPage = new PlaylistPage(BaseDefinition.getThreadLocal());
        String expectedPlaylistUpdatedMsg = "Updated playlist \"" + newPlaylistName + ".\"";

        WebDriverWait wait = new WebDriverWait(BaseDefinition.getThreadLocal(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(playlistPage.waitPlaylistUpdatedMsg(), expectedPlaylistUpdatedMsg));

        Assert.assertEquals(playlistPage.getPlaylistUpdatedMsg(), expectedPlaylistUpdatedMsg);
    }
}
