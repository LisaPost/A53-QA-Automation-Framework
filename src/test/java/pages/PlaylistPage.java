package pages;

import org.openqa.selenium.*;

public class PlaylistPage extends BasePage {
    public PlaylistPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    By selectPlaylist = By.xpath("//section[@id='playlists']//a[contains(text(),'New Playlist')]");
    //By playlistDeletedMsg = By.cssSelector("div.success.show");
    By deletePlaylistBtn = By.xpath("//button[@class='del btn-delete-playlist']");
    By parentSuccessMsg = By.cssSelector("div.success.show");
    By submitDialog = By.xpath("//p[@class='msg']");
    By submitOkBtn = By.xpath("//button[@class='ok']");

    public void selectPlaylist() {
        findElement(selectPlaylist).click();
    }
    public void tapDeletePlaylistBtn() {

        findElement(deletePlaylistBtn).click();
        findElement(submitDialog);
        findElement(submitOkBtn).click();
    }
    public String getPlaylistDeletedMsg() {
        return findElement(parentSuccessMsg).getText();
    }
}
