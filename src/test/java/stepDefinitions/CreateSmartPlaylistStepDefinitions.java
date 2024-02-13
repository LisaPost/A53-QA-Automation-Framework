package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HomePage;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.List;

import static stepDefinitions.BaseDefinition.threadLocal;

public class CreateSmartPlaylistStepDefinitions {
    HomePage homePage = new HomePage(BaseDefinition.getThreadLocal());
    private String generatedSmartPlaylistName;
    @And("I click Create Playlist button")
    public void clickCreatePlaylistBtn() {
        homePage.clickCreatePlaylistBtn();
    }

    @When("I select Create Smart Playlist option")
    public void selectCreateSmartPlaylistOption() {
        homePage.createSmartPlaylistOption.click();
    }

    @And("I type playlist name {string}")
    public void enterSmartPlaylistName(String smartPlaylistName) {
        homePage.smartPlaylistNameField.sendKeys(smartPlaylistName);
    }

    @And("I provide first rule Title contains {string}")
    public void provideFirstRuleTitleContains(String firstText) {
        homePage.titleOfFirstRule.click();
        homePage.titleOfFirstRuleContains.click();
        homePage.firstRuleTextField.sendKeys(firstText);
    }

    @And("I save smart playlist")
    public void saveSmartPlaylist() {
        homePage.saveSmartPlaylistBtn.click();
    }

