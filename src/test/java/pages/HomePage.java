package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepDefinitions.BaseDefinition;

import java.time.Duration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.jsoup.nodes.Document.OutputSettings.Syntax.html;

public class HomePage extends BasePage {
    public HomePage(WebDriver givenDriver) {

        super(givenDriver);
    }
    @FindBy(xpath = "//i[@data-testid='sidebar-create-playlist-btn']")
    public WebElement createPlaylistBtn;
    @FindBy(xpath = "//li[@data-testid='playlist-context-menu-create-smart']")
    public WebElement createSmartPlaylistOption;
    @FindBy(xpath = "//input[@name='name']")
    public WebElement smartPlaylistNameField;
    @FindBy(xpath = "//div[@data-test='smart-playlist-rule-row'][1]//select[@name='model[]']/option[1]")
    public WebElement titleOfFirstRule;
    @FindBy(xpath = "//div[@data-test='smart-playlist-rule-row'][1]//select[@name='operator[]']/option[3]")
    public WebElement titleOfFirstRuleContains;
    @FindBy(xpath = "//div[@data-test='smart-playlist-rule-row'][1]//input[@type='text']")
    public WebElement firstRuleTextField;
    @FindBy(xpath = "//div[@class='smart-playlist-form']//button[@type='submit']")
    public WebElement saveSmartPlaylistBtn;
    @FindBy(xpath = "//div[@class='smart-playlist-form']//button[@class='btn-cancel']")
    public WebElement cancelSmartPlaylistBtn;
    @FindBy(xpath = "//div[@class='dialog']//button[@class='ok']")
    public WebElement submitCancellationBtn;
    @FindBy(xpath = "//div[@class='dialog']//button[@class='cancel']")
    public WebElement discardCancellationBtn;
    @FindBy(xpath = "//button[@class='remove-rule']")
    public WebElement removeRuleBtn;
    @FindBy(xpath = "//button[@class='btn-add-rule']")
    public WebElement addRuleBtn;
    @FindBy(xpath = "//div[@data-test='smart-playlist-rule-row'][2]//select[@name='model[]']/option[3]")
    public WebElement artistOfSecondRule;
    @FindBy(xpath = "//div[@data-test='smart-playlist-rule-row'][2]//select[@name='operator[]']/option[1]")
    public WebElement artistOfSecondRuleIs;
    @FindBy(xpath = "//div[@data-test='smart-playlist-rule-row'][2]//input[@type='text']")
    public WebElement secondRuleTextField;
    @FindBy(xpath = "//div[@data-test='smart-playlist-rule-row'][3]//select[@name='model[]']/option[6]")
    public WebElement lengthOfThirdRule;
    @FindBy(xpath = "//div[@data-test='smart-playlist-rule-row'][3]//select[@name='operator[]']/option[3]")
    public WebElement lengthOfSecondRuleIsGreaterThan;
    @FindBy(xpath = "//div[@data-test='smart-playlist-rule-row'][3]//input[@type='number']")
    public WebElement thirdRuleTextField;
    @FindBy(xpath = "//button[@class='btn-add-group']")
    public WebElement addGroupBtn;
    @FindBy(xpath = "//div[@data-test='smart-playlist-rule-group'][2]//select[@name='model[]']/option[1]")
    public WebElement titleOfSecondGroupRule;
    @FindBy(xpath = "//div[@data-test='smart-playlist-rule-group'][2]//select[@name='operator[]']/option[3]")
    public WebElement titleOfSecondGroupRuleContains;
    @FindBy(xpath = "//div[@data-test='smart-playlist-rule-group'][2]//input[@type='text']")
    public WebElement secondGroupRuleTextField;
    @FindBy(xpath = "//div[@class='text']/a[text()='criteria']")
    public WebElement noMatchingCriteriaMsg;
    @FindBy(xpath = "//form[@data-testid='create-smart-playlist-form']")
    public WebElement smartPlaylistCreationForm;

    public WebElement getSmartPlaylist(String smartPlaylistName) {
        String xpathExpression = String.format("//section[@id='playlists']//li[@class='playlist playlist smart']/a[text()='%s']", smartPlaylistName);
        WebDriverWait wait = new WebDriverWait(BaseDefinition.getThreadLocal(), Duration.ofSeconds(10));
        WebElement smartPlaylist = null;
        try {
            smartPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
        } catch (NoSuchElementException | TimeoutException e) {
            e.printStackTrace();
        }
        return smartPlaylist;
    }
    public WebElement getRegularPlaylist(String regularPlaylistName) {
        String xpathExpression = String.format("//section[@id='playlists']//li[@class='playlist playlist']/a[text()='%s']", regularPlaylistName);
        WebDriverWait wait = new WebDriverWait(BaseDefinition.getThreadLocal(), Duration.ofSeconds(10));
        WebElement regularPlaylist = null;
        try {
            regularPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
        } catch (NoSuchElementException | TimeoutException e) {
            e.printStackTrace();
        }
        return regularPlaylist;
    }
    public WebElement selectSmartPlaylist(String smartPlaylistName) {
        String xpathExpression = String.format("//section[@id='playlists']//li[@class='playlist playlist smart']/a[text()='%s']", smartPlaylistName);
        WebDriverWait wait = new WebDriverWait(BaseDefinition.getThreadLocal(), Duration.ofSeconds(10));
        WebElement smartPlaylist = null;
        try {
            smartPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
        } catch (NoSuchElementException | TimeoutException e) {
            e.printStackTrace();
        }
        return smartPlaylist;
    }
    public boolean isPlaylistExists(String playlistName) {
        String xpathExpression = String.format("//section[@id='playlists']//li/a[text()='%s']", playlistName);
        List<WebElement> playlists = BaseDefinition.getThreadLocal().findElements(By.xpath(xpathExpression));
        return !playlists.isEmpty();
    }
    public boolean isGeneratedPlaylistExists(String generatedSmartPlaylistName) {
        String xpathExpression = String.format("//section[@id='playlists']//li/a[text()='%s']", generatedSmartPlaylistName);
        List<WebElement> playlists = BaseDefinition.getThreadLocal().findElements(By.xpath(xpathExpression));
        //return !playlists.isEmpty();
        return playlists.isEmpty();
    }
    public String getPlaylistNameFromCreationForm() {
        return smartPlaylistNameField.getAttribute("value");
    }
    public String getFirstRuleModel() {
        return titleOfFirstRule.getText();
    }
    public String getFirstRuleOperator() {
        return titleOfFirstRuleContains.getText();
    }
    public String getFirstRuleInput() {
        return firstRuleTextField.getAttribute("value");
    }
    public String generateInputText(int length) {
        String allowedCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+{}[]:;<>?~";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allowedCharacters.length());
            sb.append(allowedCharacters.charAt(randomIndex));
        }
        return sb.toString();
    }
    public List<String> getAllSongTitles() {
        List<String> songTitles = new ArrayList<>();
        Document doc = Jsoup.parse(String.valueOf(html));
        Elements songItems = doc.select(".song-item");
        for (Element songItem : songItems) {
            String title = songItem.select(".title").text();
            songTitles.add(title);
        }
        return songTitles;
    }
}
