package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
        PageFactory.initElements(givenDriver, this);
    }
    @FindBy(css = "img.avatar")
    public WebElement userAvatarIcon;
    @FindBy(xpath = "//i[@data-testid='sidebar-create-playlist-btn']")
    public WebElement createPlaylistBtn;
    @FindBy(xpath = "//li[@data-testid='playlist-context-menu-create-simple']")
    public WebElement createSimplePlaylistOption;
    @FindBy(xpath = "//section[@id='playlists']//input[@type='text']")
    public WebElement playlistNameTextField;
    @FindBy(css = "div.success.show")
    public WebElement parentSuccessMsg;
    @FindBy(css = "[type='search']")
    public WebElement searchField;
    @FindBy(css = "[data-test='view-all-songs-btn']")
    public WebElement viewAllSongsBtn;
    @FindBy(xpath = "//section[@id='songResultsWrapper']//table[contains(@class, 'items')]//tr[1]")
    public WebElement firstSongOnList;
    @FindBy(css = "[class='btn-add-to']")
    public WebElement addToPlaylistBtn;
    @FindBy(xpath = "//section[@id='songResultsWrapper']//li[contains(@class,'playlist') and contains(text(),'New Playlist')]")
    public WebElement playlistInDropdown;
    @FindBy(xpath = "//i[@data-testid='play-next-btn']")
    public WebElement playNextBtn;
    @FindBy(xpath = "//span[@data-testid='play-btn']")
    public WebElement playBtn;
    @FindBy(xpath = "//div[@data-testid='sound-bar-play']")
    public WebElement soundBar;

    public WebElement getUserAvatar() {
        return userAvatarIcon;
    }
    @SuppressWarnings("UnusedReturnValue")
    /*public void clickCreatePlaylistBtn() {
        //createPlaylistBtn.click();
        //return this;
        //return findElement(createPlaylistBtn);
        findElement(createPlaylistBtn).click();
    }*/
    /*public HomePage clickCreatePlaylistBtn() {
        createPlaylistBtn.click();
        return this;}*/
    /*public HomePage selectCreateSimplePlaylistOption() {
        createSimplePlaylistOption.click();
        return this;
    }*/

    /*public HomePage enterPlaylistName(String playlistName) {
        playlistNameTextField.sendKeys(playlistName);
        playlistNameTextField.sendKeys(Keys.ENTER);
        return this;
    }*/
    public String getPlaylistCreatedMsg() {
        return parentSuccessMsg.getText();
    }
    /*public HomePage searchSong(String song) {
        searchField.clear();
        searchField.sendKeys(song);
        return this;
    }*/
    /*public HomePage viewAll() {
        try {
            viewAllSongsBtn.click();
            System.out.println("view all btn is tapped");
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
            System.out.println("view all btn is not tapped");
        }
        return this;
    }*/
    /*public HomePage selectFirstSongOnList() {
        try {
            firstSongOnList.click();
            System.out.println("first song is selected");
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
            System.out.println("first song is not selected");
        }
        return this;
    }*/
    @SuppressWarnings("UnusedReturnValue")
    /*public HomePage addSelectedSongToExistingPlaylist() {
        try {
            addToPlaylistBtn.click();
            playlistInDropdown.click();
            System.out.println("song is added to playlist");
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
            System.out.println("song is not added to playlist");
        }
        return this;
    }*/
    public String getSongAddedMsg() {
        try {
            return parentSuccessMsg.getText();
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
            return null;
        }
    }
    @SuppressWarnings("UnusedReturnValue")
    /*public HomePage clickPlay(){
        WebElement playNextBtn = driver.findElement(By.xpath("//i[@data-testid='play-next-btn']"));
        WebElement playButton = driver.findElement(By.xpath("//span[@data-testid='play-btn']"));
        playNextBtn.click();
        playButton.click();
        return this;
    }*/
    public boolean isSongPlaying(){
        return soundBar.isDisplayed();
    }
}