    @Then("smart playlist {string} should be created")
    public void smartPlaylistShouldBeCreated(String smartPlaylistName) {
        WebDriverWait wait = new WebDriverWait(BaseDefinition.getThreadLocal(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(homePage.getSmartPlaylist(smartPlaylistName)));
        Assert.assertTrue(homePage.getSmartPlaylist(smartPlaylistName).isDisplayed(), "Smart playlist is not created");
    }

    @When("I remove rule")
    public void userRemovesRule() {
        homePage.removeRuleBtn.click();
    }

    @Then("smart playlist {string} should not be created")
    public void smartPlaylistShouldNotBeCreated(String regularPlaylistName) {
        WebDriverWait wait = new WebDriverWait(BaseDefinition.getThreadLocal(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(homePage.getRegularPlaylist(regularPlaylistName)));
        Assert.assertTrue(homePage.getRegularPlaylist(regularPlaylistName).isDisplayed(), "Smart playlist created");
    }

    @And("I provide second rule Artist is {string}")
    public void provideSecondRuleArtistIs(String secondText) {
        homePage.addRuleBtn.click();
        homePage.artistOfSecondRule.click();
        homePage.artistOfSecondRuleIs.click();
        homePage.secondRuleTextField.sendKeys(secondText);
    }

    @And("I provide third rule Length is greater than {string}")
    public void provideThirdRuleLengthIsGreaterThan(String secondsAmount) {
        homePage.addRuleBtn.click();
        homePage.lengthOfThirdRule.click();
        homePage.lengthOfSecondRuleIsGreaterThan.click();
        homePage.thirdRuleTextField.sendKeys(secondsAmount);
    }

    @And("I provide group rule Title contains {string}")
    public void iProvideGroupRuleTitleContains(String secondText) {
        homePage.addGroupBtn.click();
        homePage.titleOfSecondGroupRule.click();
        homePage.titleOfSecondGroupRuleContains.click();
        homePage.secondGroupRuleTextField.sendKeys(secondText);
    }

    @And("No songs match the playlists criteria message should be displayed for {string}")
    public void noSongsMatchThePlaylistsCriteriaMessageShouldBeDisplayed(String smartPlaylistName) {
        homePage.selectSmartPlaylist(smartPlaylistName);
        Assert.assertTrue(homePage.noMatchingCriteriaMsg.isDisplayed(), "NoMatch message is not found");
    }

    @And("I cancel smart playlist creation")
    public void cancelSmartPlaylistCreation() {
        homePage.cancelSmartPlaylistBtn.click();
    }

    @And("I submit smart playlist cancellation")
    public void submitCancellation() {
        homePage.submitCancellationBtn.click();
    }

    @Then("no playlist with name {string} should exist")
    public void noPlaylistWithNameShouldExist(String smartPlaylistName) {
        boolean playlistExists = homePage.isPlaylistExists(smartPlaylistName);
        Assert.assertFalse(playlistExists, "Playlist exists");
    }

    @And("I discard smart playlist cancellation")
    public void discardSmartPlaylistCancellation() {
        homePage.discardCancellationBtn.click();
    }

    @Then("I still should see smart playlist creation form")
    public void userShouldSeeSmartPlaylistCreationForm() {
        Assert.assertTrue(homePage.smartPlaylistCreationForm.isDisplayed(), "Playlist creation form is not found");
    }

    @And("playlist name {string} should remain in form")
    public void playlistNameShouldRemainInForm(String smartPlaylistName) {
        Assert.assertEquals(homePage.getPlaylistNameFromCreationForm(),smartPlaylistName, "Playlist name is not found in form");
    }

    @And("first rule Title contains {string} should remain in form")
    public void firstRuleTitleContainsShouldRemainInForm(String firstText) {
        String firstRuleModelSelected = "Title";
        String firstRuleOptionSelected = "contains";
        Assert.assertEquals(homePage.getFirstRuleModel(),firstRuleModelSelected, "First rule model is not found");
        Assert.assertEquals(homePage.getFirstRuleOperator(),firstRuleOptionSelected, "First rule operator is not found");
        Assert.assertEquals(homePage.getFirstRuleInput(),firstText, "First rule input is not found");
    }

    @And("I close browser")
    public void closeBrowser() {
        threadLocal.get().close();
    }

    @And("I open browser")
    public void openBrowser() throws MalformedURLException {
        final int timeSeconds = 10;
        threadLocal.set(pickBrowser(System.getProperty("browser")));
        threadLocal.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeSeconds));
    }
    public WebDriver pickBrowser(String browser) throws MalformedURLException {
        WebDriver driver = null;
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURL = "http://192.168.1.78:4444";
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions optionsFirefox = new FirefoxOptions();
                optionsFirefox.addArguments("-private");
                return driver = new FirefoxDriver(optionsFirefox);
            case "edge":
                WebDriverManager.edgedriver().setup();
                return driver = new EdgeDriver();
            case "grid-chrome":
                caps.setCapability("browserName", "chrome");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            case "grid-firefox":
                caps.setCapability("browserName", "firefox");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            case "grid-edge":
                caps.setCapability("browserName", "edge");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions optionsChrome = new ChromeOptions();
                optionsChrome.addArguments("--disable-notifications", "--remote-allow-origins=*", "--incognito", "--start-maximized");
                optionsChrome.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); //need explanation
                return driver = new ChromeDriver(optionsChrome);
        }
    }

    @And("I enter playlist name {string} characters length")
    public void enterPlaylistNameCharactersLength(String numberOfChars) {
        String generatedSmartPlaylistName = homePage.generateInputText(Integer.parseInt(numberOfChars));
        homePage.smartPlaylistNameField.sendKeys(generatedSmartPlaylistName);
    }

    @Then("smart playlist should not be created")
    public void smartPlaylistShouldNotBeCreated() {
        boolean playlistExists = homePage.isGeneratedPlaylistExists(generatedSmartPlaylistName);
        Assert.assertFalse(playlistExists, "Generated Smart Playlist is created");
    }

    @Then("smart playlist should be created")
    public void smartPlaylistShouldBeCreated() {
        boolean playlistExists = homePage.isGeneratedPlaylistExists(generatedSmartPlaylistName);
        Assert.assertTrue(playlistExists, "Generated Smart Playlist is not created");
    }

    @And("I select playlist {string}")
    public void userSelectsPlaylist(String smartPlaylistName) throws InterruptedException{
        Thread.sleep(2000);
        homePage.selectSmartPlaylist(smartPlaylistName);
    }

    @Then("I should see related songs with {string} in playlist")
    public void userShouldSeeRelatedSongsInPlaylist(String firstText) {
        List<String> songTitles = homePage.getAllSongTitles();
        for (String title : songTitles) {
            boolean containsFirstText = title.toLowerCase().contains(firstText.toLowerCase());
            Assert.assertTrue(containsFirstText, "Song title does not contain expected text: " + firstText);
        }
    }
}
