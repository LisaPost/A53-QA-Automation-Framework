package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HomePage;
import pages.ProfilePage;

import java.time.Duration;
import java.util.UUID;

public class ProfileChangeStepDefinitions {

    @And("I tap avatar icon")
    public void tapAvatarIcon() {
       HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        WebDriverWait wait = new WebDriverWait(BaseDefinition.getThreadLocal(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(homePage.userAvatarIcon));
       homePage.userAvatarIcon.click();
   }

    @And("I provide current {string}")
    public void provideCurrentPassword(String password) {
       ProfilePage profilePage = new ProfilePage(BaseDefinition.getThreadLocal());
        profilePage.currentPasswordField.clear();
        profilePage.currentPasswordField.sendKeys(password);
    }

    @When("I provide new name")
    public void provideNewName() {
        ProfilePage profilePage = new ProfilePage(BaseDefinition.getThreadLocal());
        String newName = generateRandomName();
        profilePage.profileNameField.clear();
        profilePage.profileNameField.sendKeys(newName);
    }

    public String generateRandomName() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @And("I click Save")
    public void clickSave() {
       ProfilePage profilePage = new ProfilePage(BaseDefinition.getThreadLocal());
        profilePage.saveProfileBtn.click();
    }
    /*public String getEnteredProfileName() {
       ProfilePage profilePage = new ProfilePage(BaseDefinition.getThreadLocal());
       return profilePage.profileNameField.getAttribute("value");
    }*/

    @Then("profile name should be changed")
    public void profileNameShouldBeChanged() {
        ProfilePage profilePage = new ProfilePage(BaseDefinition.getThreadLocal());
        HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
        WebDriverWait wait = new WebDriverWait(BaseDefinition.getThreadLocal(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(homePage.actualProfileName, profilePage.getEnteredProfileName()));
        Assert.assertEquals(homePage.actualProfileName.getText(), profilePage.getEnteredProfileName());
    }
}
