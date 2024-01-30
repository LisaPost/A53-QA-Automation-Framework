package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.HomePage;

public class PlaySongStepDefinitions {
    @When("I click PlayNext button")
    public void clickPlayNextBtn() {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        homePage.playNextBtn.click();
    }

    @And("I click Play button")
    public void clickPlayBtn() {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        homePage.playBtn.click();
    }

    @Then("song should be playing")
    public void isSongPlaying() {
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        Assert.assertTrue(homePage.isSongPlaying());
    }
}
