import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.List;

public class BaseTest {
    ChromeDriver driver;
    WebDriverWait wait;
    public Actions actions;

    @DataProvider(name = "LoginPositiveTest")
    public Object[][] getDataForPositiveLogin() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92"}
        };
    }

    @DataProvider(name = "LoginNegativeTests")
    public Object[][] getDataForNegativeLogin() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "12345678", "https://qa.koel.app/"},
                {"nonperson@testpro.io", "YrkeNi92", "https://qa.koel.app/"},
                {"", "YrkeNi92", "https://qa.koel.app/"},
                {"yelyzaveta.postnova@testpro.io", "", "https://qa.koel.app/"},
                {"", "", "https://qa.koel.app/"}
        };
    }

    @DataProvider(name = "AddPlaylistPositiveTest")
    public Object[][] getDataForAddPlaylist() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92", "New Playlist"}
        };
    }

    @DataProvider(name = "RenamePlaylistPositiveTest")
    public Object[][] getDataForRenamePlaylist() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92", "New Playlist", "Renamed Playlist"}
        };
    }

    @DataProvider(name = "AddSongPositiveTest")
    public Object[][] getDataForAddSong() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92", "New Playlist", "take my hand"}
        };
    }

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    @Parameters({"BaseUrl"})
    public void launchBrowser(String BaseUrl) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        driver.get(BaseUrl);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    public void provideEmail(String username) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("input[type='email']")));
        emailField.clear();
        emailField.sendKeys(username);
    }

    public void providePassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("input[type='password']")));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void login() {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("button[type='submit']"))).click();
    }

    public void loginToApp(String username, String password) {
        provideEmail(username);
        providePassword(password);
        login();
    }

    public void createPlaylist(String playlistName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//i[@data-testid='sidebar-create-playlist-btn']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//li[@data-testid='playlist-context-menu-create-simple']"))).click();
        WebElement typePlaylistName = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//section[@id='playlists']//input[@type='text']")));
        typePlaylistName.sendKeys(playlistName);
        typePlaylistName.sendKeys(Keys.ENTER);
    }

    public String getPlaylistCreatedMsg() {
        WebElement createdPlaylistMsg = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("div.success.show")));
        return createdPlaylistMsg.getText();
    }

    public void searchSong(String song) {
        WebElement searchField = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("input[type='search']")));
        searchField.clear();
        searchField.sendKeys(song);
    }

    public void viewAll() {
        wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("button[data-test='view-all-songs-btn']"))).click();
    }

    public void selectFirstItem() {
        wait.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//section[@id='songResultsWrapper']//table[contains(@class, 'items')]//tr[1]"))).click();
    }

    public void addSelectedSong() {
        wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("button[class='btn-add-to']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//section[@id='songResultsWrapper']//li[contains(@class,'playlist') " +
                        "and contains(text(),'New Playlist')]"))).click();
        /*wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//section[@id='songResultsWrapper']//li[contains(@class,'playlist') " +
                        "and contains(text(),'\" + playlistName + \"')]"))).click();*/
    }

    public String getSongAddedMsg() {
        WebElement songAddedMsg = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("div.success.show")));
        return songAddedMsg.getText();
    }

    public void playNextSong() {
        Actions actions = new Actions(driver);
        WebElement playButton = driver.findElement(By.cssSelector("span .play i"));
        //WebElement playButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span .play i")));
        actions.moveToElement(playButton).perform();
        playButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//i[@data-testid='play-next-btn']"))).click();
    }

    public void selectPlaylist() {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//section[@id='playlists']//a[contains(text(),'New Playlist')]"))).click();
    }

    public String getPlaylistDeletedMsg() {
        WebElement deletedPlaylistMsg = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("div.success.show")));
        return deletedPlaylistMsg.getText();
    }

    public void selectAllSongsList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//a[@href='#!/songs']"))).click();
    }

    public void contextClickFirstSong() {
        WebElement firstSongOnList = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@class='song-list-wrap main-scroll-wrap all-songs']//table[@class='items']/tr[1]")));
        actions.contextClick(firstSongOnList).perform();
    }

    public void selectPlayOption() {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//li[@class='playback']/span[text()='Play']"))).click();
    }

    public boolean isSongPlaying() {
        WebElement soundBarVisualizer = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//img[@alt='Sound bars']")));
        return soundBarVisualizer.isDisplayed();
    }

    public WebElement hoverPlay() {
        WebElement playBtn = driver.findElement(By.xpath("//span[@data-testid='play-btn']"));
        actions.moveToElement(playBtn).perform();
        return wait.until(ExpectedConditions.visibilityOf(playBtn));
    }

    public void selectPlaylistByName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='#!/favorites']"))).click();
    }

    public int countSongs() {
        return driver.findElements(By.xpath("//section[@id='favoritesWrapper']//td[@class='title']")).size();
    }

    public String getPlaylistDetails() {
        return driver.findElement(By.xpath("//span[@class='meta text-secondary']//span[@class='meta']")).getText();
    }

    public void displayAllSongs() {
        List<WebElement> songList = driver.findElements(By.xpath("//section[@id='favoritesWrapper']//td[@class='title']"));
        System.out.println("Number of songs found: " + countSongs());
        for (WebElement e : songList) {
            System.out.println(e.getText());
        }
    }

    public void contextClickOnSelectedPlaylist() {
        WebElement selectedPlaylist = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//section[@id='playlists']//a[@class='active']")));
        actions.contextClick(selectedPlaylist).perform();
    }

    public void selectEditPlaylistOption() {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-testid='playlist-context-menu-edit-85383']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@id='playlists']//li[contains(text(),'Edit')]"))).click();
    }

    public void editPlaylist(String newPlaylistName) {
        WebElement editPlaylistField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='playlist playlist editing']//input[@type='text']")));
        editPlaylistField.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        editPlaylistField.sendKeys(newPlaylistName);
        editPlaylistField.sendKeys(Keys.ENTER);
    }

    public String getPlaylistUpdatedMsg() {
        WebElement updatedPlaylistMsg = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("div.success.show")));
        return updatedPlaylistMsg.getText();
    }
}