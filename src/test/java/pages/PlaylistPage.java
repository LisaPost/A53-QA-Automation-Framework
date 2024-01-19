package pages;

import org.openqa.selenium.*;

public class PlaylistPage extends BasePage {
    public PlaylistPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    private By selectPlaylist = By.xpath("//section[@id='playlists']//a[contains(text(),'New Playlist')]");
    //By playlistDeletedMsg = By.cssSelector("div.success.show");
    private By deletePlaylistBtn = By.xpath("//button[@class='del btn-delete-playlist']");
    private By parentSuccessMsg = By.cssSelector("div.success.show");
    private By submitDialog = By.xpath("//p[@class='msg']");
    private By submitOkBtn = By.xpath("//button[@class='ok']");

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
