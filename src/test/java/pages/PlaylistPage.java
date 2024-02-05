package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlaylistPage extends BasePage {
    public PlaylistPage(WebDriver givenDriver) {
        super(givenDriver);
        //PageFactory.initElements(givenDriver, this);
    }
    /*@FindBy(xpath = "//section[@id='playlists']//a[contains(text(),'\" + playlistName + \"')]")
    WebElement selectPlaylist;*/
    /*@FindBy(xpath = "//section[@id='playlists']//a[contains(text(),\"%s\")]")
    WebElement selectPlaylist;*/
    @FindBy(xpath = "//button[@class='del btn-delete-playlist']")
    public WebElement deletePlaylistBtn;
    @FindBy(css = "div.success.show")
    public WebElement parentSuccessMsg;
    @FindBy(xpath = "//div[@class='dialog']//button[@class='ok']")
    public WebElement submitOkBtn;
    @FindBy(xpath = "//section[@id='playlists']//li[contains(text(),'Edit')]")
    public WebElement editOption;
    @FindBy(xpath = "//li[@class='playlist playlist editing']//input[@type='text']")
    public WebElement editPlaylistField;

    public WebElement waitPlaylistDeletedMsg() {
        return parentSuccessMsg;
    }
    public WebElement waitPlaylistUpdatedMsg() {
        return parentSuccessMsg;
    }
    public String getPlaylistDeletedMsg() {
        return parentSuccessMsg.getText();
    }
    public String getPlaylistUpdatedMsg() {
        return parentSuccessMsg.getText();
    }
}
