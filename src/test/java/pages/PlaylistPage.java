package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PlaylistPage extends BasePage {
    //constructor:
    public PlaylistPage(WebDriver givenDriver) {
        super(givenDriver);
    }
    //locators:
    By selectPlaylist = By.xpath("//section[@id='playlists']//a[contains(text(),'New Playlist')]");
    //By playlistDeletedMsg = By.cssSelector("div.success.show");
    By deletePlaylistBtn = By.xpath("//button[@class='del btn-delete-playlist']");
    By parentSuccessMsg = By.cssSelector("div.success.show");
    By submitDialog = By.xpath("//p[@class='msg']");
    By submitOkBtn = By.xpath("//button[@class='ok']");
    //By playlistDeletedMsg = By.xpath("//div[contains(text(),'Deleted playlist \"New Playlist.\"')]");


    //page methods:
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
    /*public String getPlaylistDeletedMsg() {
        try {
            WebElement parentElement = wait.until(ExpectedConditions.visibilityOfElementLocated(parentSuccessMsg));
            WebElement msgElement = parentElement.findElement(playlistDeletedMsg);
            return msgElement.getText();
        } catch (TimeoutException e) {
            WebElement parentElement = findElement(parentSuccessMsg);
            WebElement msgElement = parentElement.findElement(playlistDeletedMsg);
            return msgElement.getText();
        }
    }*/
    /*public String getPlaylistDeletedMsg() {
        try {
            WebElement submitDialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='msg']")));
            if(submitDialog.isDisplayed()){
                findElement(submitOkBtn).click();
        }
            wait.until(ExpectedConditions.visibilityOfElementLocated(parentSuccessMsg));
            return findElement(parentSuccessMsg).getText();
        } catch(Exception e) {
            e.printStackTrace();
            return "Error occurred while getting the playlist deleted message";
            //wait.until(ExpectedConditions.visibilityOfElementLocated(parentSuccessMsg));
            //return findElement(parentSuccessMsg).getText();
        }
    }*/
    /*public String getPlaylistDeletedMsg() {
        try {
            WebElement submitDialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='msg']")));
            if (submitDialog.isDisplayed()) {
                findElement(submitOkBtn).click();
            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(parentSuccessMsg));
            return findElement(parentSuccessMsg).getText();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while getting the playlist deleted message";
        }
    }*/
}
