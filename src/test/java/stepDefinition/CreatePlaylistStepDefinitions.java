package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.HomePage;

public class CreatePlaylistStepDefinitions {
    public String playlistName = null;
    private String expectedPlaylistCreatedMsg = "Created playlist \"" + playlistName + ".\"";

    @And("I click Create Playlist button")
    public void clickCreatePlaylistBtn() {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        try {
            homePage.createPlaylistBtn.click();
            System.out.println("create playlist button is clicked");
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
            System.out.println("create playlist button is not found");
        }
    }
    @When("I select Create Simple Playlist option")
    public void selectCreateSimplePlaylistOption() {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        homePage.createSimplePlaylistOption.click();
    }

    @And("I enter playlistName {string}")
    public void enterPlaylistName(String playlistName) {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        homePage.playlistNameTextField.sendKeys(playlistName);
        homePage.playlistNameTextField.sendKeys(Keys.ENTER);

        this.playlistName = playlistName;
        expectedPlaylistCreatedMsg = "Created playlist \"" + playlistName + ".\"";
    }

    @Then("I should see Playlist created message")
    public void iShouldSeePlaylistCreatedMessage() {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        Assert.assertEquals(homePage.getPlaylistCreatedMsg(), expectedPlaylistCreatedMsg);
    }
}
