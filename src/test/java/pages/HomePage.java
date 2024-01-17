package pages;

import org.openqa.selenium.*;

public class HomePage extends BasePage {
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }
    By userAvatarIcon = By.cssSelector("img.avatar");
    By createPlaylistBtn = By.xpath("//i[@data-testid='sidebar-create-playlist-btn']");
    By createSimplePlaylistOption = By.xpath("//li[@data-testid='playlist-context-menu-create-simple']");
    By playlistNameTextField = By.xpath("//section[@id='playlists']//input[@type='text']");
    By parentSuccessMsg = By.cssSelector("div.success.show");
    By searchField = By.cssSelector("input[type='search']");
    By viewAllSongsBtn = By.cssSelector("button[data-test='view-all-songs-btn']");
    By firstSongOnList = By.xpath("//section[@id='songResultsWrapper']//table[contains(@class, 'items')]//tr[1]");
    By addToPlaylistBtn = By.cssSelector("button[class='btn-add-to']");
    By playlistInDropdown = By.xpath("//section[@id='songResultsWrapper']//li[contains(@class,'playlist') and contains(text(),'New Playlist')]");

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
    public void viewAll() {
        try {
            findElementToClick(viewAllSongsBtn).click();
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
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
    public String getSongAddedMsg() {
        try {
            return findElement(parentSuccessMsg).getText();
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
            return null;
        }
    }
    public void clickPlay(){
        WebElement playNextBtn = driver.findElement(By.xpath("//i[@data-testid='play-next-btn']"));
        //findElement(playNextBtn).click();
        //findElement(playBtn).click();
        WebElement playButton = driver.findElement(By.xpath("//span[@data-testid='play-btn']"));
        playNextBtn.click();
        playButton.click();
    }
    public boolean isSongPlaying(){
        WebElement soundBar = driver.findElement(By.xpath("//div[@data-testid='sound-bar-play']"));
        return soundBar.isDisplayed();
    }
}
