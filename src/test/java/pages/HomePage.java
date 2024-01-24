package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }
    @FindBy(css = "img.avatar")
    WebElement userAvatarIcon;
    @FindBy(xpath = "//i[@data-testid='sidebar-create-playlist-btn']")
    WebElement createPlaylistBtn;
    @FindBy(xpath = "//li[@data-testid='playlist-context-menu-create-simple']")
    WebElement createSimplePlaylistOption;
    @FindBy(xpath = "//section[@id='playlists']//input[@type='text']")
    WebElement playlistNameTextField;
    @FindBy(css = "div.success.show")
    WebElement parentSuccessMsg;
    @FindBy(css = "[type='search']")
    WebElement searchField;
    @FindBy(css = "[data-test='view-all-songs-btn']")
    WebElement viewAllSongsBtn;
    //@FindBy(xpath = "//section[@id='songResultsWrapper']//table[contains(@class, 'items')]//tr[1]")
    @FindBy(css = ".all-songs tr.song-item:nth-child(1)")
    WebElement firstSongOnList;
    @FindBy(css = "[class='btn-add-to']")
    WebElement addToPlaylistBtn;
    @FindBy(xpath = "//section[@id='songResultsWrapper']//li[contains(@class,'playlist') and contains(text(),'New Playlist')]")
    WebElement playlistInDropdown;

    public WebElement getUserAvatar() {return userAvatarIcon;}
    public HomePage clickCreatePlaylistBtn() {createPlaylistBtn.click(); return this;}
    public HomePage selectCreateSimplePlaylistOption() {
        createSimplePlaylistOption.click();
        return this;
    }
    @SuppressWarnings("UnusedReturnValue")
    public HomePage enterPlaylistName(String playlistName) {
        playlistNameTextField.sendKeys(playlistName);
        playlistNameTextField.sendKeys(Keys.ENTER);
        return this;
    }
    public String getPlaylistCreatedMsg() {
        return parentSuccessMsg.getText();
    }
    public HomePage searchSong(String song) {
        searchField.clear();
        searchField.sendKeys(song);
        return this;
    }
    public HomePage viewAll() {
        try {
            viewAllSongsBtn.click();
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
        }
        return this;
    }
    public HomePage selectFirstSongOnList() {
        try {
            firstSongOnList.click();
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
        }
        return this;
    }
    @SuppressWarnings("UnusedReturnValue")
    public HomePage addSelectedSongToExistingPlaylist() {
        try {
            addToPlaylistBtn.click();
            playlistInDropdown.click();
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
        }
        return this;
    }
    public String getSongAddedMsg() {
        try {
            return parentSuccessMsg.getText();
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
            return null;
        }
    }
    @SuppressWarnings("UnusedReturnValue")
    public HomePage clickPlay(){
        WebElement playNextBtn = driver.findElement(By.xpath("//i[@data-testid='play-next-btn']"));
        WebElement playButton = driver.findElement(By.xpath("//span[@data-testid='play-btn']"));
        playNextBtn.click();
        playButton.click();
        return this;
    }
    public boolean isSongPlaying(){
        WebElement soundBar = driver.findElement(By.xpath("//div[@data-testid='sound-bar-play']"));
        return soundBar.isDisplayed();
    }
}
