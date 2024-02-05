package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.html.HTMLInputElement;

public class HomePage extends BasePage {
    private WebElement playlistInDropdown;
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
        //PageFactory.initElements(givenDriver, this);
    }
    @FindBy(css = "img.avatar")
    public WebElement userAvatarIcon;
    @FindBy(xpath = "//i[@data-testid='play-next-btn']")
    public WebElement playNextBtn;
    @FindBy(xpath = "//span[@data-testid='play-btn']")
    public WebElement playBtn;
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
    @FindBy(xpath = "//section[@id='songResultsWrapper']//li[contains(@class,'playlist') and contains(text(),\"%s\")]")
    private String playlistInDropdownXPath = "//section[@id='songResultsWrapper']//li[contains(@class,'playlist') and contains(text(),\"%s\")]";
    @FindBy(xpath = "//section[@id='songResultsWrapper']//li[contains(@class,'playlist') and contains(text(),\"Playlist with Song\")]")
    public WebElement playlistForSongInDropdown;
    @FindBy(xpath = "//section[@id='songResultsWrapper']//li[contains(@class,'playlist') and contains(text(),\"Playlist with song to Delete\")]")
    public WebElement playlistFromDropdown;
    @FindBy(xpath = "//a[@class='logout control']")
    WebElement logoutBtn;
    @FindBy(css = "a.view-profile>span")
    public WebElement actualProfileName;
    //@FindBy(xpath = "//section[@id='playlists']//a[contains(text(),'\" + playlistName + \"')]")
    @FindBy(xpath = "//section[@id='playlists']//a[contains(text(),\"%s\")]")
    public WebElement selectPlaylist;
    @FindBy(xpath = "//section[@id='playlists']//a[contains(text(),\"Playlist to Rename\")]")
    public WebElement selectPlaylistToRename;
    /*@FindBy(xpath = "//section[@id='playlists']//a[contains(text(),\"Playlist with Song\")]")
    public WebElement selectPlaylistToAddSong;*/
    @FindBy(xpath = "//section[@id='playlists']//a[contains(text(),\"Playlist with song to Delete\")]")
    public WebElement selectPlaylistToDelete;
    @FindBy(xpath = "//section[@id='playlists']//a[contains(text(),\"Playlist to Delete\")]")
    public WebElement selectEmptyPlaylistToDelete;
    /*@FindBy(xpath = "//section[@id='playlists']//a[contains(text(),\"New Playlist\")]")
    public WebElement selectNewPlaylistToDelete;
    @FindBy(xpath = "//section[@id='playlists']//a[contains(text(),\"Renamed Playlist\")]")
    public WebElement selectRenamedPlaylistToDelete;
    @FindBy(xpath = "//section[@id='playlists']//a[contains(text(),\"Playlist with Song\")]")
    public WebElement selectAddSongPlaylistToDelete;*/

    public WebElement getUserAvatar() {return userAvatarIcon;}

    public String getPlaylistCreatedMsg() {
        return parentSuccessMsg.getText();
    }

    /*private String getDynamicPlaylistXPath(String playlistName) {
        return String.format(playlistInDropdownXPath, playlistName);
    }*/
    @SuppressWarnings("UnusedReturnValue")
    public String getSongAddedMsg() {
        try {
            return parentSuccessMsg.getText();
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
            return null;
        }
    }
    public WebElement waitSongAddedMsg() {
        return parentSuccessMsg;
    }

    public boolean isSongPlaying(){
        WebElement soundBar = driver.findElement(By.xpath("//div[@data-testid='sound-bar-play']"));
        return soundBar.isDisplayed();
    }
    public HomePage selectPlaylistToDelete(String playlistName) {
        try {
            selectPlaylistToDelete.click();
        } catch (Exception e) {
            System.out.println("Failed to select playlist: " + e.getMessage());
        }
        return this;
    }
}
