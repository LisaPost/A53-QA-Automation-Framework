package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import pages.HomePage;

public class CreatePlaylistStepDefinitions {
    @And("I click Create Playlist button")
        public void clickCreatePlaylistBtn() {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        homePage.createPlaylistBtn.click();
    }

    @When("I select Create Simple Playlist option")
    public void selectCreateSimplePlaylistOption() {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        homePage.createSimplePlaylistOption.click();
    }

    @And("I enter {string}")
    public void enterPlaylistName(String playlistToRename) {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        homePage.playlistNameTextField.sendKeys(playlistToRename);
        homePage.playlistNameTextField.sendKeys(Keys.ENTER);
    }
    /*public String getPlaylistCreatedMsg() {

        return parentSuccessMsg.getText();
    }*/

    @Then("I should see Playlist created message for {string}")
    public void userShouldSeePlaylistCreatedMessage(String playlistName) {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        String expectedPlaylistCreatedMsg = "Created playlist \"" + playlistName + ".\"";
        Assert.assertEquals(homePage.getPlaylistCreatedMsg(), expectedPlaylistCreatedMsg);
    }
}
