package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    //constructor:
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }
    //locators:
    By userAvatarIcon = By.cssSelector("img.avatar");
    By createPlaylistBtn = By.xpath("//i[@data-testid='sidebar-create-playlist-btn']");
    By createSimplePlaylistOption = By.xpath("//li[@data-testid='playlist-context-menu-create-simple']");
    By playlistNameTextField = By.xpath("//section[@id='playlists']//input[@type='text']");
    By parentSuccessMsg = By.cssSelector("div.success.show"); //duplicate
    By searchField = By.cssSelector("input[type='search']");
    By viewAllSongsBtn = By.cssSelector("button[data-test='view-all-songs-btn']");
    By firstSongOnList = By.xpath("//section[@id='songResultsWrapper']//table[contains(@class, 'items')]//tr[1]");
    By addToPlaylistBtn = By.cssSelector("button[class='btn-add-to']");
    By playlistInDropdown = By.xpath("//section[@id='songResultsWrapper']//li[contains(@class,'playlist') and contains(text(),'New Playlist')]");
    //By parentSuccessMsg = By.cssSelector("div.success.show");
    By playBtn = By.xpath("//span[@data-testid='play-btn']");
    By playNextBtn = By.xpath("//i[@data-testid='play-next-btn");
    By soundBarPlay = By.xpath("//div[@data-testid='sound-bar-play']");
    By songAddedMsg = By.xpath("//div[contains(text(),'Added 1 song into \"New Playlist.\"')]");

    //page methods:
    public WebElement getUserAvatar() {
        return findElement(userAvatarIcon);
    }
    public void clickCreatePlaylistBtn() {
        findElement(createPlaylistBtn).click();
    }
    public void selectCreateSimplePlaylistOption() {
        findElementToClick(createSimplePlaylistOption).click();
    }
    public void enterPlaylistName(String playlistName) {
        findElement(playlistNameTextField).sendKeys(playlistName);
        findElement(playlistNameTextField).sendKeys(Keys.ENTER);
    }
    public String getPlaylistCreatedMsg() {
        return findElement(parentSuccessMsg).getText();
    }
    public void searchSong(String song) {
        findElement(searchField).clear();
        findElement(searchField).sendKeys(song);
    }

    /*public void viewAll() {
        findElementToClick(viewAllSongsBtn).click();
    }*/

    public void viewAll() {
        try {
            findElementToClick(viewAllSongsBtn).click();
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
            //e.printStackTrace();
        }
    }

    public void selectFirstSongOnList() {
        try {
            findElementToClick(firstSongOnList).click();
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
        }
    }

    public void addSelectedSongToExistingPlaylist() {
        try {
            findElementToClick(addToPlaylistBtn).click();
            findElement(playlistInDropdown).click();
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
        }
    }

    /*public String getSongAddedMsg() {
        try {
            WebElement parentElement = wait.until(ExpectedConditions.visibilityOfElementLocated(parentSuccessMsg));
            WebElement msgElement = parentElement.findElement(songAddedMsg);
            return msgElement.getText();
        } catch (TimeoutException e) {
            WebElement parentElement = findElement(parentSuccessMsg);
            WebElement msgElement = parentElement.findElement(songAddedMsg);
            return msgElement.getText();
        }
    }*/
    public String getSongAddedMsg() {
        try {
            return findElement(parentSuccessMsg).getText();
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
            return null;
        }
    }
    /*public void playNextSong() {
        try {
            findElement(playNextBtn).click();
            actions.moveToElement((WebElement) playNextBtn).perform();
            ((WebElement) playNextBtn).click();
            findElement(playSongBtn).click();
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
        }
    }*/
    public void clickPlay(){
        WebElement playNextBtn = driver.findElement(By.xpath("//i[@data-testid='play-next-btn']"));
        //findElement(playNextBtn).click();
        //findElement(playBtn).click();
        WebElement playButton = driver.findElement(By.xpath("//span[@data-testid='play-btn']"));
        playNextBtn.click();
        playButton.click();
    }
    /*public boolean isSongPlaying(){
        findElement(soundBarPlay);
        return ((WebElement) soundBarPlay).isDisplayed();
    }*/
    public boolean isSongPlaying(){
        WebElement soundBar = driver.findElement(By.xpath("//div[@data-testid='sound-bar-play']"));
        return soundBar.isDisplayed();
    }
}
