package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class PlaylistPage extends BasePage {
    public PlaylistPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    @FindBy(xpath = "//section[@id='playlists']//a[contains(text(),'New Playlist')]")
    WebElement selectPlaylist;
    @FindBy(xpath = "//button[@class='del btn-delete-playlist']")
    WebElement deletePlaylistBtn;
    @FindBy(css = "div.success.show")
    WebElement parentSuccessMsg;
    //@FindBy(xpath = "//p[@class='msg']")
    //WebElement submitDialog;
    @FindBy(xpath = "//button[@class='ok']")
    WebElement submitOkBtn;
    public PlaylistPage selectPlaylist() {
        selectPlaylist.click();
        return this;
    }
    public PlaylistPage tapDeletePlaylistBtn() {

        deletePlaylistBtn.click();
        //WebElement submitDialog1 = submitDialog;
        submitOkBtn.click();
        return this;
    }
    public String getPlaylistDeletedMsg() {
        return parentSuccessMsg.getText();
    }
}
