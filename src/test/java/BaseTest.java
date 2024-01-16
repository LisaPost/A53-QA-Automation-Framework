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

public class BaseTest {
    ChromeDriver driver;
    WebDriverWait wait;
    public Actions actions;

    @DataProvider(name = "RenamePlaylistPositiveTest")
    public Object[][] getDataForRenamePlaylist() {
        return new Object[][]{
                {"yelyzaveta.postnova@testpro.io", "YrkeNi92", "New Playlist", "Renamed Playlist"}
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
    public void selectPlaylist(String playlistName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//section[@id='playlists']//a[contains(text(),'" +playlistName+ "')]"))).click();
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
        WebElement parentElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        //WebElement updatedPlaylistMsg = parentElement.findElement(By.xpath("//div[contains(text(),'Updated playlist " +newPlaylistName+ ")]"));
        //Updated playlist \"" + newPlaylistName + ".\"
        WebElement updatedPlaylistMsg = parentElement.findElement(By.xpath("//div[contains(text(),'Updated playlist \"Renamed Playlist.\"')]"));
        return updatedPlaylistMsg.getText();
    }
}