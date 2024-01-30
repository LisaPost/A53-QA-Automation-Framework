package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlaylistPage extends BasePage{
    public PlaylistPage(WebDriver givenDriver) {
        super(givenDriver);
        PageFactory.initElements(givenDriver, this);
    }
    @FindBy(xpath = "//section[@id='playlists']//a[contains(text(),'New Playlist')]")
    public WebElement selectPlaylist;
    @FindBy(xpath = "//button[@class='del btn-delete-playlist']")
    public WebElement deletePlaylistBtn;
    @FindBy(css = "div.success.show")
    public WebElement parentSuccessMsg;
    @FindBy(xpath = "//button[@class='ok']")
    public WebElement submitOkBtn;
    public PlaylistPage selectPlaylist(String playlistName) {
        selectPlaylist.click();
        return this;
    }
    @SuppressWarnings("UnusedReturnValue")
    public PlaylistPage tapDeletePlaylistBtn() {

        deletePlaylistBtn.click();
        submitOkBtn.click();
        return this;
    }
    public String getPlaylistDeletedMsg() {
        return parentSuccessMsg.getText();
    }
}
