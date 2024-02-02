package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    private WebElement playlistInDropdown;
    public HomePage(WebDriver givenDriver) {
        super(givenDriver);
    }
    @FindBy(css = "img.avatar")
    public WebElement userAvatarIcon;
    @FindBy(xpath = "//i[@data-testid='sidebar-create-playlist-btn']")
    WebElement createPlaylistBtn;
    @FindBy(xpath = "//li[@data-testid='playlist-context-menu-create-simple']")
    WebElement createSimplePlaylistOption;
    @FindBy(xpath = "//section[@id='playlists']//input[@type='text']")
    WebElement playlistNameTextField;
    @FindBy(css = "div.success.show")
    WebElement parentSuccessMsg;
    @FindBy(css = "[type='search']")
    WebElement searchField;
    @FindBy(css = "[data-test='view-all-songs-btn']")
    WebElement viewAllSongsBtn;
    @FindBy(xpath = "//section[@id='songResultsWrapper']//table[contains(@class, 'items')]//tr[1]")
    WebElement firstSongOnList;
    @FindBy(css = "[class='btn-add-to']")
    WebElement addToPlaylistBtn;
    @FindBy(xpath = "//section[@id='songResultsWrapper']//li[contains(@class,'playlist') and contains(text(),\"%s\")]")
    private String playlistInDropdownXPath = "//section[@id='songResultsWrapper']//li[contains(@class,'playlist') and contains(text(),\"%s\")]";
    @FindBy(xpath = "//a[@class='logout control']")
    WebElement logoutBtn;
    @FindBy(css = "a.view-profile>span")
    public WebElement actualProfileName;
    @FindBy(xpath = "//section[@id='playlists']//a[contains(text(),\"%s\")]")
    WebElement selectPlaylist;
    @FindBy(xpath = "//section[@id='playlists']//a[contains(text(),\"Playlist with song to Delete\")]")
    WebElement selectPlaylistToDelete;

    public WebElement getUserAvatar() {return userAvatarIcon;}
    public HomePage clickCreatePlaylistBtn() {createPlaylistBtn.click(); return this;}
    public HomePage selectCreateSimplePlaylistOption() {
        createSimplePlaylistOption.click();
        return this;
    }
    @SuppressWarnings("UnusedReturnValue")
    public HomePage enterPlaylistName(String playlistName) {
        playlistNameTextField.sendKeys(playlistName);
        playlistNameTextField.sendKeys(Keys.ENTER);
        return this;
    }
    public String getPlaylistCreatedMsg() {
        return parentSuccessMsg.getText();
    }
    public HomePage searchSong(String song) {
        searchField.clear();
        searchField.sendKeys(song);
        return this;
    }
    public HomePage viewAll() {
        try {
            viewAllSongsBtn.click();
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
        }
        return this;
    }
    public HomePage selectFirstSongOnList() {
        try {
            firstSongOnList.click();
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
        }
        return this;
    }
    private String getDynamicPlaylistXPath(String playlistName) {
        return String.format(playlistInDropdownXPath, playlistName);
    }
    @SuppressWarnings("UnusedReturnValue")
    /*public HomePage addSelectedSongToExistingPlaylist(String playlistName) {
        try {
            addToPlaylistBtn.click();
            playlistInDropdown = driver.findElement(By.xpath(String.format(playlistInDropdownXPath, playlistName)));
            playlistInDropdown.click();
            playlistInDropdown.click();
        } catch (Exception e) {
            System.out.println("Smth went wrong: " + e);
        }
        return this;
    }*/
    public HomePage addSelectedSongToExistingPlaylist(String playlistName) {
        try {
            addToPlaylistBtn.click();
            playlistInDropdownXPath = getDynamicPlaylistXPath(playlistName);

            playlistInDropdown = driver.findElement(By.xpath(playlistInDropdownXPath));
            playlistInDropdown.click();
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e);
        }
        return this;
    }
    public WebElement getPlaylistElementByName(String playlistName) {
        String dynamicXpath = "//section[@id='songResultsWrapper']//li[contains(@class,'playlist') and contains(text(), '" + playlistName + "')]";
        return driver.findElement(By.xpath(dynamicXpath));
    }
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
    @SuppressWarnings("UnusedReturnValue")
    public HomePage clickPlay(){
        WebElement playNextBtn = driver.findElement(By.xpath("//i[@data-testid='play-next-btn']"));
        WebElement playButton = driver.findElement(By.xpath("//span[@data-testid='play-btn']"));
        playNextBtn.click();
        playButton.click();
        return this;
    }
    public boolean isSongPlaying(){
        WebElement soundBar = driver.findElement(By.xpath("//div[@data-testid='sound-bar-play']"));
        return soundBar.isDisplayed();
    }
    public HomePage logout() {
        logoutBtn.click();
        return this;
    }
    public HomePage clickOnAvatar() {
        userAvatarIcon.click();
        return this;
    }
    public HomePage selectPlaylist(String playlistName) {
        try {
            selectPlaylist.click();
            String dynamicXPath = String.format("//section[@id='playlists']//a[contains(text(),'%s')]", playlistName);
            System.out.println("Constructed XPath: " + dynamicXPath);
            driver.findElement(By.xpath(dynamicXPath)).click();
        } catch (Exception e) {
            System.out.println("Failed to select playlist: " + e.getMessage());
        }
        return this;
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
